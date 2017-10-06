/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Organization;
import com.sg.herodb.model.SuperHuman;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class OrganizationDaoImpl implements OrganizationDaoInterface{
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
    public List<Organization> getAllOrganizations() {
        // get all the Organizations
        List<Organization> orgs = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
        return orgs;
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
    
    public List<Organization> findOrgsForSuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_SELECT_ORGS_BY_SUPERHUMAN_ID,
                new OrganizationMapper(),
                superHuman.getSuperHumanId());
    }
    
    // Mapper for Organization class
    //==============================
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
    
    
    
}
