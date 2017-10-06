/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public class SightingDaoTestStubImpl {
    
    List<Sighting> sightings = new ArrayList<>();
    
    
    public void initSightingTestDaoStubImpl() {
        Sighting sOne = new Sighting();
        sOne.setName("Empire State of Mind");
        sOne.setDescription("Battle at the Empire State Building");
        sOne.setStreet("350 5th Ave.");
        sOne.setCity("New York");
        sOne.setState("NY");
        sOne.setZip("10118");
        sOne.setLatitude(new BigDecimal("40.75"));
        sOne.setLongitude(new BigDecimal("-73.99"));
        sOne.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sOne.setSightingId(1);
        sightings.add(sOne);
        
        Sighting sTwo = new Sighting();
        sTwo.setName("Avengers");
        sTwo.setDescription("Avengers at Comic-con.");
        sTwo.setStreet("123 Super Street");
        sTwo.setCity("Avengeville");
        sTwo.setState("MD");
        sTwo.setZip("12345");
        sOne.setLatitude(new BigDecimal("40.75"));
        sOne.setLongitude(new BigDecimal("-73.99"));
        sOne.setSightingDate(LocalDate.parse("2017-04-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sOne.setSightingId(2);
        sightings.add(sTwo);
        
    }

//    @Override
    public void addSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void deleteSighting(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void updateSighting(Sighting sighting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Sighting> getAllSightings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Sighting> getAllSightingsLimit10() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public Sighting getSightingById(int sightingId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Sighting> getAllSightingsBySuperHuman(int superHumanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Sighting> findSightingsForSuperHuman(SuperHuman superHuman) {
        int superId = superHuman.getSuperHumanId();
        if (superId == 2) {
            return sightings;
        } else {
            return null;
        }
    }
    
}
