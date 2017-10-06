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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eriklarson-laptop
 */
public class HeroDaoJDBCTemplateImpl implements HeroDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Prepared Statements for SuperHuman class
    // =========================================
    private static final String SQL_INSERT_SUPERHUMAN
            = "insert into super_humans (superhuman_name, description)"
            + "values (?, ?)";

    private static final String SQL_INSERT_SUPERHUMAN_X_ORGS
            = "INSERT INTO superhuman_x_orgs (superhuman_id, organization_id)"
            + "values (?, ?)";

    private static final String SQL_INSERT_SUPERHUMAN_X_POWERS
            = "INSERT INTO superhuman_x_powers (superhuman_id, powertype_id) "
            + "values (?, ?)";

    private static final String SQL_INSERT_SUPERHUMAN_X_SIGHTINGS
            = "INSERT INTO superhuman_x_sightings (superhuman_id, sighting_id) "
            + "values (?, ?)";

    private static final String SQL_DELETE_SUPERHUMAN_X_ORGS
            = "DELETE FROM superhuman_x_orgs WHERE superhuman_id = ?";

    private static final String SQL_DELETE_SUPERHUMAN_X_POWERS
            = "DELETE FROM superhuman_x_POWERS WHERE superhuman_id = ?";

    private static final String SQL_DELETE_SUPERHUMAN_X_SIGHTINGS
            = "DELETE FROM superhuman_x_sightings WHERE superhuman_id = ?";

    private static final String SQL_DELETE_SUPERHUMAN
            = "delete from super_humans where superhuman_id = ?";

    private static final String SQL_UPDATE_SUPERHUMAN
            = "UPDATE super_humans SET superhuman_name = ?, description = ? "
            + "WHERE superhuman_id = ?";

    private static final String SQL_SELECT_SUPERHUMAN
            = "select * from super_humans where superhuman_id = ?";

    private static final String SQL_SELECT_ALL_SUPERHUMANS
            = "select * from super_humans";

    private static final String SQL_SELECT_SUPERHUMAN_BY_ORG_ID
            = "select sh.superhuman_id, sh.superhuman_name, sh.description, "
            + "sh.societal_impact "
            + "from super_humans sh "
            + "join superhuman_x_orgs sxo on sh.superhuman_id = sxo.superhuman_id "
            + "join organizations org on sxo.organization_id = org.organization_id "
            + "where sxo.organization_id = ?";

    private static final String SQL_SELECT_SUPERHUMAN_BY_SIGHTING_ID
            = "select sh.superhuman_id, sh.superhuman_name, sh.description, sh.societal_impact "
            + "from super_humans sh "
            + "join superhuman_x_sightings sxs on sh.superhuman_id = sxs.superhuman_id "
            + "join sightings s on sxs.sighting_id = s.sighting_id "
            + "where s.sighting_id = ?";

    // Prepared Statements for Organization class
    // ===========================================
    private static final String SQL_INSERT_ORGANIZATION
            = "INSERT INTO organizations ("
            + "organization_name, description, street, city, state, zip, phone)"
            + "values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "DELETE FROM organizations WHERE organization_id = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "UPDATE organizations SET organization_name = ?, "
            + "description = ?, street = ?, city = ?, state = ?, zip = ?, phone = ? "
            + "WHERE organization_id = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "SELECT * FROM organizations WHERE organization_id = ?";

    private static final String SQL_SELECT_ORGS_BY_SUPERHUMAN_ID
            = "SELECT org.organization_id, org.organization_name, org.description, org.street, "
            + "org.city, org.state, org.zip, org.phone "
            + "FROM organizations org "
            + "JOIN superhuman_x_orgs sxo on org.organization_id = sxo.organization_id "
            + "JOIN super_humans sh on sxo.superhuman_id = sh.superhuman_id "
            + "WHERE sh.superhuman_id = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "SELECT * FROM organizations "
            + "ORDER BY organization_name ASC";
    
