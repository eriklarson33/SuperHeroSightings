/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.controller;

import com.sg.herodb.dao.HeroDao;
import com.sg.herodb.model.Organization;
import com.sg.herodb.model.PowerBody;
import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperHumansPerOrganization;
import com.sg.herodb.model.SuperHumansPerSighting;
import com.sg.herodb.model.SuperPower;
import com.sg.herodb.serviceLayer.ServiceLayer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author eriklarson-laptop
 */
@CrossOrigin
@Controller
public class RESTController {

    private HeroDao dao;
    private ServiceLayer service;

    @Inject
    public RESTController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/superHuman/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SuperHuman getSuper(@PathVariable("id") int id) {

        return service.getSuperHumanById(id);
    }

    @RequestMapping(value = "/superHuman", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SuperHuman createSuper(@Valid @RequestBody SuperHuman superHuman) {
        return service.addSuperHuman(superHuman);
    }

// NOTES ON THE ABOVE @RequestMapping(value="/createSuperHuman", method = RequestMethod.POST)
//
//1. This endpoint is mapped to POST requests for the /contact URL pattern.
//2. The @RequestBody annotation lets the Spring Framework know that we expect JSON data 
//        in the request body and that we want Spring to convert the JSON data into 
//        a Contact object for us.
//3. Our code persists the incoming Contact by passing it to the addContact method on the DAO.  
//        It then returns the Contact object returned by the DAO (this Contact object now has a newly 
//        assigned contact id).
//4. The @ResponseBody annotation tells the Spring Framework that we want it to convert 
//        the Contact object returned by our method into JSON format, place it in 
//        the Response Body, and send it to the client.
//5. The @ResponseStatus annotation tells the Spring Framework to return a 401 Created 
//        HTTP status code back with the response.
    @RequestMapping(value = "/superHuman/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuper(@PathVariable("id") int id) {
        service.deleteSuperHuman(id);
    }

    @RequestMapping(value = "/superHuman/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuperHuman(@PathVariable("id") int id, @Valid @RequestBody SuperHuman superHuman) throws UpdateIntegrityException {

        if (id != superHuman.getSuperHumanId()) {
            throw new UpdateIntegrityException("SuperHuman Id on URL must match Super Human Id in submitted data.");
        }
        service.updateSuperHuman(superHuman);
    }

    @RequestMapping(value = "/superHumans", method = RequestMethod.GET)
    @ResponseBody
    public List<SuperHuman> getAllSuperHumans() {
        return service.getAllSuperHumans();
    }

    @RequestMapping(value = "/showSightings", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> showSightingsSearch() {
        // Get all the Sightings from the DAO
        return service.getAllSightings();
    }
    
    @RequestMapping(value = "/showSightingsLimit10", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> showSightingsSearchLimit10() {
        // Get all the Sightings from the DAO
        return service.getAllSightingsLimit10();
    }
    
    @RequestMapping(value = "/deleteSighting/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrentSighting(@PathVariable("id") int id) {
        service.deleteSighting(id);
    }
    
    @RequestMapping(value = "/sightingBySuperId/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> currentSightingsBySuper(@PathVariable("id") int id) {
        //return dao.getAllSightingsBySuperHuman(id);
        return service.getAllSightingsBySuperHuman(id);
    }
    
    @RequestMapping(value = "/sightingsByDate/{date}", method = RequestMethod.GET)
    @ResponseBody
    public List<Sighting> getAllSightingsByDate(@PathVariable("date") String siteDate) {
       LocalDate findDate = LocalDate.parse(siteDate, DateTimeFormatter.ISO_LOCAL_DATE);
        return service.getAllSightingsByDate(findDate);
    }

    @RequestMapping(value = "/showOrganizations", method = RequestMethod.GET)
    @ResponseBody
    public List<Organization> getAllOrganizations() {
        // Get all the Organizations from the DAO
        return service.getAllOrganizations();
    }

    @RequestMapping(value = "/superHumansByOrg/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<SuperHuman> getAllSupersByOrg(@PathVariable("id") int id) {
        return service.getAllSuperHumansByOrg(id);
    }

    @RequestMapping(value = "/superHumansBySite/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<SuperHuman> getAllSupersBySite(@PathVariable("id") int id) {
        return service.getAllSuperHumansBySighting(id);
    }

    @RequestMapping(value = "/showSightingById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Sighting showSightingById(@PathVariable("id") int id) {
        return service.getSightingById(id);
    }

    @RequestMapping(value = "/showOrganizationsById", method = RequestMethod.GET)
    @ResponseBody
    public Organization showOrganizationById(@PathVariable("id") int id) {
        return service.getOrganizationById(id);
    }
    
    @RequestMapping(value = "/showSuperPowers", method = RequestMethod.GET)
    @ResponseBody
    public List<SuperPower> showAllSuperPowers() {
        // Get all the Sightings from the DAO
        return service.getAllSuperPowers();
    }
    
    @RequestMapping(value = "/powersBySuperHuman/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<SuperPower> getAllPowersBySuper(@PathVariable("id") int id) {
        return service.getAllSuperPowerBySuperHumanId(id);
    }
    
    @RequestMapping(value = "/deleteSuperPower/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuperPower(@PathVariable("id") int id) {
        service.deleteSuperPower(id);
    }
    
    //BridgeTAbleMethods:
// UPDATE  
    @RequestMapping(value = "/superHumanXsightings/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSupersSightings(@RequestBody SuperHumansPerSighting s) throws UpdateIntegrityException {
        service.superHumanXsightings(s);
        
//        String eventId = s.getSightingId();
//        int sightingId = Integer.parseInt(eventId);
//        //dao.deleteSupersSighting(sightingId);
//        service.deleteSupersSighting(sightingId);
//        
//        List<String> supers = s.getSupers();
//        for(String sh: supers) {
//            int superHumanId = Integer.parseInt(sh);
//            dao.insertSupersXSightingsConnect(superHumanId, sightingId);
//        }
    }
    
    @RequestMapping(value = "/sighting/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSighting(@PathVariable("id") int id) {
        //dao.deleteSupersSighting(id);
        service.deleteSupersSighting(id);
    }
    
    @RequestMapping(value = "/superHumanXorganizations/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSupersOrganizations(@RequestBody SuperHumansPerOrganization sh) throws UpdateIntegrityException {
        service.superHumanXorganizations(sh);
        //dao.insertSupersXOrganizationsConnect(superId, orgId);
    }
    
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Organization getOrg(@PathVariable("id") int id) {

        return service.getOrganizationById(id);
    }
    
    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("id") int id) {
        service.deleteSupersOrganization(id);
        //dao.deleteSupersOrganization(id);
    }
    
    @RequestMapping(value = "/superHumanXsuperPowers/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSupersAndPowers(@RequestBody PowerBody p) throws UpdateIntegrityException {
        service.superHumanXsuperPowers(p);
//        String superId = p.getSuperHumanId();
//        int superHumanId = Integer.parseInt(superId);
//        dao.deleteSupersPowers(superHumanId);
//        List<String> powers = p.getPowers();
//        
//        for(String power: powers) {
//            int powerId = Integer.parseInt(power);
//            //NOTE: This is not doing a for Each... It is only updating the last one.
//            dao.insertSupersXPowersConnect(powerId, superHumanId);
//        }
    }
    
    @RequestMapping(value = "/superPower/{superHumanId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSuperHumansPower(@PathVariable("superHumanId") int id) {
        //dao.deleteSupersPowers(id);
        service.deleteSupersPowers(id);
    }

    
}

//EXAMPLE FOR CODE ALONG:
//@RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
//@ResponseStatus(HttpStatus.NO_CONTENT)
//public void updateContact(@PathVariable("id") long id, @RequestBody Contact contact) {
//    // favor the path variable over the id in the object if they differ
//    contact.setContactId(id);
//    dao.updateContact(contact);
//}
//From StackOverflow:
//https://stackoverflow.com/questions/11351015/multiple-pathvariable-in-spring-mvc
//@RequestMapping(value = "/{app}/conf/{fnm}", method=RequestMethod.GET)
//public ResponseEntity<?> getConf(@PathVariable("app") String app, @PathVariable("fnm") String fnm) {
//   log.debug("AppName:" + app);
//   log.debug("fName:" + fnm);
//           ...
//           return ...
//
//More Info from StackOverflow:
//https://stackoverflow.com/questions/12893566/passing-multiple-variables-in-requestbody-to-a-spring-mvc-controller-using-ajax

