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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author eriklarson-laptop
 */
public class SuperHumanDaoInterfaceTest {

    private SuperHumanDaoInterface superDao;
    private OrganizationDaoInterface organizationDao;
    private SightingDaoInterface sightingDao;
    private SuperPowerDaoInterface superPowerDao;

    public SuperHumanDaoInterfaceTest() {
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
     * Test of setJdbcTemplate method, of class HeroDaoJDBCTemplateImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
        System.out.println("setJdbcTemplate");
        JdbcTemplate jdbcTemplate = null;
        SuperHumanDaoImpl instance = new SuperHumanDaoImpl();
        instance.setJdbcTemplate(jdbcTemplate);
    }

    /**
     * Test of addSuperHuman method, of class SuperHumanDaoInterface.
     */
//    @Test
//    public void testAddSuperHuman() {
//    }
    /**
     * Test of deleteSuperHuman method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testDeleteSuperHuman() {
        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);
        SuperHuman fromDB = superDao.getSuperHumanById(sh.getSuperHumanId());
        assertEquals(fromDB.getSuperHumanName(), sh.getSuperHumanName());
        // Delete Super Human
        superDao.deleteSuperHuman(fromDB.getSuperHumanId());
        assertNull(superDao.getSuperHumanById(fromDB.getSuperHumanId()));
    }

    /**
     * Test of updateSuperHuman method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testUpdateSuperHuman() {
        // First Test creates & updates a Super Human without any powers, orgs, sightings
        // ===============================================================================
        // Create New Super Human
        SuperHuman shOne = new SuperHuman();
        shOne.setSuperHumanName("John Deer");
        shOne.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(shOne);

        // Update Super Human Name
        shOne.setSuperHumanName("Jolly Green Giant");
        superDao.updateSuperHuman(shOne);

        SuperHuman updatedSHOne = superDao.getSuperHumanById(shOne.getSuperHumanId());
        assertEquals(updatedSHOne.getSuperHumanName(), shOne.getSuperHumanName());

        // delete all Super Humans
        List<SuperHuman> superHumans = superDao.getAllSuperHumans();
        for (SuperHuman currentSuperHuman : superHumans) {
            superDao.deleteSuperHuman(currentSuperHuman.getSuperHumanId());
        }

        // Second Test inlcudes powers, orgs, and sighting lists
        //========================================================
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");

        organizationDao.addOrganization(org);

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

        SuperPower spOne = new SuperPower();
        spOne.setName("Power Of One");
        spOne.setDescription("Will never Change");
        superPowerDao.addSuperPower(spOne);

        SuperPower sp = new SuperPower();
        sp.setName("Green Thumb");
        sp.setDescription("Gifted in planting and nurturing all plant life.");
        superPowerDao.addSuperPower(sp);

        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deere");
        sh.setDescription("He's a great Farmer.");
        // Add Organizations associated with the Super Human
        List<Organization> orgs = new ArrayList<>();
        orgs.add(org);
        sh.setOrganizations(orgs);
        // Add Sightings Associated with the Organization
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(s);
        sh.setSightings(sightings);
        // Add PowerTypes to the Associated Super Human
        List<SuperPower> superPowers = new ArrayList<>();
        superPowers.add(sp);
        sh.setSuperPowers(superPowers);

        superDao.addSuperHuman(sh);

        SuperHuman fromDB = superDao.getSuperHumanById(sh.getSuperHumanId());
        assertEquals(fromDB.getSuperHumanId(), sh.getSuperHumanId());

        // Update Super Human Name
        sh.setSuperHumanName("Jolly Green Giant");
        superDao.updateSuperHuman(sh);

        SuperHuman updatedSH = superDao.getSuperHumanById(sh.getSuperHumanId());
        assertEquals(updatedSH.getSuperHumanName(), sh.getSuperHumanName());
    }

    /**
     * Test of getSuperHumanById method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testAddGetSuperHumanById() {
        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);

        SuperHuman fromDB = superDao.getSuperHumanById(sh.getSuperHumanId());
        assertEquals(fromDB.getSuperHumanId(), sh.getSuperHumanId());
    }

    /**
     * Test of getAllSuperHumans method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testGetAllSuperHumans() {
        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);

        // Create New Super Human
        SuperHuman sh2 = new SuperHuman();
        sh2.setSuperHumanName("Thor");
        sh2.setDescription("God of Thunder.");
        superDao.addSuperHuman(sh2);

        List<SuperHuman> fromDB = superDao.getAllSuperHumans();
        SuperHuman dataBaseSuper = superDao.getSuperHumanById(sh2.getSuperHumanId());

        assertNotNull(fromDB);
        assertEquals(dataBaseSuper.getSuperHumanName(), sh2.getSuperHumanName());
    }

    /**
     * Test of getAllSuperHumansByOrg method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testGetAllSuperHumansByOrgANDinsertSupersXOrgConnect() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");
        organizationDao.addOrganization(org);
        //Get Org Id
        Organization dataBaseOrg = organizationDao.getOrganizationById(org.getOrganizationId());
        int orgId = dataBaseOrg.getOrganizationId();

        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);
        // Get Super Id
        SuperHuman dataBaseSuper = superDao.getSuperHumanById(sh.getSuperHumanId());
        int superId = dataBaseSuper.getSuperHumanId();

        // Connect them via Bridge Table
        superDao.insertSupersXOrganizationsConnect(superId, orgId);
        // Get All Super Humans by orgId and Test It
        List<SuperHuman> fromMethod = superDao.getAllSuperHumansByOrg(orgId);
        SuperHuman updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getSuperHumanName(), sh.getSuperHumanName());
    }

    /**
     * Test of getAllSuperHumansBySighting method, of class
     * SuperHumanDaoInterface.
     */
    @Test
    public void testGetAllSuperHumansBySightingANDinsertSupersXSightingConnect() {
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
        // Get Super Id
        SuperHuman dataBaseSuper = superDao.getSuperHumanById(sh.getSuperHumanId());
        int superId = dataBaseSuper.getSuperHumanId();

        // Connect them via Bridge Table
        superDao.insertSupersXSightingsConnect(superId, sightingId);
        // Get All Super Humans by sighingId and Test It
        List<SuperHuman> fromMethod = superDao.getAllSuperHumansBySighting(sightingId);
        SuperHuman updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getSuperHumanName(), sh.getSuperHumanName());
    }

