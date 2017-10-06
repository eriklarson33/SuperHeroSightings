/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.serviceLayer;

import com.sg.herodb.dao.OrganizationDaoInterface;
import com.sg.herodb.dao.SightingDaoInterface;
import com.sg.herodb.dao.SuperHumanDaoInterface;
import com.sg.herodb.dao.SuperPowerDaoInterface;
import com.sg.herodb.dao.UserDao;
import com.sg.herodb.model.Organization;
import com.sg.herodb.model.PowerBody;
import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperHumansPerOrganization;
import com.sg.herodb.model.SuperHumansPerSighting;
import com.sg.herodb.model.SuperPower;
import com.sg.herodb.model.User;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author eriklarson-laptop
 */

@Service
public class ServiceLayer {
    
    private SuperHumanDaoInterface superHumanDao;
    private OrganizationDaoInterface organizationDao;
    private SightingDaoInterface sightingDao;
    private SuperPowerDaoInterface superPowerDao;
    private UserDao userDao;
    
    @Inject
    public ServiceLayer (SuperHumanDaoInterface superHumanDao,
            OrganizationDaoInterface organizationDao, 
            SightingDaoInterface sightingDao, 
            SuperPowerDaoInterface superPowerDao,
            UserDao userDao) {
        this.superHumanDao = superHumanDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
        this.superPowerDao = superPowerDao;
        this.userDao = userDao;
    }
    
    // DAO Interface for SuperHuman class
    public SuperHuman addSuperHuman (SuperHuman superHuman) {
        return superHumanDao.addSuperHuman(superHuman);
    }
    
    public void deleteSuperHuman (int superHumanId) {
        superHumanDao.deleteSuperHuman(superHumanId);
    }
    
    public void updateSuperHuman (SuperHuman superHuman) {
        superHumanDao.updateSuperHuman(superHuman);
    }
    
    // Update this....  
    // (need to add associatePowersSightingsAndOrgsWithSuperHuman at Service Level
    public SuperHuman getSuperHumanById (int superHumanId) {
        try {
            // get the properties from the super_humans table
            SuperHuman superH = superHumanDao.getSuperHumanById(superHumanId);
            
            // get the organizations for the current SuperHuman & set the list
            superH.setOrganizations(organizationDao.findOrgsForSuperHuman(superH));
            // get the powers for the current SuperHuman & set the list
            superH.setSuperPowers(superPowerDao.findPowersForSuperHuman(superH));
            // get the sightings for the current SuperHuman & set the list
            superH.setSightings(sightingDao.findSightingsForSuperHuman(superH));
            return superH;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    public List<SuperHuman> getAllSuperHumans() {
        List<SuperHuman> sups = superHumanDao.getAllSuperHumans();
        return associatePowersSightingsAndOrgsWithSuperHuman(sups);
    }
    
    public List<SuperHuman> getAllSuperHumansByOrg(int organizationId) {
        List<SuperHuman> sups = superHumanDao.getAllSuperHumansByOrg(organizationId);
        return associatePowersSightingsAndOrgsWithSuperHuman(sups);
    }
    
    public List<SuperHuman> getAllSuperHumansBySighting (int sightingId) {
        List<SuperHuman> sups = superHumanDao.getAllSuperHumansBySighting(sightingId);
        return associatePowersSightingsAndOrgsWithSuperHuman(sups);
    }

    
    // DAO Interface for Organization class
    public void addOrganization (Organization organization) {
        organizationDao.addOrganization(organization);
    }
    
    public void deleteOrganization (int organizationId) {
        // delete superhuman_x_orgs relationship for this superhuman
        superHumanDao.deleteSupersOrganization(organizationId);
        // delete organization
        organizationDao.deleteOrganization(organizationId);
    }
    
    public void updateOrganization (Organization organization) {
        organizationDao.updateOrganization(organization);
    }
    
    public List<Organization> getAllOrganizations() {
        return organizationDao.getAllOrganizations();
    }
    
    public Organization getOrganizationById (int organizationId) {
        return organizationDao.getOrganizationById(organizationId);
    }
    
    public List<Organization> getAllOrganizationsBySuperHuman (int superHumanId) {
        return organizationDao.getAllOrganizationsBySuperHuman(superHumanId);
    }
    
    // DAO Interface for Sighting class
    public void addSighting (Sighting sighting) {
        sightingDao.addSighting(sighting);
    }
    
    public void deleteSighting (int sightingId) {
        // delete the superhuman_x_sightings relationship
        superHumanDao.deleteSupersSighting(sightingId);
        // delete super human
        sightingDao.deleteSighting(sightingId);
    }
    
    public void updateSighting (Sighting sighting) {
        sightingDao.updateSighting(sighting);
    }
    
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }
    
    public List<Sighting> getAllSightingsLimit10() {
        return sightingDao.getAllSightingsLimit10();
    }
    
    public Sighting getSightingById (int sightingId) {
        return sightingDao.getSightingById(sightingId);
    }
    
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        return sightingDao.getAllSightingsByDate(date);
    }
    
