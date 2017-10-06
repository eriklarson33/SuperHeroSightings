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
public class SuperHumansPerOrganization {
    
    String organizationId;
    List<String> orgSupers;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getOrgSupers() {
        return orgSupers;
    }

    public void setOrgSupers(List<String> orgSupers) {
        this.orgSupers = orgSupers;
    }
    
    
    
}
