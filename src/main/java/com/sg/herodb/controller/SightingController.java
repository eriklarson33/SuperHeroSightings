/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.controller;

import com.sg.herodb.dao.HeroDao;
import com.sg.herodb.model.Sighting;
import com.sg.herodb.serviceLayer.ServiceLayer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eriklarson-laptop
 */
@Controller
public class SightingController {
    
    private ServiceLayer service;
    
    @Inject
    public SightingController (ServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/sightings", method=RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        
        // Get all the Sightings from the DAO
        List<Sighting> sightingList = service.getAllSightings();
        
        // Put the list of Sightings on the Model
        model.addAttribute("sightingList", sightingList);
        
        
        // Return the logical name of our View component
        return "sightings";
    }
    
    @RequestMapping(value="/showAllSightings", method=RequestMethod.GET)
    public String showAllSightingsSearch(Model model) {
        
        // Get all the Sightings from the DAO
        List<Sighting> sightingList = service.getAllSightings();
        
        // Put the list of Sightings on the Model
        model.addAttribute("sightingList", sightingList);
        
        // Return the logical name of our View component
        return "redirect:sightings";
    }
    
    @RequestMapping(value="/searchSightingById", method=RequestMethod.GET)
    public String showSightingById(HttpServletRequest request, Model model) {
        // Get the id of the desired sighting
        String searchCriteria = request.getParameter("searchCriteria");
        int sightingId = Integer.parseInt(searchCriteria);
        
        // Get all the sightings from the DAO
        Sighting sightingList = service.getSightingById(sightingId);
        
        // Put the list of Sightings on the Model
        model.addAttribute("sightingList", sightingList);
        
        // Return the logical name of our View component
        return "redirect:sightings";
    }
    
    @RequestMapping (value= "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {
        // grab the incoming values from the form and create a new Sighting object
        Sighting site = new Sighting();
        site.setName(request.getParameter("siteName"));
        site.setDescription(request.getParameter("description"));
        site.setStreet(request.getParameter("street"));
        site.setCity(request.getParameter("city"));
        site.setState(request.getParameter("state"));
        site.setZip(request.getParameter("zip"));
        site.setLatitude(new BigDecimal (request.getParameter("latitude")));
        site.setLongitude(new BigDecimal(request.getParameter("longitude")));
        
        String sDate = request.getParameter("sightingDate");
        LocalDate siteDate = LocalDate.parse(sDate, DateTimeFormatter.ISO_LOCAL_DATE);
        
        // set the LocalDateTime
        site.setSightingDate(siteDate);
        
        // persist the new Sighting
        service.addSighting(site);
        
        return "redirect:sightings";
    }
    
    
    @RequestMapping (value= "/displaySiteProfile", method=RequestMethod.GET)
    public String displaySiteProfile (HttpServletRequest request, Model model) {
        String siteIdParameter = request.getParameter("siteId");
        int siteId = Integer.parseInt(siteIdParameter);
        
        // get the profile information of the selected site
        Sighting siteProfile = service.getSightingById(siteId);
        
        model.addAttribute("siteProfile", siteProfile);
        
//        // get the list of Super Humans at the sighting.
//        List<SuperHuman> superList = dao.getAllSuperHumansBySighting(siteId);
//        
//        model.addAttribute("superList", superList);
//        
//        // Get the list of all available Super Humans that may be added to the Sighting.
//        List<SuperHuman> totalSupersList = dao.getAllSuperHumans();
//        
//        model.addAttribute("totalListOfSupers", totalSupersList);
        
        return "editSightingForm";
    }
    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting (@Valid @ModelAttribute("siteProfile") Sighting sighting, BindingResult result) {
        
        if(result.hasErrors()) {
            return "editSightingForm";
        }
        
        
        
        service.updateSighting(sighting);
        
        return "redirect:sightings";
    }
    
    @RequestMapping(value = "/selectSuperHumans", method = RequestMethod.POST)
    public String updateSupersSighted(HttpServletRequest request, Model model) {
        
        model.addAttribute("totalSupersList", service.getAllSuperHumans());
        
        //dao.updateSuperHuman(superHuman);
        
        return "redirect:sightings";
    }
    
    @RequestMapping(value="/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("siteId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        service.deleteSighting(sightingId);
        return "redirect:sightings";
    }
    
    @RequestMapping(value = "/searchSightings", method = RequestMethod.GET)
    public String getSearchRequest(HttpServletRequest request, Model model) {

        String search = request.getParameter("requestedSearch");

        String criteria = request.getParameter("searchCriteria");

        String ret = "";

        if ((search).equals("sightingBySuperId")) {
            // Get the Super Human Id
            int superId = Integer.parseInt(criteria);

            // Get the SuperHumans from the DAO
            //SuperHuman superHuman = dao.getSuperHumanById(superId);
//            SuperHuman superHuman = service.getSuperHumanById(superId);

            // Add the sighting to a list
            List <Sighting>siteList = service.getAllSightingsBySuperHuman(superId);
            
            // Put the list of Sightings on the Model
            model.addAttribute("sightingList", siteList);

        } else if ((search).equals("sightingByDate")) {
            // Get the Org Id
            String sDate = criteria;
            LocalDate siteDate = LocalDate.parse(sDate, DateTimeFormatter.ISO_LOCAL_DATE);

            //List<SuperHuman> superHumanList = dao.getAllSuperHumansByOrg(orgId);
            List <Sighting>siteList = service.getAllSightingsByDate(siteDate);

            // Put the list of Sightings on the Model
            model.addAttribute("sightingList", siteList);

        } else if ((search).equals("showSightingsLimit10")) {

            //Get the List of Sightings
            List<Sighting>siteList = service.getAllSightingsLimit10();
                    
            //  Add the list of sightings to the model
            model.addAttribute("sightingList", siteList);

        } else if ((search).equals("showSightings")) {
            // Get all the Sightings from the DAO
            List<Sighting>siteList = service.getAllSightings();

            //  Add the list of sightings to the model
            model.addAttribute("sightingList", siteList);
        }
        // Return the logical name of our View component
        return "sightings";
    }
    
}
