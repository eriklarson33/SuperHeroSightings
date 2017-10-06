/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoDbImpl implements UserDao {

    private static final String SQL_INSERT_USER
            = "insert into users (username, password, enabled) values (?, ?, 1)";
    private static final String SQL_INSERT_AUTHORITY
            = "insert into authorities (username, authority) values (?, ?)";
    private static final String SQL_DELETE_USER
            = "delete from users where username = ?";
    private static final String SQL_DELETE_AUTHORITIES
            = "delete from authorities where username = ?";
    private static final String SQL_GET_ALL_USERS
            = "select * from users";
    private static final String SQL_GET_USER_DATA
            = "SELECT * FROM users WHERE user_id = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User newUser) {
        jdbcTemplate.update(SQL_INSERT_USER,
                newUser.getUsername(),
                newUser.getPassword());
        newUser.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        // now insert user's roles
        ArrayList<String> authorities = newUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                    newUser.getUsername(),
                    authority);
        }

        return newUser;
    }

    @Override
    public void deleteUser(String username) {
        // first delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        // second delete the user
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }

    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
    
    @Override
    public User getUserById(int userId) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_GET_USER_DATA, 
                    new UserMapper(),
                    userId);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public void deleteUserAuthorities(User user) {
        // get the username
        String usernameToEdit = user.getUsername();
        // first delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, usernameToEdit);
        
    }
    
    @Override
    public void updateUserAuthorities(User user) {
        // delete the previous Authorities associated with the user
        deleteUserAuthorities(user);
        
         // now insert user's updated roles/authorities
        ArrayList<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                    user.getUsername(),
                    authority);
        }
    }

}
