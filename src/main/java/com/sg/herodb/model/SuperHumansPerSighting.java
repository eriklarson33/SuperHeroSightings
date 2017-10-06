/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.model;

import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public class SuperHumansPerSighting {
    
    String sightingId;
    List<String> supers;

    public String getSightingId() {
        return sightingId;
    }

    public void setSightingId(String sightingId) {
        this.sightingId = sightingId;
    }

    public List<String> getSupers() {
        return supers;
    }

    public void setSupers(List<String> supers) {
        this.supers = supers;
    }
    
}
