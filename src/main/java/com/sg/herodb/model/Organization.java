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
public class Organization {
    
    private int organizationId;
    @NotEmpty(message = "You must supply a value for the Name")
    @Length(max = 40, message = "The Name cannot be any longer than 40 characters in length.")
    private String organizationName;
    @Length(max = 175, message = "The Description cannot be any longer than 175 characters in length.")
    private String description;
    @NotEmpty(message = "You must supply a value for the Street.")
    @Length(max = 30, message = "The Street Address cannot be any longer than 30 characters in length.")
    private String street;
    @NotEmpty(message = "You must supply a value for City.")
    @Length(max = 20, message = "The City cannot be any longer than 20 characters in length.")
    private String city;
    @NotEmpty(message = "You must supply a value for State.")
    @Length(max = 20, message = "The State cannot be any longer than 20 characters in length.")
    private String state;
    @NotEmpty(message = "You must supply a value for Zip.")
    @Length(max = 20, message = "The Zip cannot be any longer than 20 characters in length.")
    private String zip;
    @NotEmpty(message = "You must supply a phone number.")
    @Length(max = 20, message = "You must supply a valid phone number.")
    private String phone;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    
}
