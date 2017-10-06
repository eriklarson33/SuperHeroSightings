/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.SuperHuman;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eriklarson-laptop
 */
public class SuperHumanTestDaoStubImpl {
    
    List<SuperHuman> supers = new ArrayList<>();
    Map<Integer, SuperHuman> superHumansMap = new HashMap<>();
    
    
    public void initSuperHumanTestDaoStubImpl() {
        SuperHuman superOne = new SuperHuman();
        superOne.setSuperHumanName("John Deer");
        superOne.setDescription("He's a great Farmer.");
        superOne.setSuperHumanId(1);
        //supers.add(superOne);
        superHumansMap.put(1, superOne);
        
        SuperHuman superTwo = new SuperHuman();
        superTwo.setSuperHumanName("Jolly Green Giant");
        superTwo.setDescription("Has a GIANT Green Thumb.");
        superTwo.setSuperHumanId(2);
        //supers.add(superTwo);
        superHumansMap.put(2, superTwo);
    }
    


    public SuperHuman addSuperHuman(SuperHuman superHuman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void deleteSuperHuman(int superHumanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void updateSuperHuman(SuperHuman superHuman) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public SuperHuman getSuperHumanById(int superHumanId) {
        return superHumansMap.get(superHumanId);
    }

//    @Override
    public List<SuperHuman> getAllSuperHumans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<SuperHuman> getAllSuperHumansByOrg(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<SuperHuman> getAllSuperHumansBySighting(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void insertSupersXSightingsConnect(int superId, int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void deleteSupersSighting(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void insertSupersXOrganizationsConnect(int superId, int orgId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void deleteSupersOrganization(int orgId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void insertSupersXPowersConnect(int powerTypeId, int superId) {
//        jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_POWERS,
//                superId,
//                powerTypeId);
    }

//    @Override
    public void deleteSupersPowers(int superHumanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
