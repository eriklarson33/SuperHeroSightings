/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eriklarson-laptop
 */
public class SuperPower {
    
    private int powerTypeId;
    @NotEmpty(message = "You must supply a value for the Name")
    @Length(max = 30, message = "The Name cannot be any longer than 30 characters in length.")
    private String name;
    @NotEmpty(message = "You must supply a value for the Name")
    @Length(max = 100, message = "The Name cannot be any longer than 100 characters in length.")
    private String description;

    public int getPowerTypeId() {
        return powerTypeId;
    }
    
    public void setPowerTypeId(int powerTypeId) {
        this.powerTypeId = powerTypeId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }
    
    
    
}