//    private static final String SQL_INSERT_SUPERS_ORGANIZATIONS
//            = "INSERT into superhumans_x_orgs(organization_id, superhuman_id) "
//            + "VALUES (?, ?)";
//    
    private static final String SQL_DELETE_SUPERS_ORGANIZATIONS
            = "DELETE FROM superhuman_x_orgs WHERE organization_id = ?";

    // Prepared Statements for Sighting class
    // =========================================
    private static final String SQL_INSERT_SIGHTING
            = "INSERT INTO sightings "
            + "(sighting_name, description, street, city, state, zip, latitude, "
            + "longitude, sighting_date) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "DELETE FROM sightings WHERE sighting_id = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "UPDATE sightings SET sighting_name = ?, description = ?, street = ?,"
            + "city = ?, state = ?, zip = ?, latitude = ?, longitude = ?, sighting_date = ?"
            + "WHERE sighting_id = ?";

    private static final String SQL_SELECT_SIGHTING
            = "SELECT * FROM sightings WHERE sighting_id = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_SUPERHUMAN_ID
            = "SELECT s.sighting_id, s.sighting_name, s.description, s.street, "
            + "s.city, s.state, s.zip, s.latitude, s.longitude, s.sighting_date "
            + "FROM sightings s "
            + "JOIN superhuman_x_sightings sxs on s.sighting_id = sxs.sighting_id "
            + "WHERE sxs.superhuman_id = ?";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "SELECT s.sighting_id, s.sighting_name, s.description, s.street, "
            + "s.city, s.state, s.zip, s.latitude, s.longitude, s.sighting_date "
            + "FROM sightings s "
            + "WHERE s.sighting_date = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "SELECT * FROM sightings "
            + "ORDER BY sighting_date DESC";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS_LIMIT_10
            = "SELECT * FROM sightings "
            + "ORDER BY sighting_date DESC "
            + "LIMIT 10";
    
//    private static final String SQL_INSERT_SUPERS_SIGHTINGS
//            = "INSERT into superhuman_x_sightings(sighting_id, superhuman_id) "
//            + "VALUES (?, ?)";
//    
    private static final String SQL_DELETE_SUPERS_SIGHTINGS
            = "DELETE FROM superhuman_x_sightings WHERE sighting_id = ?";

    // Prepared Statements for SuperPower class
    // ========================================
    private static final String SQL_INSERT_SUPERPOWER
            = "INSERT INTO super_powers (power_type, description) "
            + "VALUES (?, ?)";

    private static final String SQL_DELETE_SUPERPOWER
            = "DELETE FROM super_powers WHERE powertype_id = ?";

    private static final String SQL_UPDATE_SUPERPOWER
            = "UPDATE super_powers SET power_type = ?, description = ? "
            + "WHERE powertype_id = ?";

    private static final String SQL_SELECT_SUPERPOWER
            = "SELECT * FROM super_powers WHERE powertype_id = ?";

    private static final String SQL_SELECT_SUPERPOWERS_BY_SUPERHUMAN_ID
            = "SELECT sp.powertype_id, sp.power_type, sp.description "
            + "FROM super_powers sp "
            + "JOIN superhuman_x_powers sxp on sp.powertype_id = sxp.powertype_id "
            + "WHERE sxp.superhuman_id = ?";

    private static final String SQL_SELECT_ALL_SUPERPOWERS
            = "SELECT * FROM super_powers "
            + "ORDER BY power_type ASC";
    
