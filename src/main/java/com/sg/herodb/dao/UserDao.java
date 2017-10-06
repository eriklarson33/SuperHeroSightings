/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.dao;

import com.sg.herodb.model.User;
import java.util.List;

/**
 *
 * @author eriklarson-laptop
 */
public interface UserDao {

    public User addUser(User newUser);

    public void deleteUser(String username);

    public List<User> getAllUsers();
    
    public User getUserById(int userId);
    
    public void deleteUserAuthorities(User user);
    
    public void updateUserAuthorities(User user);

}
