/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public interface SightingDaoInterface {
    
    // DAO Interface for Sighting class
    public void addSighting (Sighting sighting);
    public void deleteSighting (int sightingId);
    public void updateSighting (Sighting sighting);
    public List<Sighting> getAllSightings();
    public List<Sighting> getAllSightingsLimit10();
    public Sighting getSightingById (int sightingId);
    public List<Sighting> getAllSightingsByDate(LocalDate date);
    public List<Sighting> getAllSightingsBySuperHuman (int superHumanId);
    public List<Sighting> findSightingsForSuperHuman(SuperHuman superHuman);
    
}
