/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Organization;
import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperPower;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author eriklarson-laptop
 */
public class OrganizationDaoInterfaceTest {
    
    private SuperHumanDaoInterface superDao;
    private OrganizationDaoInterface organizationDao;
    private SightingDaoInterface sightingDao;
    private SuperPowerDaoInterface superPowerDao;
    
    public OrganizationDaoInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        superDao = ctx.getBean("superHumanDao", SuperHumanDaoInterface.class);
        organizationDao = ctx.getBean("organizationDao", OrganizationDaoInterface.class);
        sightingDao = ctx.getBean("sightingDao", SightingDaoInterface.class);
        superPowerDao = ctx.getBean("superPowerDao", SuperPowerDaoInterface.class);

        // delete all Super Humans
        List<SuperHuman> superHumans = superDao.getAllSuperHumans();
        for (SuperHuman currentSuperHuman : superHumans) {
            superDao.deleteSuperHuman(currentSuperHuman.getSuperHumanId());
        }
        //delete all organizations
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrg : organizations) {
            organizationDao.deleteOrganization(currentOrg.getOrganizationId());
        }
        // delete all sightings
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
        // delete all superPowers
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers();
        for (SuperPower currentPower : superPowers) {
            superPowerDao.deleteSuperPower(currentPower.getPowerTypeId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganization method, of class OrganizationDaoInterface.
     */
    @Test
    public void testAddGetOrganization() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");

        organizationDao.addOrganization(org);

        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao.getOrganizationId(), org.getOrganizationId());
    }

    /**
     * Test of deleteOrganization method, of class OrganizationDaoInterface.
     */
    @Test
    public void testDeleteOrganization() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");

        organizationDao.addOrganization(org);

        int x = org.getOrganizationId();

        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao.getOrganizationId(), org.getOrganizationId());
        organizationDao.deleteOrganization(x);
        assertNull(organizationDao.getOrganizationById(x));
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoInterface.
     */
    @Test
    public void testUpdateOrganization() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");

        organizationDao.addOrganization(org);

        // update Organization Name
        org.setOrganizationName("Farmers United");
        organizationDao.updateOrganization(org);

        Organization updatedOrg = organizationDao.getOrganizationById(org.getOrganizationId());
        assertEquals(updatedOrg.getOrganizationName(), org.getOrganizationName());
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoInterface.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");

        organizationDao.addOrganization(org);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Avengers");
        org2.setDescription("Super Heroes");
        org2.setStreet("123 Super Street");
        org2.setCity("Avengeville");
        org2.setState("MD");
        org2.setZip("12345");
        org2.setPhone("0987654321");

        organizationDao.addOrganization(org2);

        List<Organization> fromDB = organizationDao.getAllOrganizations();
        Organization dataBaseOrg2 = organizationDao.getOrganizationById(org2.getOrganizationId());

        assertNotNull(fromDB);
        assertEquals(dataBaseOrg2.getOrganizationName(), org2.getOrganizationName());
    }

    /**
     * Test of getOrganizationById method, of class OrganizationDaoInterface.
     */
//    @Test
//    public void testGetOrganizationById() {
    // See testAddGetOrganization()
//    }

    /**
     * Test of getAllOrganizationsBySuperHuman method, of class OrganizationDaoInterface.
     */
    @Test
    public void testGetAllOrganizationsBySuperHuman() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");

        organizationDao.addOrganization(org);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Avengers");
        org2.setDescription("Super Heroes");
        org2.setStreet("123 Super Street");
        org2.setCity("Avengeville");
        org2.setState("MD");
        org2.setZip("12345");
        org2.setPhone("0987654321");

        organizationDao.addOrganization(org2);

        List<Organization> fromDB = organizationDao.getAllOrganizations();
        Organization dataBaseOrg2 = organizationDao.getOrganizationById(org2.getOrganizationId());

        assertNotNull(fromDB);
        assertEquals(dataBaseOrg2.getOrganizationName(), org2.getOrganizationName());
        
        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        
        // Add Organizations associated with the Super Human
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org2);
        sh.setOrganizations(orgs);
        superDao.addSuperHuman(sh);
        
        SuperHuman updatedSH = superDao.getSuperHumanById(sh.getSuperHumanId());
        assertEquals(updatedSH.getSuperHumanName(), sh.getSuperHumanName());

        int superHumanId = updatedSH.getSuperHumanId();
        List<Organization> updatedOrgList = organizationDao.getAllOrganizationsBySuperHuman(superHumanId);
        for (Organization organization : updatedOrgList) {
            String name = organization.getOrganizationName();
            int orgId = organization.getOrganizationId();
            String description = organization.getDescription();
            assertEquals(name, "Avengers");
        }
    }
    
    /**
     * Test of findOrgsForSuperHuman method, of class OrganizationDaoInterface.
     */
    @Test
    public void testFindOrgsForSuperHuman() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");

        organizationDao.addOrganization(org);
        
        Organization org2 = new Organization();
        org2.setOrganizationName("Avengers");
        org2.setDescription("Super Heroes");
        org2.setStreet("123 Super Street");
        org2.setCity("Avengeville");
        org2.setState("MD");
        org2.setZip("12345");
        org2.setPhone("0987654321");

        organizationDao.addOrganization(org2);

        List<Organization> fromDB = organizationDao.getAllOrganizations();
        Organization dataBaseOrg2 = organizationDao.getOrganizationById(org2.getOrganizationId());

        assertNotNull(fromDB);
        assertEquals(dataBaseOrg2.getOrganizationName(), org2.getOrganizationName());
        
        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        
        // Add Organizations associated with the Super Human
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org2);
        sh.setOrganizations(orgs);
        superDao.addSuperHuman(sh);
        
        SuperHuman updatedSH = superDao.getSuperHumanById(sh.getSuperHumanId());
        assertEquals(updatedSH.getSuperHumanName(), sh.getSuperHumanName());

        int superHumanId = updatedSH.getSuperHumanId();
        List<Organization> updatedOrgList = organizationDao.findOrgsForSuperHuman(updatedSH);
        for (Organization organization : updatedOrgList) {
            String name = organization.getOrganizationName();
            int orgId = organization.getOrganizationId();
            String description = organization.getDescription();
            assertEquals(name, "Avengers");
        }
    }
    
}