//    private static final String SQL_INSERT_SUPERS_POWERS
//            = "INSERT into superhuman_x_powers(powertype_id, superhuman_id) "
//            + "VALUES (?, ?)";
//    
    private static final String SQL_DELETE_SUPERS_POWERS
            = "DELETE FROM superhuman_x_powers WHERE superhuman_id = ?";

    // SuperHuman Methods
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperHuman addSuperHuman(SuperHuman superHuman) {
        jdbcTemplate.update(SQL_INSERT_SUPERHUMAN,
                superHuman.getSuperHumanName(),
                superHuman.getDescription());

        int superHumanId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        superHuman.setSuperHumanId(superHumanId);
        return superHuman;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperHuman(int superHumanId) {
        // delete superhuman_x_orgs relationship for this superhuman
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_ORGS, superHumanId);
        // delete the superhuman_x_powers relationship
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_POWERS, superHumanId);
        // delete the superhuman_x_sightings relationship
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_SIGHTINGS, superHumanId);
        // delete super human
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN, superHumanId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperHuman(SuperHuman superHuman) {
        // update the super_humans table
        jdbcTemplate.update(SQL_UPDATE_SUPERHUMAN,
                superHuman.getSuperHumanName(),
                superHuman.getDescription(),
                superHuman.getSuperHumanId());

// delete the relationships & reset each of them
        // delete the superhuman_x_orgs relationship
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_ORGS,
                superHuman.getSuperHumanId());
        // reset the superhuman_x_powers relationship
        insertSuperHumanXOrgs(superHuman);

        // delete the superhuman_x_powers relationship
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_POWERS,
                superHuman.getSuperHumanId());
        insertSuperHumanXPowers(superHuman);

        // delete the superhuman_x_sightings relationship
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_SIGHTINGS,
                superHuman.getSuperHumanId());
        insertSuperHumanXSightings(superHuman);
    }

    @Override
    public SuperHuman getSuperHumanById(int superHumanId) {
        try {
            // get the properties from the super_humans table
            SuperHuman superHuman = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHUMAN,
                    new SuperHumanMapper(),
                    superHumanId);
            
            // get the organizations for the current SuperHuman & set the list
            superHuman.setOrganizations(findOrgsForSuperHuman(superHuman));
            // get the powers for the current SuperHuman & set the list
            superHuman.setSuperPowers(findPowersForSuperHuman(superHuman));
            // get the sightings for the current SuperHuman & set the list
            superHuman.setSightings(findSightingsForSuperHuman(superHuman));

            return superHuman;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperHuman> getAllSuperHumans() {
        // get all the Super Humans
        List<SuperHuman> superHumanList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHUMANS,
                new SuperHumanMapper());
        // set the orgs, super Powers, and sightings for each Super Human
        return associatePowersSightingsAndOrgsWithSuperHuman(superHumanList);
    }

    @Override
    public List<SuperHuman> getAllSuperHumansByOrg(int organizationId) {
        // get the super heroes associated with this organization
        List<SuperHuman> superHumanList
                = jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_ORG_ID,
                        new SuperHumanMapper(),
                        organizationId);
        //  set the orgs, super Powers, and sightings for each Super Human
        return associatePowersSightingsAndOrgsWithSuperHuman(superHumanList);
    }

    @Override
    public List<SuperHuman> getAllSuperHumansBySighting(int sightingId) {
        // get all super Humans associated with this sighting
        List<SuperHuman> superHumanList
                = jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_SIGHTING_ID,
                        new SuperHumanMapper(),
                      //  new SightingMapper(),
                        sightingId);
        //  set the orgs, super Powers, and sightings for each Super Human
        return associatePowersSightingsAndOrgsWithSuperHuman(superHumanList);
    }

    @Override
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getZip(),
                organization.getPhone());

        int orgId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);
        organization.setOrganizationId(orgId);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getState(),
                organization.getZip(),
                organization.getPhone(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(), 
                    organizationId);
            
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperHuman(int superHumanId) {
        return jdbcTemplate.query(SQL_SELECT_ORGS_BY_SUPERHUMAN_ID,
                new OrganizationMapper(),
                superHumanId);
    }

    // SIGHTING METHODS
    // ====================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getName(),
                sighting.getDescription(),
                sighting.getStreet(),
                sighting.getCity(),
                sighting.getState(),
                sighting.getZip(),
                sighting.getLatitude(),
                sighting.getLongitude(),
                sighting.getSightingDate().toString());

        int sightingId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        sighting.setSightingId(sightingId);
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getName(),
                sighting.getDescription(),
                sighting.getStreet(),
                sighting.getCity(),
                sighting.getState(),
                sighting.getZip(),
                sighting.getLatitude(),
                sighting.getLongitude(),
                sighting.getSightingDate().toString(),
                sighting.getSightingId());
        