    public List<Sighting> getAllSightingsBySuperHuman (int superHumanId) {
        return sightingDao.getAllSightingsBySuperHuman(superHumanId);
    }
    
    // DAO Interface for SuperPowerclass
    public void addSuperPower (SuperPower superPower) {
        superPowerDao.addSuperPower(superPower);
    }
    
    public void deleteSuperPower (int powerTypeId) {
        superPowerDao.deleteSuperPower(powerTypeId);
    }
    
    public void updateSuperPower (SuperPower superPower) {
        superPowerDao.updateSuperPower(superPower);
    }
    
    public List<SuperPower> getAllSuperPowers() {
        return superPowerDao.getAllSuperPowers();
    }
    
    public SuperPower getSuperPowerById (int powerTypeId) {
        return superPowerDao.getSuperPowerById(powerTypeId);
    }
    
    public List<SuperPower> getAllSuperPowerBySuperHumanId (int superHumanId) {
        return superPowerDao.getAllSuperPowerBySuperHumanId(superHumanId);
    }
    
    // DAO Interface for Bridge Connections
    public void insertSupersXSightingsConnect(int superId, int sightingId) {
        superHumanDao.insertSupersXSightingsConnect(superId, sightingId);
    }
    
    public void deleteSupersSighting(int sightingId) {
        superHumanDao.deleteSupersSighting(sightingId);
    }
    
    public void insertSupersXOrganizationsConnect(int superId, int orgId) {
        superHumanDao.insertSupersXOrganizationsConnect(superId, orgId);
    }
    
    public void deleteSupersOrganization(int orgId) {
        superHumanDao.deleteSupersOrganization(orgId);
    }
    
    public void insertSupersXPowersConnect(int powerTypeId, int superId) {
        superHumanDao.insertSupersXPowersConnect(powerTypeId, superId);
    }
    
    public void deleteSupersPowers(int superHumanId) {
        superHumanDao.deleteSupersPowers(superHumanId);
    }
    
    public void superHumanXsightings (SuperHumansPerSighting sups) {
        String eventId = sups.getSightingId();
        int sightingId = Integer.parseInt(eventId);
        //dao.deleteSupersSighting(sightingId);
        deleteSupersSighting(sightingId);
        
        List<String> supers = sups.getSupers();
        for(String sh: supers) {
            int superHumanId = Integer.parseInt(sh);
            insertSupersXSightingsConnect(superHumanId, sightingId);
        }
    }
    
    public void superHumanXorganizations (SuperHumansPerOrganization supers){
        String organizationId = supers.getOrganizationId();
        int orgId = Integer.parseInt(organizationId);
        deleteSupersOrganization(orgId);
        
        List<String> superHumanList = supers.getOrgSupers();
        for(String sh: superHumanList) {
            int superHumanId = Integer.parseInt(sh);
            insertSupersXOrganizationsConnect(superHumanId, orgId);
        }
    }
    
    public void superHumanXsuperPowers(PowerBody p) {
        
        String superId = p.getSuperHumanId();
        int superHumanId = Integer.parseInt(superId);
        deleteSupersPowers(superHumanId);
        List<String> powers = p.getPowers();
        
        for(String power: powers) {
            int powerId = Integer.parseInt(power);
            insertSupersXPowersConnect(powerId, superHumanId);
        }
    }
    
    //Associate Super Human Object Lists Helper Method
    
    public List<SuperHuman> associatePowersSightingsAndOrgsWithSuperHuman(List<SuperHuman> superHumanList) {
        // set the complete list of org ids for each Super Human
        for (SuperHuman currentSH : superHumanList) {
            // add orgs to current Super Human
            currentSH.setOrganizations(organizationDao.findOrgsForSuperHuman(currentSH));
            // add super powers to current Super Human
            currentSH.setSuperPowers(superPowerDao.findPowersForSuperHuman(currentSH));
            // add sightings to current Super Human
            currentSH.setSightings(sightingDao.findSightingsForSuperHuman(currentSH));
        }
        return superHumanList;
    }
    
    // Methods for User Dao
    public User addUser(User newUser) {
        return userDao.addUser(newUser);
    }
    
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }
    
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }
    
    public void deleteUserAuthorities(User user) {
        userDao.deleteUserAuthorities(user);
    }
    
    public void updateUserAuthorities(User user) {
        userDao.updateUserAuthorities(user);
    }
    
}