//    /**
//     * Test of insertSupersXSightingsConnect method, of class SuperHumanDaoInterface.
//     */
//    @Test
//    public void testInsertSupersXSightingsConnect() {
    // See testGetAllSuperHumansBySightingANDinsertSupersXSightingConnect()
//    }
    /**
     * Test of deleteSupersSighting method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testDeleteSupersSighting() {
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

        // Delete the Connection
        superDao.deleteSupersSighting(sightingId);

        List<Sighting> allSightingsDeleted = sightingDao.getAllSightingsBySuperHuman(superHumanId);
        assertTrue(allSightingsDeleted.isEmpty());
    }

//    /**
//     * Test of insertSupersXOrganizationsConnect method, of class SuperHumanDaoInterface.
//     */
//    @Test
//    public void testInsertSupersXOrganizationsConnect() {
    // See testGetAllSuperHumansByOrgANDinsertSupersXOrgConnect()
//    }
    /**
     * Test of deleteSupersOrganization method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testDeleteSupersOrganization() {
        Organization org = new Organization();
        org.setOrganizationName("Midwest Farmers");
        org.setDescription("Proud Farmers of America");
        org.setStreet("123 Main Street");
        org.setCity("Farmville");
        org.setState("IA");
        org.setZip("54321");
        org.setPhone("1234567890");
        organizationDao.addOrganization(org);
        //Get Org Id
        Organization dataBaseOrg = organizationDao.getOrganizationById(org.getOrganizationId());
        int orgId = dataBaseOrg.getOrganizationId();

        // Create New Super Human
        SuperHuman sh = new SuperHuman();
        sh.setSuperHumanName("John Deer");
        sh.setDescription("He's a great Farmer.");
        superDao.addSuperHuman(sh);
        // Get Super Id
        SuperHuman dataBaseSuper = superDao.getSuperHumanById(sh.getSuperHumanId());
        int superId = dataBaseSuper.getSuperHumanId();

        // Connect them via Bridge Table
        superDao.insertSupersXOrganizationsConnect(superId, orgId);

        List<SuperHuman> fromMethod = superDao.getAllSuperHumansByOrg(orgId);
        SuperHuman updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getSuperHumanName(), sh.getSuperHumanName());

        // Delete the Connection
        superDao.deleteSupersOrganization(orgId);
        List<Organization> allOrganizationsDeleted = organizationDao.getAllOrganizationsBySuperHuman(superId);
        assertTrue(allOrganizationsDeleted.isEmpty());
    }

//    /**
//     * Test of insertSupersXPowersConnect method, of class SuperHumanDaoInterface.
//     */
//    @Test
//    public void testInsertSupersXPowersConnect() {
    // See testDeleteSupersPowers()
//    }
    /**
     * Test of deleteSupersPowers method, of class SuperHumanDaoInterface.
     */
    @Test
    public void testDeleteSupersPowers() {
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

        // Connect the SuperHuman with the SuperPowers at the Bridge Table and Test
        superDao.insertSupersXPowersConnect(powerId, superHumanId);

        List<SuperPower> fromMethod = superPowerDao.getAllSuperPowerBySuperHumanId(superHumanId);
        SuperPower updatedFromMethod = fromMethod.get(0);

        assertEquals(updatedFromMethod.getName(), sp.getName());

        //Delete the Bridge Connection and Test
        superDao.deleteSupersPowers(superHumanId);

        List<SuperPower> allPowersDeleted = superPowerDao.getAllSuperPowerBySuperHumanId(superHumanId);
        assertTrue(allPowersDeleted.isEmpty());
    }
}
