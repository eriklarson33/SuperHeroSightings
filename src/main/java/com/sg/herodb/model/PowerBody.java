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
public class PowerBody {
    
    String superHumanId;
    List<String> powers;

    public String getSuperHumanId() {
        return superHumanId;
    }

    public void setSuperHumanId(String superHumanId) {
        this.superHumanId = superHumanId;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }
    
    
    
}
