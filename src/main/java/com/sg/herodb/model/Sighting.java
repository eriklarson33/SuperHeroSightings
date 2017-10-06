 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eriklarson-laptop
 */
public class Sighting {
    
    private int sightingId;
    @NotEmpty(message = "You must supply a value for the Name")
    @Length(max = 30, message = "The Name cannot be any longer than 30 characters in length.")
    private String name;
    @NotEmpty(message = "You must supply a value for the Description.")
    @Length(max = 100, message = "The Description cannot be any longer than 100 characters in length.")
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
    @NotNull(message = "You must supply a value for Latitude.")
    @Digits(integer=3, fraction=4, message = "The latitude can't have an integer greater than 3 digits, or decimal greater than 4 digits.")
    private BigDecimal latitude;
    @NotNull(message = "You must supply a value for Longitude.")
    @Digits(integer=3, fraction=4, message = "The longitude can't have an integer greater than 3 digits, or decimal greater than 4 digits.")
    private BigDecimal longitude;
    
    private LocalDate sightingDate;
    
//    public Sighting (int sightingId, String name, String description, String street, 
//            String city, String state, String zip, String latitude, String longitude) {
//        this.sightingId = sightingId;
//        this.name = name;
//        this.description = description;
//        this.street = street;
//        this.city = city;
//        this.state = state;
//        this.zip = zip;
//        BigDecimal setLat = new BigDecimal(latitude);
//        this.latitude = setLat;
//        BigDecimal setLong = new BigDecimal(longitude);
//        this.longitude = setLong;
//    }
            

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    public void setLatitude(String latitude) {
        BigDecimal setLat = new BigDecimal(latitude);
        this.latitude = setLat;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    
    public void setLongitude(String longitude) {
        BigDecimal setLong = new BigDecimal(longitude);
        this.longitude = setLong;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }
    
}