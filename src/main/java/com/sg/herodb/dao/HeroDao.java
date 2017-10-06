/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Organization;
import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperPower;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public interface HeroDao {
    // DAO Interface for SuperHuman class
    public SuperHuman addSuperHuman (SuperHuman superHuman);
    public void deleteSuperHuman (int superHumanId);
    public void updateSuperHuman (SuperHuman superHuman);
    public SuperHuman getSuperHumanById (int superHumanId);
    public List<SuperHuman> getAllSuperHumans();
    public List<SuperHuman> getAllSuperHumansByOrg(int organizationId);
    public List<SuperHuman> getAllSuperHumansBySighting (int sightingId);
    // DAO Interface for Organization class
    public void addOrganization (Organization organization);
    public void deleteOrganization (int organizationId);
    public void updateOrganization (Organization organization);
    public List<Organization> getAllOrganizations();
    public Organization getOrganizationById (int organizationId);
    public List<Organization> getAllOrganizationsBySuperHuman (int superHumanId);
    // DAO Interface for Sighting class
    public void addSighting (Sighting sighting);
    public void deleteSighting (int sightingId);
    public void updateSighting (Sighting sighting);
    public List<Sighting> getAllSightings();
    public List<Sighting> getAllSightingsLimit10();
    public Sighting getSightingById (int sightingId);
    public List<Sighting> getAllSightingsByDate(LocalDate date);
    public List<Sighting> getAllSightingsBySuperHuman (int superHumanId);
    // DAO Interface for SuperPowerclass
    public void addSuperPower (SuperPower superPower);
    public void deleteSuperPower (int powerTypeId);
    public void updateSuperPower (SuperPower superPower);
    public List<SuperPower> getAllSuperPowers();
    public SuperPower getSuperPowerById (int powerTypeId);
    public List<SuperPower> getAllSuperPowerBySuperHumanId (int superHumanId);
    // DAO Interface for Bridge Connections
    public void insertSupersXSightingsConnect(int superId, int sightingId);
    public void deleteSupersSighting(int sightingId);
    public void insertSupersXOrganizationsConnect(int superId, int orgId);
    public void deleteSupersOrganization(int orgId);
    public void insertSupersXPowersConnect(int powerTypeId, int superId);
    public void deleteSupersPowers(int superHumanId);
    
}
