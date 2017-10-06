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
public class SuperPowerDaoInterfaceTest {
    
    private SuperHumanDaoInterface superDao;
    private OrganizationDaoInterface organizationDao;
    private SightingDaoInterface sightingDao;
    private SuperPowerDaoInterface superPowerDao;
    
    public SuperPowerDaoInterfaceTest() {
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
     * Test of addSuperPower method, of class SuperPowerDaoInterface.
     */
    @Test
    public void testAddGetSuperPower() {
        SuperPower sp = new SuperPower();
        sp.setName("Green Thumb");
        sp.setDescription("Gifted in planting and nurturing all plant life.");

        superPowerDao.addSuperPower(sp);

        SuperPower fromDB = superPowerDao.getSuperPowerById(sp.getPowerTypeId());
        assertEquals(fromDB.getPowerTypeId(), sp.getPowerTypeId());
    }

    /**
     * Test of deleteSuperPower method, of class SuperPowerDaoInterface.
     */
    @Test
    public void testDeleteSuperPower() {
        SuperPower sp = new SuperPower();
        sp.setName("Green Thumb");
        sp.setDescription("Gifted in planting and nurturing all plant life.");
        superPowerDao.addSuperPower(sp);

        SuperPower fromDB = superPowerDao.getSuperPowerById(sp.getPowerTypeId());
        assertEquals(fromDB.getPowerTypeId(), sp.getPowerTypeId());
        superPowerDao.deleteSuperPower(sp.getPowerTypeId());
        assertNull(superPowerDao.getSuperPowerById(sp.getPowerTypeId()));
    }

    /**
     * Test of updateSuperPower method, of class SuperPowerDaoInterface.
     */
    @Test
    public void testUpdateSuperPower() {
        SuperPower sp = new SuperPower();
        sp.setName("Green Thumb");
        sp.setDescription("Gifted in planting and nurturing all plant life.");

        superPowerDao.addSuperPower(sp);

        // update Super Power
        sp.setName("Farmer's Delight");
        superPowerDao.updateSuperPower(sp);

        SuperPower updatedSP = superPowerDao.getSuperPowerById(sp.getPowerTypeId());
        assertEquals(updatedSP.getName(), sp.getName());
    }

    /**
     * Test of getAllSuperPowers method, of class SuperPowerDaoInterface.
     */
    @Test
    public void testGetAllSuperPowers() {
        SuperPower sp = new SuperPower();
        sp.setName("Green Thumb");
        sp.setDescription("Gifted in planting and nurturing all plant life.");
        superPowerDao.addSuperPower(sp);

        List<SuperPower> fromDB = superPowerDao.getAllSuperPowers();
        SuperPower dataBasePower = superPowerDao.getSuperPowerById(sp.getPowerTypeId());

        assertNotNull(fromDB);
        assertEquals(sp.getName(), dataBasePower.getName());
    }

    /**
     * Test of getSuperPowerById method, of class SuperPowerDaoInterface.
     */
//    @Test
//    public void testGetSuperPowerById() {
    // See testUpdateSuperPower()
//    }

    /**
     * Test of getAllSuperPowerBySuperHumanId method, of class SuperPowerDaoInterface.
     */
    @Test
    public void testGetAllSuperPowerBySuperHumanId() {
        SuperPower sp = new SuperPower();
        sp.setName("Green Thumb");
        sp.setDescription("Gifted in planting and nurturing all plant life.");
        superPowerDao.addSuperPower(sp);
        SuperPower fromDBpower = superPowerDao.getSuperPowerById(sp.getPowerTypeId());
        int powerId = fromDBpower.getPowerTypeId();

        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deere");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);
        SuperHuman fromDBsuper = superDao.getSuperHumanById(sh.getSuperHumanId());
        int superHumanId = fromDBsuper.getSuperHumanId();

        // Connect the SuperHuman with the SuperPowers at the Bridge Table
        superDao.insertSupersXPowersConnect(powerId, superHumanId);

        List<SuperPower> fromMethod = superPowerDao.getAllSuperPowerBySuperHumanId(superHumanId);
        SuperPower updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getName(), sp.getName());
    }

    /**
     * Test of findPowersForSuperHuman method, of class SuperPowerDaoInterface.
     */
    @Test
    public void testFindPowersForSuperHuman() {
        SuperPower sp = new SuperPower();
        sp.setName("Green Thumb");
        sp.setDescription("Gifted in planting and nurturing all plant life.");
        superPowerDao.addSuperPower(sp);
        SuperPower fromDBpower = superPowerDao.getSuperPowerById(sp.getPowerTypeId());
        int powerId = fromDBpower.getPowerTypeId();

        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);
        SuperHuman fromDBsuper = superDao.getSuperHumanById(sh.getSuperHumanId());
        int superHumanId = fromDBsuper.getSuperHumanId();

        // Connect the SuperHuman with the SuperPowers at the Bridge Table
        superDao.insertSupersXPowersConnect(powerId, superHumanId);

        List<SuperPower> fromMethod = superPowerDao.findPowersForSuperHuman(fromDBsuper);
        SuperPower updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getName(), sp.getName());
    }
    
}
