/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.SuperHuman;
import com.sg.herodb.model.SuperPower;
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
public class SuperPowerDaoImpl implements SuperPowerDaoInterface{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
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

    private static final String SQL_DELETE_SUPERPOWER_BRIDGE_CONNECT
            = "DELETE FROM superhuman_x_powers WHERE powertype_id = ?";
    
    // SUPER POWER METHODS
    // ====================
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
        // delete the superhuman_x_powers relationship
        jdbcTemplate.update(SQL_DELETE_SUPERPOWER_BRIDGE_CONNECT, powerTypeId);
        // delete super power
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
    
    @Override
    public List<SuperPower> findPowersForSuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_SELECT_SUPERPOWERS_BY_SUPERHUMAN_ID,
                new SuperPowerMapper(),
                superHuman.getSuperHumanId());
    }
    
    // Mapper for SuperPower class
    //============================
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
    
}
