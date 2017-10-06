/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.controller;

import com.sg.herodb.model.SuperPower;
import com.sg.herodb.serviceLayer.ServiceLayer;
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
public class SuperPowerController {
        
    private ServiceLayer service;
    
    @Inject
    public SuperPowerController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/superPowers", method = RequestMethod.GET)
    public String displaySuperPowersPage(Model model) {

        // Get all the SuperHumans from the DAO
        List<SuperPower> superPowerList = service.getAllSuperPowers();

        // Put the list of Contacts on the Model
        model.addAttribute("superPowerList", superPowerList);

        // Return the logical name of our View component
        return "superPowers";
    }
    
    @RequestMapping(value = "/showAllSuperPowers", method = RequestMethod.GET)
    public String showAllSuperPowersSearch(Model model) {

       // Get all the SuperHumans from the DAO
        List<SuperPower> superPowerList = service.getAllSuperPowers();

        // Put the list of Contacts on the Model
        model.addAttribute("superPowerList", superPowerList);

        // Return the logical name of our View component
        return "superPowers";
    }
    
    @RequestMapping(value = "/searchPowerById", method = RequestMethod.GET)
    public String showOrgById(HttpServletRequest request, Model model) {
        // Get the id of the desired Super Human
        String searchCriteria = request.getParameter("searchCriteria");
        int powerId = Integer.parseInt(searchCriteria);
        // Get all the SuperHumans from the DAO
        SuperPower powerList = service.getSuperPowerById(powerId);

        // Put the list of Contacts on the Model
        model.addAttribute("powerList", powerList);

        // Return the logical name of our View component
        return "redirect:superPowers";
    }
    
    @RequestMapping(value = "/createSuperPower", method = RequestMethod.POST)
    public String createOrg(HttpServletRequest request) {
        // grab the incoming values from the form and create a new SuperHuman object
        SuperPower power = new SuperPower();
        power.setName(request.getParameter("powerName"));
        power.setDescription(request.getParameter("powerDescription"));
        
//        org.setOrganizationName(request.getParameter("orgName"));
//        org.setDescription(request.getParameter("description"));
//        org.setStreet(request.getParameter("street"));
//        org.setCity(request.getParameter("city"));
//        org.setState(request.getParameter("state"));
//        org.setZip("zip");
//        org.setPhone("phone");

        // persist the new Organization
        service.addSuperPower(power);
        
        return "redirect:superPowers";
    }
    
    @RequestMapping(value = "/displayPowerProfile", method = RequestMethod.GET)
    public String displayPowerProfile(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powerTypeId");
        int powerId = Integer.parseInt(powerIdParameter);

        // get the profile information of the selected Super
        SuperPower powerProfile = service.getSuperPowerById(powerId);
        
        model.addAttribute("powerProfile", powerProfile);
        
        return "editSuperPowers";
    }
    
    @RequestMapping(value = "/editCurrentSuperPower", method = RequestMethod.POST)
    public String editSuperPower(@Valid @ModelAttribute("powerProfile") SuperPower superPower, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editSuperPowers";
        }
        
        service.updateSuperPower(superPower);
        
        return "redirect:superPowers";
    }
    
    @RequestMapping(value = "/deleteSuperPower", method = RequestMethod.GET)
    public String deleteSuperPower(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powerTypeId");
        int powerId = Integer.parseInt(powerIdParameter);
        service.deleteSuperPower(powerId);
        return "redirect:superPowers";
    }
    
}
