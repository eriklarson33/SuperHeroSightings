/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.serviceLayer;

import com.sg.herodb.dao.OrganizationDaoTestStubImpl;
import com.sg.herodb.dao.SightingDaoTestStubImpl;
import com.sg.herodb.dao.SuperHumanTestDaoStubImpl;
import com.sg.herodb.dao.SuperPowerDaoTestStubImpl;
import com.sg.herodb.model.Organization;
import com.sg.herodb.model.PowerBody;
import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperPower;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author eriklarson-laptop
 */
public class ServiceLayerTest {

    SuperHumanTestDaoStubImpl superHumanDao;
    OrganizationDaoTestStubImpl organizationDao;
    SightingDaoTestStubImpl sightingDao;
    SuperPowerDaoTestStubImpl superPowerDao;

    public ServiceLayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.superHumanDao = new SuperHumanTestDaoStubImpl();
        this.organizationDao = new OrganizationDaoTestStubImpl();
        this.sightingDao = new SightingDaoTestStubImpl();
        this.superPowerDao = new SuperPowerDaoTestStubImpl();
        
        superHumanDao.initSuperHumanTestDaoStubImpl();
        organizationDao.initOrganizationTestDaoStubImpl();
        superPowerDao.initSuperPowerTestDaoStubImpl();
        sightingDao.initSightingTestDaoStubImpl();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperHuman method, of class ServiceLayer.
     */
    @Test
    public void testAddSuperHuman() {
    }

    /**
     * Test of deleteSuperHuman method, of class ServiceLayer.
     */
    @Test
    public void testDeleteSuperHuman() {
    }

    /**
     * Test of updateSuperHuman method, of class ServiceLayer.
     */
    @Test
    public void testUpdateSuperHuman() {
    }

    /**
     * Test of getSuperHumanById method, of class ServiceLayer.
     */
    @Test
    public void testGetSuperHumanById() {

            // get the properties from the super_humans table
            SuperHuman superH = superHumanDao.getSuperHumanById(2);
            assertEquals("Jolly Green Giant", superH.getSuperHumanName());

            // get the organizations for the current SuperHuman & set the list
            superH.setOrganizations(organizationDao.findOrgsForSuperHuman(superH));

            List<Organization> fromDB = organizationDao.findOrgsForSuperHuman(superH);
            assertNotNull(fromDB);
            assertTrue(fromDB.size() == 2);

            // get the powers for the current SuperHuman & set the list
            superH.setSuperPowers(superPowerDao.findPowersForSuperHuman(superH));
            
            List<SuperPower> fromPowerDB = superPowerDao.findPowersForSuperHuman(superH);
            assertNotNull(fromPowerDB);
            assertTrue(fromPowerDB.size() == 2);
            
            // get the sightings for the current SuperHuman & set the list
            superH.setSightings(sightingDao.findSightingsForSuperHuman(superH));
            
            List<Sighting> fromSightingDB = sightingDao.findSightingsForSuperHuman(superH);
            assertNotNull(fromSightingDB);
            assertTrue(fromSightingDB.size() == 2);
    }


//
//    /**
//     * Test of associatePowersSightingsAndOrgsWithSuperHuman method, of class
//     * ServiceLayer.
//     */
//    @Test
//    public void testAssociatePowersSightingsAndOrgsWithSuperHuman() {
    // Uses same methods as ttestGetSuperHumanById(), please refer to that.
//    }

}
