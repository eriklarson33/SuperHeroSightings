/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.Sighting;
import com.sg.herodb.model.SuperHuman;
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
public class SightingDaoImpl implements SightingDaoInterface{
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
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
    
    private static final String SQL_DELETE_SUPERS_SIGHTINGS
            = "DELETE FROM superhuman_x_sightings WHERE sighting_id = ?";
    
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
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        try {
        // get all the Sightings
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                new SightingMapper(),
                date.toString());
        return sightings;
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
    public List<Sighting> findSightingsForSuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPERHUMAN_ID,
                new SightingMapper(),
                superHuman.getSuperHumanId());
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
    
}
