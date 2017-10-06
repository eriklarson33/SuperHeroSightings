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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author eriklarson-laptop
 */
//public class HeroDaoJDBCTemplateImplTest {
//
//    HeroDao dao;
//
//    public HeroDaoJDBCTemplateImplTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        ApplicationContext ctx
//                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//        dao = ctx.getBean("HeroDao", HeroDao.class);
//
//        // delete all Super Humans
//        List<SuperHuman> superHumans = dao.getAllSuperHumans();
//        for (SuperHuman currentSuperHuman : superHumans) {
//            dao.deleteSuperHuman(currentSuperHuman.getSuperHumanId());
//        }
//        //delete all organizations
//        List<Organization> organizations = dao.getAllOrganizations();
//        for (Organization currentOrg : organizations) {
//            dao.deleteOrganization(currentOrg.getOrganizationId());
//        }
//        // delete all sightings
//        List<Sighting> sightings = dao.getAllSightings();
//        for (Sighting currentSighting : sightings) {
//            dao.deleteOrganization(currentSighting.getSightingId());
//        }
//        // delete all superPowers
//        List<SuperPower> superPowers = dao.getAllSuperPowers();
//        for (SuperPower currentPower : superPowers) {
//            dao.deleteSuperPower(currentPower.getPowerTypeId());
//        }
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of setJdbcTemplate method, of class HeroDaoJDBCTemplateImpl.
//     */
//    @Test
//    public void testSetJdbcTemplate() {
//        System.out.println("setJdbcTemplate");
//        JdbcTemplate jdbcTemplate = null;
//        HeroDaoJDBCTemplateImpl instance = new HeroDaoJDBCTemplateImpl();
//        instance.setJdbcTemplate(jdbcTemplate);
//    }
//
//    /**
//     * Test of addSuperHuman method, of class HeroDaoJDBCTemplateImpl.
//     */
//    @Test
//    public void testAddGetSuperHuman() {
//
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//
//        dao.addOrganization(org);
//
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//
//        dao.addSuperPower(sp);
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        // Add Organizations associated with the Super Human
//        List<Organization> orgs = new ArrayList<>();
//        orgs.add(org);
//        sh.setOrganizations(orgs);
//        // Add Sightings Associated with the Organization
//        List<Sighting> sightings = new ArrayList<>();
//        sightings.add(s);
//        sh.setSightings(sightings);
//        // Add PowerTypes to the Associated Super Human
//        List<SuperPower> superPowers = new ArrayList<>();
//        superPowers.add(sp);
//        sh.setSuperPowers(superPowers);
//
//        dao.addSuperHuman(sh);
//
//        SuperHuman fromDB = dao.getSuperHumanById(sh.getSuperHumanId());
//        assertEquals(fromDB.getSuperHumanId(), sh.getSuperHumanId());
//    }
//
//    @Test
//    public void testUpdateAndRetrieveByPowerBySuperHuman() {
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//
//        dao.addOrganization(org);
//
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        SuperPower spOne = new SuperPower();
//        spOne.setName("Power Of One");
//        spOne.setDescription("Will never Change");
//        dao.addSuperPower(spOne);
//
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//        dao.addSuperPower(sp);
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        // Add Organizations associated with the Super Human
//        List<Organization> orgs = new ArrayList<>();
//        orgs.add(org);
//        sh.setOrganizations(orgs);
//        // Add Sightings Associated with the Organization
//        List<Sighting> sightings = new ArrayList<>();
//        sightings.add(s);
//        sh.setSightings(sightings);
//        // Add PowerTypes to the Associated Super Human
//        List<SuperPower> superPowers = new ArrayList<>();
//        superPowers.add(sp);
//        sh.setSuperPowers(superPowers);
//
//        dao.addSuperHuman(sh);
//
//        SuperHuman fromDB = dao.getSuperHumanById(sh.getSuperHumanId());
//        assertEquals(fromDB.getSuperHumanId(), sh.getSuperHumanId());
//
//        // Update Super Human Name
//        sh.setSuperHumanName("Jolly Green Giant");
//        dao.updateSuperHuman(sh);
//
//        SuperHuman updatedSH = dao.getSuperHumanById(sh.getSuperHumanId());
//        assertEquals(updatedSH.getSuperHumanName(), sh.getSuperHumanName());
//
//        int superHumanId = updatedSH.getSuperHumanId();
//        List<SuperPower> updatedPowerList = dao.getAllSuperPowerBySuperHumanId(superHumanId);
//        for (SuperPower power : updatedPowerList) {
//            String name = power.getName();
//            int powerId = power.getPowerTypeId();
//            String description = power.getDescription();
//            System.out.println("Name: " + name + "");
//
//            System.out.println(updatedPowerList);
//            assertEquals(name, "Green Thumb");
//        }
//    }
//
//    @Test
//    public void testGetAllSupers() {
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//
//        dao.addSuperHuman(sh);
//
//        List<SuperHuman> fromDB = dao.getAllSuperHumans();
//        SuperHuman dataBaseSuper = dao.getSuperHumanById(sh.getSuperHumanId());
//
//        assertNotNull(fromDB);
//        assertEquals(dataBaseSuper.getSuperHumanId(), sh.getSuperHumanId());
//
//    }
//
//    @Test
//    public void testGetAllSupersByOrg() {
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//        dao.addOrganization(org);
//        //Get Org Id
//        Organization dataBaseOrg = dao.getOrganizationById(org.getOrganizationId());
//        int orgId = dataBaseOrg.getOrganizationId();
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        dao.addSuperHuman(sh);
//        // Get Super Id
//        SuperHuman dataBaseSuper = dao.getSuperHumanById(sh.getSuperHumanId());
//        int superId = dataBaseSuper.getSuperHumanId();
//
//        // Connect them via Bridge Table
//        dao.insertSupersXOrganizationsConnect(superId, orgId);
//
//        List<SuperHuman> fromMethod = dao.getAllSuperHumansByOrg(orgId);
//        SuperHuman updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getSuperHumanName(), sh.getSuperHumanName());
//    }
//
//    @Test
//    public void testDeleteSupersOrganizationBridgeConnection() {
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//        dao.addOrganization(org);
//        //Get Org Id
//        Organization dataBaseOrg = dao.getOrganizationById(org.getOrganizationId());
//        int orgId = dataBaseOrg.getOrganizationId();
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        dao.addSuperHuman(sh);
//        // Get Super Id
//        SuperHuman dataBaseSuper = dao.getSuperHumanById(sh.getSuperHumanId());
//        int superId = dataBaseSuper.getSuperHumanId();
//
//        // Connect them via Bridge Table
//        dao.insertSupersXOrganizationsConnect(superId, orgId);
//
//        List<SuperHuman> fromMethod = dao.getAllSuperHumansByOrg(orgId);
//        SuperHuman updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getSuperHumanName(), sh.getSuperHumanName());
//
//        // Delete the Connection
//        dao.deleteSupersOrganization(orgId);
//        List<Organization> allOrganizationsDeleted = dao.getAllOrganizationsBySuperHuman(superId);
//        assertTrue(allOrganizationsDeleted.isEmpty());
//    }
//
//    @Test
//    public void testGetAllSupersBySighting() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//        // Get the Sighting Id from the DB
//        Sighting fromDBsite = dao.getSightingById(s.getSightingId());
//        int sightingId = fromDBsite.getSightingId();
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        dao.addSuperHuman(sh);
//        // Get Super Id
//        SuperHuman dataBaseSuper = dao.getSuperHumanById(sh.getSuperHumanId());
//        int superId = dataBaseSuper.getSuperHumanId();
//
//        // Connect them via Bridge Table
//        dao.insertSupersXSightingsConnect(superId, sightingId);
//
//        List<SuperHuman> fromMethod = dao.getAllSuperHumansBySighting(sightingId);
//        SuperHuman updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getSuperHumanName(), sh.getSuperHumanName());
//    }
//
//    @Test
//    public void updateOrganization() {
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//
//        dao.addOrganization(org);
//
//        // update Organization Name
//        org.setOrganizationName("Farmers United");
//        dao.updateOrganization(org);
//
//        Organization updatedOrg = dao.getOrganizationById(org.getOrganizationId());
//        assertEquals(updatedOrg.getOrganizationName(), org.getOrganizationName());
//    }
//
//    @Test
//    public void updateSighting() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        // update Sighting Name
//        s.setName("King Kong's new Jungle Gym");
//        dao.updateSighting(s);
//
//        Sighting updatedSite = dao.getSightingById(s.getSightingId());
//        assertEquals(updatedSite.getName(), s.getName());
//    }
//
//    @Test
//    public void updateSuperPower() {
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//
//        dao.addSuperPower(sp);
//
//        // update Super Power
//        sp.setName("Farmer's Delight");
//        dao.updateSuperPower(sp);
//
//        SuperPower updatedSP = dao.getSuperPowerById(sp.getPowerTypeId());
//        assertEquals(updatedSP.getName(), sp.getName());
//    }
//
//    /**
//     * Test of deleteSuperHuman method, of class HeroDaoJDBCTemplateImpl.
//     */
//    @Test
//    public void testDeleteSuperHuman() {
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//
//        dao.addOrganization(org);
//
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//
//        dao.addSuperPower(sp);
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        // Add Organizations associated with the Super Human
//        List<Organization> orgs = new ArrayList<>();
//        orgs.add(org);
//        sh.setOrganizations(orgs);
//        // Add Sightings Associated with the Organization
//        List<Sighting> sightings = new ArrayList<>();
//        sightings.add(s);
//        sh.setSightings(sightings);
//        // Add PowerTypes to the Associated Super Human
//        List<SuperPower> superPowers = new ArrayList<>();
//        superPowers.add(sp);
//        sh.setSuperPowers(superPowers);
//
//        dao.addSuperHuman(sh);
//
//        SuperHuman fromDB = dao.getSuperHumanById(sh.getSuperHumanId());
//        assertEquals(fromDB.getSuperHumanId(), sh.getSuperHumanId());
//
//        dao.deleteSuperHuman(sh.getSuperHumanId());
//        assertNull(dao.getSuperHumanById(sh.getSuperHumanId()));
//    }
//
//    /**
//     * Test of addOrganization method, of class HeroDaoJDBCTemplateImpl.
//     */
//    @Test
//    public void testAddGetOrganization() {
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//
//        dao.addOrganization(org);
//
//        Organization fromDao = dao.getOrganizationById(org.getOrganizationId());
//        assertEquals(fromDao.getOrganizationId(), org.getOrganizationId());
//    }
//
//    /**
//     * Test of deleteOrganization method, of class HeroDaoJDBCTemplateImpl.
//     */
//    @Test
//    public void testDeleteOrganization() {
//        Organization org = new Organization();
//        org.setOrganizationName("Midwest Farmers");
//        org.setDescription("Proud Farmers of America");
//        org.setStreet("123 Main Street");
//        org.setCity("Farmville");
//        org.setState("IA");
//        org.setZip("54321");
//        org.setPhone("1234567890");
//
//        dao.addOrganization(org);
//
//        int x = org.getOrganizationId();
//
//        Organization fromDao = dao.getOrganizationById(org.getOrganizationId());
//        assertEquals(fromDao.getOrganizationId(), org.getOrganizationId());
//        dao.deleteOrganization(x);
//        assertNull(dao.getOrganizationById(x));
//    }
//
//    /**
//     * Test of addSighting method, of class HeroDaoJDBCTemplateImpl.
//     */
//    @Test
//    public void testAddGetSighting() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        Sighting fromDB = dao.getSightingById(s.getSightingId());
//        assertEquals(fromDB.getSightingId(), s.getSightingId());
//    }
//
//    @Test
//    public void testAddGetAllSightings() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        List<Sighting> fromDB = dao.getAllSightings();
//        Sighting dataBaseSite = dao.getSightingById(s.getSightingId());
//
//        assertNotNull(fromDB);
//        assertEquals(s.getName(), dataBaseSite.getName());
//
//    }
//
//    @Test
//    public void testAddGetSightingLimit10() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        List<Sighting> fromDB = dao.getAllSightingsLimit10();
//        Sighting dataBaseSite = dao.getSightingById(s.getSightingId());
//
//        assertNotNull(fromDB);
//        assertEquals(s.getName(), dataBaseSite.getName());
//    }
//
//    @Test
//    public void testGetAllSightingsBySuperHumanBridgeConnection() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//        // Get the Sighting Id from the DB
//        Sighting fromDBsite = dao.getSightingById(s.getSightingId());
//        int sightingId = fromDBsite.getSightingId();
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        dao.addSuperHuman(sh);
//        // Get the Super Human Id from the DB
//        SuperHuman fromDBsuper = dao.getSuperHumanById(sh.getSuperHumanId());
//        int superHumanId = fromDBsuper.getSuperHumanId();
//
//        // Connect the SuperHuman with the Sighting at the Bridge Table
//        dao.insertSupersXSightingsConnect(superHumanId, sightingId);
//
//        List<Sighting> fromMethod = dao.getAllSightingsBySuperHuman(superHumanId);
//        Sighting updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getSightingId(), sightingId);
//
//    }
//
//    @Test
//    public void testGetAllSightingsByDate() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//
//        LocalDate siteDate = LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE);
//        List<Sighting> fromMethod = dao.getAllSightingsByDate(siteDate);
//
//        for (Sighting site : fromMethod) {
//            String name = site.getName();
//            int siteId = site.getSightingId();
//            String description = site.getDescription();
//            System.out.println("Name: " + name + " ");
//            System.out.println("ID: " + siteId + " ");
//            System.out.println("Description: " + description + " ");
//
//        Sighting updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getName(), s.getName());
//        }
//    }
//
//    @Test
//    public void testDeleteSupersSightingBridgeConnection() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_LOCAL_DATE));
//        dao.addSighting(s);
//        // Get the Sighting Id from the DB
//        Sighting fromDBsite = dao.getSightingById(s.getSightingId());
//        int sightingId = fromDBsite.getSightingId();
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        dao.addSuperHuman(sh);
//        // Get the Super Human Id from the DB
//        SuperHuman fromDBsuper = dao.getSuperHumanById(sh.getSuperHumanId());
//        int superHumanId = fromDBsuper.getSuperHumanId();
//
//        // Connect the SuperHuman with the Sighting at the Bridge Table
//        dao.insertSupersXSightingsConnect(superHumanId, sightingId);
//
//        List<Sighting> fromMethod = dao.getAllSightingsBySuperHuman(superHumanId);
//        Sighting updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getSightingId(), sightingId);
//
//        // Delete the Connection
//        dao.deleteSupersSighting(sightingId);
//
//        List<Sighting> allSightingsDeleted = dao.getAllSightingsBySuperHuman(superHumanId);
//        assertTrue(allSightingsDeleted.isEmpty());
//
//    }
//
//    /**
//     * Test of deleteSighting method, of class HeroDaoJDBCTemplateImpl.
//     */
//    @Test
//    public void testDeleteSighting() {
//        Sighting s = new Sighting();
//        s.setName("Empire State of Mind");
//        s.setDescription("Battle at the Empire State Building");
//        s.setStreet("350 5th Ave.");
//        s.setCity("New York");
//        s.setState("NY");
//        s.setZip("10118");
//        s.setLatitude(new BigDecimal("40.75"));
//        s.setLongitude(new BigDecimal("-73.99"));
//        s.setSightingDate(LocalDate.parse("2015-10-12",
//                DateTimeFormatter.ISO_DATE));
//        dao.addSighting(s);
//
//        Sighting fromDB = dao.getSightingById(s.getSightingId());
//        assertEquals(fromDB.getSightingId(), s.getSightingId());
//        dao.deleteSighting(s.getSightingId());
//        assertNull(dao.getSightingById(s.getSightingId()));
//    }
//
//    @Test
//    public void testAddGetSuperPowers() {
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//
//        dao.addSuperPower(sp);
//
//        SuperPower fromDB = dao.getSuperPowerById(sp.getPowerTypeId());
//        assertEquals(fromDB.getPowerTypeId(), sp.getPowerTypeId());
//    }
//
//    @Test
//    public void testDeleteSuperPower() {
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//        dao.addSuperPower(sp);
//
//        SuperPower fromDB = dao.getSuperPowerById(sp.getPowerTypeId());
//        assertEquals(fromDB.getPowerTypeId(), sp.getPowerTypeId());
//        dao.deleteSuperPower(sp.getPowerTypeId());
//        assertNull(dao.getSuperPowerById(sp.getPowerTypeId()));
//    }
//
//    @Test
//    public void testGetAllSuperPowers() {
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//        dao.addSuperPower(sp);
//
//        List<SuperPower> fromDB = dao.getAllSuperPowers();
//        SuperPower dataBasePower = dao.getSuperPowerById(sp.getPowerTypeId());
//
//        assertNotNull(fromDB);
//        assertEquals(sp.getName(), dataBasePower.getName());
//    }
//
//    @Test
//    public void testGetAllSuperPowerBySuperHumanBridgeConnection() {
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//        dao.addSuperPower(sp);
//        SuperPower fromDBpower = dao.getSuperPowerById(sp.getPowerTypeId());
//        int powerId = fromDBpower.getPowerTypeId();
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        dao.addSuperHuman(sh);
//        SuperHuman fromDBsuper = dao.getSuperHumanById(sh.getSuperHumanId());
//        int superHumanId = fromDBsuper.getSuperHumanId();
//
//        // Connect the SuperHuman with the SuperPowers at the Bridge Table
//        dao.insertSupersXPowersConnect(powerId, superHumanId);
//
//        List<SuperPower> fromMethod = dao.getAllSuperPowerBySuperHumanId(superHumanId);
//        SuperPower updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getName(), sp.getName());
//
//    }
//
//    @Test
//    public void testDeleteSupersPowers() {
//        SuperPower sp = new SuperPower();
//        sp.setName("Green Thumb");
//        sp.setDescription("Gifted in planting and nurturing all plant life.");
//        dao.addSuperPower(sp);
//        SuperPower fromDBpower = dao.getSuperPowerById(sp.getPowerTypeId());
//        int powerId = fromDBpower.getPowerTypeId();
//
//        // Create New Super Human
//        SuperHuman sh = new SuperHuman();
//        sh.setSuperHumanName("John Deer");
//        sh.setDescription("He's a great Farmer.");
//        dao.addSuperHuman(sh);
//        SuperHuman fromDBsuper = dao.getSuperHumanById(sh.getSuperHumanId());
//        int superHumanId = fromDBsuper.getSuperHumanId();
//
//        // Connect the SuperHuman with the SuperPowers at the Bridge Table
//        dao.insertSupersXPowersConnect(powerId, superHumanId);
//
//        List<SuperPower> fromMethod = dao.getAllSuperPowerBySuperHumanId(superHumanId);
//        SuperPower updatedFromMethod = fromMethod.get(0);
//
//        assertEquals(updatedFromMethod.getName(), sp.getName());
//
//        //Delete the Bridge Connection
//        dao.deleteSupersPowers(superHumanId);
//
//        List<SuperPower> allPowersDeleted = dao.getAllSuperPowerBySuperHumanId(superHumanId);
//        assertTrue(allPowersDeleted.isEmpty());
//    }
//
//}
