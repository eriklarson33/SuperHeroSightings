/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Organization;
import com.sg.herodb.model.SuperHuman;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public class OrganizationDaoTestStubImpl {
    
    List<Organization> orgs = new ArrayList<>();
    List<SuperHuman> supers = new ArrayList<>();
    
    public void initOrganizationTestDaoStubImpl() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");
        org.setOrganizationId(1);

        orgs.add(org);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Avengers");
        org2.setDescription("Super Heroes");
        org2.setStreet("123 Super Street");
        org2.setCity("Avengeville");
        org2.setState("MD");
        org2.setZip("12345");
        org2.setPhone("0987654321");
        org2.setOrganizationId(2);
        orgs.add(org2);
        
        SuperHuman superTwo = new SuperHuman();
        superTwo.setSuperHumanName("Jolly Green Giant");
        superTwo.setDescription("Has a GIANT Green Thumb.");
        superTwo.setSuperHumanId(2);
        supers.add(superTwo);
        
        superTwo.setOrganizations(orgs);
    }

//    @Override
    public void addOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void deleteOrganization(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public void updateOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//   @Override
    public List<Organization> getAllOrganizations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public Organization getOrganizationById(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Organization> getAllOrganizationsBySuperHuman(int superHumanId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
    public List<Organization> findOrgsForSuperHuman(SuperHuman superHuman) {
        int superId = superHuman.getSuperHumanId();
        if (superId == 2) {
            return orgs;
        } else {
            return null;
        }
    }
    
}
