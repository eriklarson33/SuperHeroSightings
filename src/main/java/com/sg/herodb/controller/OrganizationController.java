/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.controller;

import com.sg.herodb.model.Organization;
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
public class OrganizationController {
    
    private ServiceLayer service;
    
    @Inject
    public OrganizationController(ServiceLayer service) {
        this.service = service;
    }

//    @RequestMapping(value="/")
//    public String rootPage (Model model) {
//        return "index";
//    }
    @RequestMapping(value = "/organizations", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {

        // Get all the SuperHumans from the DAO
        List<Organization> organizationList = service.getAllOrganizations();

        // Put the list of Contacts on the Model
        model.addAttribute("organizationList", organizationList);

        // Return the logical name of our View component
        return "organizations";
    }
    
    @RequestMapping(value = "/showAllOrganizations", method = RequestMethod.GET)
    public String showAllOrganizationsSearch(Model model) {

        // Get all the SuperHumans from the DAO
        List<Organization> organizationList = service.getAllOrganizations();

        // Put the list of Contacts on the Model
        model.addAttribute("organizationList", organizationList);

        // Return the logical name of our View component
        return "redirect:organizations";
    }
    
    @RequestMapping(value = "/searchOrgById", method = RequestMethod.GET)
    public String showOrgById(HttpServletRequest request, Model model) {
        // Get the id of the desired Super Human
        String searchCriteria = request.getParameter("searchCriteria");
        int orgId = Integer.parseInt(searchCriteria);
        // Get all the SuperHumans from the DAO
        Organization organizationList = service.getOrganizationById(orgId);

        // Put the list of Contacts on the Model
        model.addAttribute("organizationList", organizationList);

        // Return the logical name of our View component
        return "redirect:organizations";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrg(HttpServletRequest request) {
        // grab the incoming values from the form and create a new SuperHuman object
        Organization org = new Organization();
        org.setOrganizationName(request.getParameter("orgName"));
        org.setDescription(request.getParameter("description"));
        org.setStreet(request.getParameter("street"));
        org.setCity(request.getParameter("city"));
        org.setState(request.getParameter("state"));
        org.setZip("zip");
        org.setPhone("phone");

        // persist the new Organization
        service.addOrganization(org);
        
        return "redirect:organizations";
    }
    
    @RequestMapping(value = "/displayOrgProfile", method = RequestMethod.GET)
    public String displayOrgProfile(HttpServletRequest request, Model model) {
        String orgIdParameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(orgIdParameter);

        // get the profile information of the selected Super
        Organization orgProfile = service.getOrganizationById(orgId);
        
        model.addAttribute("orgProfile", orgProfile);
        
        return "editOrganizationForm";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("orgProfile") Organization organization, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editOrganizationForm";
        }
        
        service.updateOrganization(organization);
        
        return "redirect:organizations";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(organizationIdParameter);
        service.deleteOrganization(orgId);
        return "redirect:organizations";
    }
    
}
