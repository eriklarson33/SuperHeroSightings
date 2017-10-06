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
import java.util.Collections;
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
public class SuperHumanDaoImpl implements SuperHumanDaoInterface {
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate (JdbcTemplate jdbcTemplate) {
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
            = "DELETE FROM superhuman_x_powers WHERE superhuman_id = ?";

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
    
    private static final String SQL_DELETE_SUPERS_SIGHTINGS
            = "DELETE FROM superhuman_x_sightings WHERE sighting_id = ?";
    
    private static final String SQL_DELETE_SUPERS_ORGANIZATIONS
            = "DELETE FROM superhuman_x_orgs WHERE organization_id = ?";
    
    // SuperHuman Class Methods
    //=========================
    
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
            
//            // get the organizations for the current SuperHuman & set the list
//            superHuman.setOrganizations(findOrgsForSuperHuman(superHuman));
//            // get the powers for the current SuperHuman & set the list
//            superHuman.setSuperPowers(findPowersForSuperHuman(superHuman));
//            // get the sightings for the current SuperHuman & set the list
//            superHuman.setSightings(findSightingsForSuperHuman(superHuman));
//
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
        return superHumanList;
//        // set the orgs, super Powers, and sightings for each Super Human
//        return associatePowersSightingsAndOrgsWithSuperHuman(superHumanList);
    }

    @Override
    public List<SuperHuman> getAllSuperHumansByOrg(int organizationId) {
        // get the super heroes associated with this organization
        List<SuperHuman> superHumanList
                = jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_ORG_ID,
                        new SuperHumanMapper(),
                        organizationId);
        return superHumanList;
//        //  set the orgs, super Powers, and sightings for each Super Human
//        return associatePowersSightingsAndOrgsWithSuperHuman(superHumanList);
    }

    @Override
    public List<SuperHuman> getAllSuperHumansBySighting(int sightingId) {
        // get all super Humans associated with this sighting
        List<SuperHuman> superHumanList
                = jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_SIGHTING_ID,
                        new SuperHumanMapper(),
                      //  new SightingMapper(),
                        sightingId);
        return superHumanList;
//        //  set the orgs, super Powers, and sightings for each Super Human
//        return associatePowersSightingsAndOrgsWithSuperHuman(superHumanList);
    }

    @Override
    public void insertSupersXSightingsConnect(int superId, int sightingId) {
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
    public void insertSupersXPowersConnect(int powerTypeId, int superId) {
        jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_POWERS,
                superId,
                powerTypeId);
    }

    @Override
    public void deleteSupersPowers(int superHumanId) {
        // Remove all supers from the organization
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_X_POWERS, superHumanId);
    }
    
   // HELPER METHODS
    // ==============
    // Following Helper Methods update Bridge Tables via the SuperHuman class
    private void insertSuperHumanXOrgs(SuperHuman superHuman) {
        final int superHumanId = superHuman.getSuperHumanId();
        List<Organization> organizations = superHuman.getOrganizations();
        
        // if null, return an empty list to avoid NullPointerExceptions
        if (null == organizations) {
            organizations = Collections.emptyList();
        }
        
        for (Organization currentOrg : organizations) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_ORGS,
                    superHumanId,
                    currentOrg.getOrganizationId());
        }
    }

    private void insertSuperHumanXPowers(SuperHuman superHuman) {
        final int superHumanId = superHuman.getSuperHumanId();
        List<SuperPower> superPowers = superHuman.getSuperPowers();
        
        // if null, return an empty list to avoid NullPointerExceptions
        if (superPowers == null) {
            superPowers = Collections.emptyList();
        }
        
        for (SuperPower currentSuperPower : superPowers) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_POWERS,
                    superHumanId,
                    currentSuperPower.getPowerTypeId());
        }
    }

    private void insertSuperHumanXSightings(SuperHuman superHuman) {
        final int superHumanId = superHuman.getSuperHumanId();
        List<Sighting> sightings = superHuman.getSightings();

        // if null, return an empty list to avoid NullPointerExceptions
        if (sightings == null) {
            sightings = Collections.emptyList();
        }
        
        for (Sighting currentSighting : sightings) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_X_SIGHTINGS,
                    superHumanId,
                    currentSighting.getSightingId());
        }
    }

//    private List<Organization> findOrgsForSuperHuman(SuperHuman superHuman) {
//        return jdbcTemplate.query(SQL_SELECT_ORGS_BY_SUPERHUMAN_ID,
//                new HeroDaoJDBCTemplateImpl.OrganizationMapper(),
//                superHuman.getSuperHumanId());
//    }

//    private List<SuperPower> findPowersForSuperHuman(SuperHuman superHuman) {
//        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHUMAN_ID,
//                new HeroDaoJDBCTemplateImpl.SuperPowerMapper(),
//                superHuman.getSuperHumanId());
//    }

//    private List<Sighting> findSightingsForSuperHuman(SuperHuman superHuman) {
//        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPERHUMAN_ID,
//                new HeroDaoJDBCTemplateImpl.SightingMapper(),
//                superHuman.getSuperHumanId());
//    }

//    private List<SuperHuman> associatePowersSightingsAndOrgsWithSuperHuman(List<SuperHuman> superHumanList) {
//        // set the complete list of org ids for each Super Human
//        for (SuperHuman currentSH : superHumanList) {
//            // add orgs to current Super Human
//            currentSH.setOrganizations(findOrgsForSuperHuman(currentSH));
//            // add super powers to current Super Human
//            currentSH.setSuperPowers(findPowersForSuperHuman(currentSH));
//            // add sightings to current Super Human
//            currentSH.setSightings(findSightingsForSuperHuman(currentSH));
//        }
//        return superHumanList;
//    }
    
    // Mapper for SuperHuman class
    //============================
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
    
}
