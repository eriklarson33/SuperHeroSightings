/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Organization;
import com.sg.herodb.model.SuperHuman;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public interface OrganizationDaoInterface {
    
    // DAO Interface for Organization class
    public void addOrganization (Organization organization);
    public void deleteOrganization (int organizationId);
    public void updateOrganization (Organization organization);
    public List<Organization> getAllOrganizations();
    public Organization getOrganizationById (int organizationId);
    public List<Organization> getAllOrganizationsBySuperHuman (int superHumanId);
    public List<Organization> findOrgsForSuperHuman(SuperHuman superHuman);
    
}
