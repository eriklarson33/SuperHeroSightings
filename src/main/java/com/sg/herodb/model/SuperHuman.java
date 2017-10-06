/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.model;

import java.util.Collections;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eriklarson-laptop
 */
public class SuperHuman {
    
    private int superHumanId;
    @NotEmpty(message = "You must supply a value for the Super Being's Name.")
    @Length(max = 20, message = "The Name cannot be any longer than 20 characters in length." )
    private String superHumanName;
    @NotEmpty (message = "You must supply a brief description about this Super Human.")
    @Length (max = 100, message = "The description cannot be more than 100 Characters long.")
    private String description;
    private List<Sighting> sightings;
    private List<Organization> organizations;
    private List<SuperPower> superPowers;
    

    public int getSuperHumanId() {
        return superHumanId;
    }

    public void setSuperHumanId(int superHumanId) {
        this.superHumanId = superHumanId;
    }

    public String getSuperHumanName() {
        return superHumanName;
    }

    public void setSuperHumanName(String superHumanName) {
        this.superHumanName = superHumanName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Sighting> getSightings() {
        // if null, return an empty list to avoid NullPointerExceptions
//        if (sightings == null) {
//            sightings = Collections.emptyList();
//        }
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    public List<Organization> getOrganizations() {
        // if null, return an empty list to avoid NullPointerExceptions
//        if (null == organizations) {
//            organizations = Collections.emptyList();
//        }
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<SuperPower> getSuperPowers() {
        // if null, return an empty list to avoid NullPointerExceptions
//        if (superPowers == null) {
//            superPowers = Collections.emptyList();
//        }
        return superPowers;
    }

    public void setSuperPowers(List<SuperPower> superPowers) {
        this.superPowers = superPowers;
    }


    
}
