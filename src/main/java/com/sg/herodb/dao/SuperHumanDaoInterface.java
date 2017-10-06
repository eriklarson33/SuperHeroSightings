/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.SuperHuman;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public interface SuperHumanDaoInterface {
    
    // DAO Interface for SuperHuman class
    public SuperHuman addSuperHuman (SuperHuman superHuman);
    public void deleteSuperHuman (int superHumanId);
    public void updateSuperHuman (SuperHuman superHuman);
    public SuperHuman getSuperHumanById (int superHumanId);
    public List<SuperHuman> getAllSuperHumans();
    public List<SuperHuman> getAllSuperHumansByOrg(int organizationId);
    public List<SuperHuman> getAllSuperHumansBySighting (int sightingId);
    
    // DAO Interface for Bridge Connections
    public void insertSupersXSightingsConnect(int superId, int sightingId);
    public void deleteSupersSighting(int sightingId);
    public void insertSupersXOrganizationsConnect(int superId, int orgId);
    public void deleteSupersOrganization(int orgId);
    public void insertSupersXPowersConnect(int powerTypeId, int superId);
    public void deleteSupersPowers(int superHumanId);
    
}
