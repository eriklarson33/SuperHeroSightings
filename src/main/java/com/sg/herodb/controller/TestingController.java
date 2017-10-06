
package com.sg.herodb.controller;

import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.serviceLayer.ServiceLayer;
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
public class TestingController {
    
    private ServiceLayer service;
    
    @Inject
    public TestingController (ServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping (value = "/testDropDownBox")
    public String displayTestPage (Model model) {
        return "testDropDownBox";
    }
    
//    //NOTE: Refer to LESSON 3d: Factorizor Spring MVC Code-Along
//    @RequestMapping (value = "/searchHeroes", method = RequestMethod.GET)
//    public String decideSearchParameter (HttpServletRequest request, Map<String, Object> model) {
//        
//        String searchResult = request.getParameter("search");
//        String searchCriteria = request.getParameter("searchCriteria");
//        Integer criteria = Integer.parseInt(searchCriteria);
//        
//        if ("showAll".equals(searchResult)) {
//            List superHumanList = service.getAllSuperHumans();
//            
//        } else if ("findSHbyId".equals(searchResult)) {
//            SuperHuman superHumanList = service.getSuperHumanById(criteria);
//        }
//        
//        model.put("superHumanList", superHumanList);
//        
//        return "testDropDownBox";
//    }
    
//    @RequestMapping(value = "/searchSupers", method = RequestMethod.GET)
//    public String getSearchRequest(HttpServletRequest request, Model model) {
//
//        String search = request.getParameter("requestedSearch");
//
//        String criteria = request.getParameter("searchCriteria");
//
//        String ret = "";
//
//        if ((search).equals("findById")) {
//            // Get the Super Human Id
//            int superId = Integer.parseInt(criteria);
//
//            // Get the SuperHumans from the DAO
//        //SuperHuman superHuman = dao.getSuperHumanById(superId);
//        SuperHuman superHuman = service.getSuperHumanById(superId);
//        
//        // Add the superHuman to a list
//        List superHumanList = new ArrayList();
//        superHumanList.add(superHuman);
//            
//             // Put the list of Contacts on the Model
//        model.addAttribute("superHumanList", superHumanList);
//
//            
//        } else if ((search).equals("findByOrg")) {
//            // Get the Org Id
//            int orgId = Integer.parseInt(criteria);
//
//            //List<SuperHuman> superHumanList = dao.getAllSuperHumansByOrg(orgId);
//            List<SuperHuman> superHumanList = service.getAllSuperHumansByOrg(orgId);
//
//            model.addAttribute("superHumanList", superHumanList);
//            
//        } else if ((search).equals("findBySighting")) {
//            // Get the Sighting Id
//            int sightingId = Integer.parseInt(criteria);
//            
//            //Get the List of Super Humans
//            List<SuperHuman> superHumanList = service.getAllSuperHumansBySighting(sightingId);
//            
//            //  Add the list of supers to the model
//            model.addAttribute("superHumanList", superHumanList);
//            
//        } else if ((search).equals("showAllSupers")) {
//            // Get all the SuperHumans from the DAO
//            //List<SuperHuman> superHumanList = dao.getAllSuperHumans();
//            List<SuperHuman> superHumanList = service.getAllSuperHumans();
//
//            // Put the list of Contacts on the Model
//            model.addAttribute("superHumanList", superHumanList);
//        }
//        // Return the logical name of our View component
//        return "testDropDownBox";
//    }
    
//    @RequestMapping(value = "/displayProfile", method = RequestMethod.GET)
//    public String displaySuperProfile(HttpServletRequest request, Model model) {
//        String superIdParameter = request.getParameter("superId");
//        int superId = Integer.parseInt(superIdParameter);
//
//        // get the profile information of the selected Super
//        //SuperHuman superProfile = dao.getSuperHumanById(superId);
//        SuperHuman superProfile = service.getSuperHumanById(superId);
//
//        model.addAttribute("superProfile", superProfile);
//
//        return "editSuperHuman";
//    }
//    
    
    
}
