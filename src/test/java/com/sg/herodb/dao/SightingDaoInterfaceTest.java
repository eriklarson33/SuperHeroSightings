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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoInterfaceTest {
    
    private SuperHumanDaoInterface superDao;
    private OrganizationDaoInterface organizationDao;
    private SightingDaoInterface sightingDao;
    private SuperPowerDaoInterface superPowerDao;
    
    public SightingDaoInterfaceTest() {
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
     * Test of addSighting method, of class SightingDaoInterface.
     */
    @Test
    public void testAddGetSighting() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sightingDao.addSighting(s);

        Sighting fromDB = sightingDao.getSightingById(s.getSightingId());
        assertEquals(fromDB.getSightingId(), s.getSightingId());
    }

    /**
     * Test of deleteSighting method, of class SightingDaoInterface.
     */
    @Test
    public void testDeleteSighting() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_DATE));
        sightingDao.addSighting(s);

        Sighting fromDB = sightingDao.getSightingById(s.getSightingId());
        assertEquals(fromDB.getSightingId(), s.getSightingId());
        sightingDao.deleteSighting(s.getSightingId());
        assertNull(sightingDao.getSightingById(s.getSightingId()));
    }

    /**
     * Test of updateSighting method, of class SightingDaoInterface.
     */
    @Test
    public void testUpdateSighting() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sightingDao.addSighting(s);

        // update Sighting Name
        s.setName("King Kong's new Jungle Gym");
        sightingDao.updateSighting(s);

        Sighting updatedSite = sightingDao.getSightingById(s.getSightingId());
        assertEquals(updatedSite.getName(), s.getName());
    }

    /**
     * Test of getAllSightings method, of class SightingDaoInterface.
     */
    @Test
    public void testGetAllSightings() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sightingDao.addSighting(s);

        List<Sighting> fromDB = sightingDao.getAllSightings();
        Sighting dataBaseSite = sightingDao.getSightingById(s.getSightingId());

        assertNotNull(fromDB);
        assertEquals(s.getName(), dataBaseSite.getName());
    }

    /**
     * Test of getAllSightingsLimit10 method, of class SightingDaoInterface.
     */
    @Test
    public void testGetAllSightingsLimit10() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sightingDao.addSighting(s);

        List<Sighting> fromDB = sightingDao.getAllSightingsLimit10();
        Sighting dataBaseSite = sightingDao.getSightingById(s.getSightingId());

        assertNotNull(fromDB);
        assertEquals(s.getName(), dataBaseSite.getName());
    }

    /**
     * Test of getSightingById method, of class SightingDaoInterface.
     */
//    @Test
//    public void testGetSightingById() {
    // See testAddGetSighting()
//    }

    /**
     * Test of getAllSightingsByDate method, of class SightingDaoInterface.
     */
    @Test
    public void testGetAllSightingsByDate() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sightingDao.addSighting(s);

        LocalDate siteDate = LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE);
        List<Sighting> fromMethod = sightingDao.getAllSightingsByDate(siteDate);

        for (Sighting site : fromMethod) {
            String name = site.getName();
            int siteId = site.getSightingId();
            String description = site.getDescription();
            System.out.println("Name: " + name + " ");
            System.out.println("ID: " + siteId + " ");
            System.out.println("Description: " + description + " ");

        Sighting updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getName(), s.getName());
        }
    }

    /**
     * Test of getAllSightingsBySuperHuman method, of class SightingDaoInterface.
     */
    @Test
    public void testGetAllSightingsBySuperHuman() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sightingDao.addSighting(s);
        // Get the Sighting Id from the DB
        Sighting fromDBsite = sightingDao.getSightingById(s.getSightingId());
        int sightingId = fromDBsite.getSightingId();

        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);
        // Get the Super Human Id from the DB
        SuperHuman fromDBsuper = superDao.getSuperHumanById(sh.getSuperHumanId());
        int superHumanId = fromDBsuper.getSuperHumanId();

        // Connect the SuperHuman with the Sighting at the Bridge Table
        superDao.insertSupersXSightingsConnect(superHumanId, sightingId);

        List<Sighting> fromMethod = sightingDao.getAllSightingsBySuperHuman(superHumanId);
        Sighting updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getSightingId(), sightingId);
    }
    
    /**
     * Test of findSightingsForSuperHuman method, of class SightingDaoInterface.
     */
    @Test
    public void testFindSightingsForSuperHuman() {
        Sighting s = new Sighting();
        s.setName("Empire State of Mind");
        s.setDescription("Battle at the Empire State Building");
        s.setStreet("350 5th Ave.");
        s.setCity("New York");
        s.setState("NY");
        s.setZip("10118");
        s.setLatitude(new BigDecimal("40.75"));
        s.setLongitude(new BigDecimal("-73.99"));
        s.setSightingDate(LocalDate.parse("2015-10-12",
                DateTimeFormatter.ISO_LOCAL_DATE));
        sightingDao.addSighting(s);
        // Get the Sighting Id from the DB
        Sighting fromDBsite = sightingDao.getSightingById(s.getSightingId());
        int sightingId = fromDBsite.getSightingId();

        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);
        // Get the Super Human Id from the DB
        SuperHuman fromDBsuper = superDao.getSuperHumanById(sh.getSuperHumanId());
        int superHumanId = fromDBsuper.getSuperHumanId();

        // Connect the SuperHuman with the Sighting at the Bridge Table
        superDao.insertSupersXSightingsConnect(superHumanId, sightingId);

        List<Sighting> fromMethod = sightingDao.findSightingsForSuperHuman(fromDBsuper);
        Sighting updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getSightingId(), sightingId);
    }
}
