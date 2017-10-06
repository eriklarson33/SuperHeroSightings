/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperPower;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public interface SuperPowerDaoInterface {
    
    // DAO Interface for SuperPowerclass
    public void addSuperPower (SuperPower superPower);
    public void deleteSuperPower (int powerTypeId);
    public void updateSuperPower (SuperPower superPower);
    public List<SuperPower> getAllSuperPowers();
    public SuperPower getSuperPowerById (int powerTypeId);
    public List<SuperPower> getAllSuperPowerBySuperHumanId (int superHumanId);
    public List<SuperPower> findPowersForSuperHuman(SuperHuman superHuman);
        
}