//        LocalDate siteDate = LocalDate.parse(sighting.getSightingDate(), DateTimeFormatter.ISO_LOCAL_DATE);
//        
//        // set the LocalDateTime
//        site.setSightingDate(siteDate);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
           return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(), 
                    sightingId);
            
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightingsBySuperHuman(int superHumanId) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPERHUMAN_ID,
                new SightingMapper(),
                superHumanId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        // get all the Organizations
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
        return orgs;
    }

    @Override
    public List<Sighting> getAllSightings() {
        // get all the Sightings
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        return sightings;
    }
    
    @Override
    public List<Sighting> getAllSightingsLimit10() {
        // get all the Sightings
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS_LIMIT_10,
                new SightingMapper());
        return sightings;
    }
    
    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate siteDate) {
        try {
        // get all the Sightings
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                new SightingMapper(),
                siteDate.toString());
        return sightings;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    // SUPER POWER METHODS
    // ====================
    @Override
    public void addSuperPower(SuperPower superPower) {
        jdbcTemplate.update(SQL_INSERT_SUPERPOWER,
                superPower.getName(),
                superPower.getDescription());

        int superPowerId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                        Integer.class);

        superPower.setPowerTypeId(superPowerId);
    }

    @Override
    public void deleteSuperPower(int powerTypeId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER, powerTypeId);
    }

    @Override
    public void updateSuperPower(SuperPower superPower) {
        jdbcTemplate.update(SQL_UPDATE_SUPERPOWER,
                superPower.getName(),
                superPower.getDescription(),
                superPower.getPowerTypeId());
    }

    @Override
    public List<SuperPower> getAllSuperPowers() {
        // get all the SuperPowers
        List<SuperPower> superPowers = jdbcTemplate.query(SQL_SELECT_ALL_SUPERPOWERS,
                new SuperPowerMapper());
        return superPowers;
    }

    @Override
    public SuperPower getSuperPowerById(int powerTypeId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER, 
                    new SuperPowerMapper(),
                    powerTypeId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPower> getAllSuperPowerBySuperHumanId(int superHumanId) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHUMAN_ID,
                new SuperPowerMapper(),
                superHumanId);
    }

    // HELPER METHODS
    // ==============
    // Following Helper Methods update Bridge Tables via the SuperHuman class
    private void insertSuperHumanXOrgs(SuperHuman superHuman) {
        final int superHumanId = superHuman.getSuperHumanId();
        final List<Organization> organizations = superHuman.getOrganizations();

        for (Organization currentOrg : organizations) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_ORGS,
                    superHumanId,
                    currentOrg.getOrganizationId());
        }
    }

    private void insertSuperHumanXPowers(SuperHuman superHuman) {
        final int superHumanId = superHuman.getSuperHumanId();
        final List<SuperPower> superPowers = superHuman.getSuperPowers();

        for (SuperPower currentSuperPower : superPowers) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_POWERS,
                    superHumanId,
                    currentSuperPower.getPowerTypeId());
        }
    }

    private void insertSuperHumanXSightings(SuperHuman superHuman) {
        final int superHumanId = superHuman.getSuperHumanId();
        final List<Sighting> sightings = superHuman.getSightings();

        for (Sighting currentSighting : sightings) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_SIGHTINGS,
                    superHumanId,
                    currentSighting.getSightingId());
        }
    }

    private List<Organization> findOrgsForSuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_SELECT_ORGS_BY_SUPERHUMAN_ID,
                new OrganizationMapper(),
                superHuman.getSuperHumanId());
    }

    private List<SuperPower> findPowersForSuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHUMAN_ID,
                new SuperPowerMapper(),
                superHuman.getSuperHumanId());
    }

    private List<Sighting> findSightingsForSuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPERHUMAN_ID,
                new SightingMapper(),
                superHuman.getSuperHumanId());
    }

    private List<SuperHuman> associatePowersSightingsAndOrgsWithSuperHuman(List<SuperHuman> superHumanList) {
        // set the complete list of org ids for each Super Human
        for (SuperHuman currentSH : superHumanList) {
            // add orgs to current Super Human
            currentSH.setOrganizations(findOrgsForSuperHuman(currentSH));
            // add super powers to current Super Human
            currentSH.setSuperPowers(findPowersForSuperHuman(currentSH));
            // add sightings to current Super Human
            currentSH.setSightings(findSightingsForSuperHuman(currentSH));
        }
        return superHumanList;
    }

    // Mapper for SuperHuman class
    private static final class SuperHumanMapper implements RowMapper<SuperHuman> {

        @Override
        public SuperHuman mapRow(ResultSet rs, int i) throws SQLException {
            SuperHuman sh = new SuperHuman();
            sh.setSuperHumanId(rs.getInt("superhuman_id"));
            sh.setSuperHumanName(rs.getString("superhuman_name"));
            sh.setDescription(rs.getString("description"));
            return sh;
        }
    }

    // Mapper for Organization class
    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organization_id"));
            org.setOrganizationName(rs.getString("organization_name"));
            org.setDescription(rs.getString("description"));
            org.setStreet(rs.getString("street"));
            org.setCity(rs.getString("city"));
            org.setState(rs.getString("state"));
            org.setZip(rs.getString("zip"));
            org.setPhone(rs.getString("phone"));
            return org;
        }
    }

    // Mapper for Sighting class
    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sighting_id"));
            s.setName(rs.getString("sighting_name"));
            s.setDescription(rs.getString("description"));
            s.setStreet(rs.getString("street"));
            s.setCity(rs.getString("city"));
            s.setState(rs.getString("state"));
            s.setZip(rs.getString("zip"));
            s.setLatitude(rs.getBigDecimal("latitude"));
            s.setLongitude(rs.getBigDecimal("longitude"));
            s.setSightingDate (rs.getDate("sighting_date").toLocalDate());
            //NOTES on the SQL Date to LocalDAte conversion from: https://stackoverflow.com/questions/29168494/how-to-convert-localdate-to-sql-date-java?noredirect=1&lq=1
            return s;
        }
    }

    // Mapper for SuperPower class
    private static final class SuperPowerMapper implements RowMapper<SuperPower> {

        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower sp = new SuperPower();
            sp.setPowerTypeId(rs.getInt("powertype_id"));
            sp.setName(rs.getString("power_type"));
            sp.setDescription(rs.getString("description"));
            return sp;
        }
    }
    
    // NEW BRIDGE METHODS
    // ====================

    @Override
    public void insertSupersXSightingsConnect(int superId, int sightingId) {
        // Remove all supers from the organization
        //jdbcTemplate.update(SQL_DELETE_SUPERS_SIGHTINGS, sightingId);
        // Insert new List of supers at the sighting
        jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_SIGHTINGS,
                superId,
                sightingId);
    }
    
    @Override
    public void deleteSupersSighting(int sightingId) {
        // Remove all supers from the sighting
        jdbcTemplate.update(SQL_DELETE_SUPERS_SIGHTINGS, sightingId);
    }
    
    @Override
    public void insertSupersXOrganizationsConnect(int superId, int orgId) {
        // Remove all supers from the organization
        jdbcTemplate.update(SQL_DELETE_SUPERS_ORGANIZATIONS, orgId);
        // Insert new List of supers at the Organization
        jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_ORGS,
                superId,
                orgId);
    }
    
    @Override
    public void deleteSupersOrganization(int orgId) {
        // Remove all supers from the organization
        jdbcTemplate.update(SQL_DELETE_SUPERS_ORGANIZATIONS, orgId);
    }
    
    @Override
    public void insertSupersXPowersConnect(int powerTypeId, int shId) {
        // Remove all supers from the sighting
        //jdbcTemplate.update(SQL_DELETE_SUPERS_POWERS, shId);
        // Insert new List of supers at the sighting
        jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_POWERS,
                shId,
                powerTypeId);
    }
    
    @Override
    public void deleteSupersPowers(int superHumanId) {
        // Remove all supers from the organization
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_POWERS, superHumanId);
    }
    
}
