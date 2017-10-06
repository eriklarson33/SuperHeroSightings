/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.controller;

import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.serviceLayer.ServiceLayer;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class SuperHumanController {

    private ServiceLayer service;

    @Inject
    public SuperHumanController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rootPage(Model model) {
        // Get all the Sightings from the DAO
        List<Sighting> latestSightingList = service.getAllSightingsLimit10();
        // Put the list of Sightings on the Model
        model.addAttribute("latestSightingList", latestSightingList);
        // Return the logical name of our View component
        return "index";
    }

    @RequestMapping(value = "/superHeroesAndVillains", method = RequestMethod.GET)
    public String displaySuperHumansPage(Model model) {

        // Get all the SuperHumans from the DAO
        List<SuperHuman> superHumanList = service.getAllSuperHumans();

        // Put the list of Contacts on the Model
        model.addAttribute("superHumanList", superHumanList);

        // Return the logical name of our View component
        return "superHeroesAndVillains";
    }

//    @RequestMapping(value = "/showAllSupers", method = RequestMethod.GET)
//    public String showAllSupersSearch(Model model) {
//
//        // Get all the SuperHumans from the DAO
//        List<SuperHuman> superHumanList = dao.getAllSuperHumans();
//
//        // Put the list of Contacts on the Model
//        model.addAttribute("superHumanList", superHumanList);
//
//        // Return the logical name of our View component
//        return "redirect:superHeroesAndVillains";
//    }
//
    @RequestMapping(value = "/editSuper", method = RequestMethod.POST)
    public String showSuperById(HttpServletRequest request) {

        // Get the id of the desired Super Human
        String searchCriteria = request.getParameter("superHumanId");
        int superId = Integer.parseInt(searchCriteria);
        
        // Get the superHuman model using the superHumanId
        SuperHuman editSuper = service.getSuperHumanById(superId);
        
        // Get the "edited" Name from the JSP and update it
        String name = request.getParameter("superHumanName");
        editSuper.setSuperHumanName(name);
        
        // Get the "edited" Description from the JSP and update it
        String description = request.getParameter("description");
        editSuper.setDescription(description);
        
        // Update the Dao
        service.updateSuperHuman(editSuper);

        return "redirect:superHeroesAndVillains";
    }

//
//    @RequestMapping(value = "/searchSuperByOrg", method = RequestMethod.GET)
//    public String showSuperByOrg(HttpServletRequest request, Model model) {
//
//        String searchCriteria = request.getParameter("searchCriteria");
//
//        int orgId = Integer.parseInt(searchCriteria);
//
//        List<SuperHuman> superHumanList = dao.getAllSuperHumansByOrg(orgId);
//
//        model.addAttribute("superHumanList", superHumanList);
//
//        return "redirect:superHeroesAndVillains";
//    }
    @RequestMapping(value = "/createSuperHuman", method = RequestMethod.POST)
    public String createSuperHuman(HttpServletRequest request) {
        // grab the incoming values from the form and create a new SuperHuman object
        SuperHuman superHuman = new SuperHuman();
        superHuman.setSuperHumanName(request.getParameter("superHumanName"));
        superHuman.setDescription(request.getParameter("description"));

        // persist the new Super Human
        //dao.addSuperHuman(superHuman);
        service.addSuperHuman(superHuman);

        return "redirect:superHeroesAndVillains";
    }

    @RequestMapping(value = "/displayProfile", method = RequestMethod.GET)
    public String displaySuperProfile(HttpServletRequest request, Model model) {
        String superIdParameter = request.getParameter("superId");
        int superId = Integer.parseInt(superIdParameter);

        // get the profile information of the selected Super
        //SuperHuman superProfile = dao.getSuperHumanById(superId);
        SuperHuman superProfile = service.getSuperHumanById(superId);

        model.addAttribute("superProfile", superProfile);

        return "editSuperHuman";
    }

    @RequestMapping(value = "/searchSupers", method = RequestMethod.GET)
    public String getSearchRequest(HttpServletRequest request, Model model) {

        String search = request.getParameter("requestedSearch");

        String criteria = request.getParameter("searchCriteria");

        String ret = "";

        if ((search).equals("findById")) {
            // Get the Super Human Id
            int superId = Integer.parseInt(criteria);

            // Get the SuperHumans from the DAO
            //SuperHuman superHuman = dao.getSuperHumanById(superId);
            SuperHuman superHuman = service.getSuperHumanById(superId);

            // Add the superHuman to a list
            List superHumanList = new ArrayList();
            superHumanList.add(superHuman);

            // Put the list of Contacts on the Model
            model.addAttribute("superHumanList", superHumanList);

        } else if ((search).equals("findByOrg")) {
            // Get the Org Id
            int orgId = Integer.parseInt(criteria);

            //List<SuperHuman> superHumanList = dao.getAllSuperHumansByOrg(orgId);
            List<SuperHuman> superHumanList = service.getAllSuperHumansByOrg(orgId);

            model.addAttribute("superHumanList", superHumanList);

        } else if ((search).equals("findBySighting")) {
            // Get the Sighting Id
            int sightingId = Integer.parseInt(criteria);

            //Get the List of Super Humans
            List<SuperHuman> superHumanList = service.getAllSuperHumansBySighting(sightingId);

            //  Add the list of supers to the model
            model.addAttribute("superHumanList", superHumanList);

        } else if ((search).equals("showAllSupers")) {
            // Get all the SuperHumans from the DAO
            //List<SuperHuman> superHumanList = dao.getAllSuperHumans();
            List<SuperHuman> superHumanList = service.getAllSuperHumans();

            // Put the list of Contacts on the Model
            model.addAttribute("superHumanList", superHumanList);
        }
        // Return the logical name of our View component
        return "superHeroesAndVillains";
    }

    @RequestMapping(value = "/deleteSuperHuman", method = RequestMethod.GET)
    public String deleteSuperHuman(HttpServletRequest request, Model model) {
        String superIdParameter = request.getParameter("superId");
        int supId = Integer.parseInt(superIdParameter);
        service.deleteSuperHuman(supId);
        return "redirect:superHeroesAndVillains";
    }

}
