/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperPower;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public class SuperPowerDaoTestStubImpl {
    
    List<SuperPower> superPowers= new ArrayList<>();
    
    public void initSuperPowerTestDaoStubImpl() {
        SuperPower spOne = new SuperPower();
        spOne.setName("Green Thumb");
        spOne.setDescription("Gifted in planting and nurturing all plant life.");
        spOne.setPowerTypeId(1);
        superPowers.add(spOne);
        
        SuperPower spTwo = new SuperPower();
        spTwo.setName("Lighting Manipulation");
        spTwo.setDescription("The ability to control and create lighting.");
        spTwo.setPowerTypeId(2);
        superPowers.add(spTwo);
    }

//    @Override
    public void addSuperPower(SuperPower superPower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void deleteSuperPower(int powerTypeId) {
        
    }

//    @Override
    public void updateSuperPower(SuperPower superPower) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<SuperPower> getAllSuperPowers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public SuperPower getSuperPowerById(int powerTypeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<SuperPower> getAllSuperPowerBySuperHumanId(int superHumanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<SuperPower> findPowersForSuperHuman(SuperHuman superHuman) {
        int superId = superHuman.getSuperHumanId();
        if (superId == 2) {
            return superPowers;
        } else {
            return null;
        }
    }
    
}
