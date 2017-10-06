/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.herodb.controller;

import com.sg.herodb.model.User;
import com.sg.herodb.serviceLayer.ServiceLayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eriklarson-laptop
 */
@Controller
public class UserController {

    private ServiceLayer service;
    private PasswordEncoder encoder;

    @Inject
    public UserController(ServiceLayer service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    // This endpoint retrieves all users from the database and puts the
    // List of users on the model
    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List<User> usersFromDao = service.getAllUsers();
        List<User> untouchables = new ArrayList<>();
        List<User> users = new ArrayList<>();

        for (User currentUser : usersFromDao) {
            if ((currentUser.getUsername()).equalsIgnoreCase("admin")) {
                untouchables.add(currentUser);
            } else {
                users.add(currentUser);
            }

        }
        model.put("untouchables", untouchables);
        model.put("users", users);
        return "displayUserList";
    }
    // This endpoint just displays the Add User form

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUserForm";
    }
    // This endpoint processes the form data and creates a new User

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();
        // This example uses a plain HTML form so we must get values 
        // from the request
        newUser.setUsername(req.getParameter("username"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin 
        // box is checked
        newUser.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            newUser.addAuthority("ROLE_ADMIN");
        }

        service.addUser(newUser);

        return "redirect:displayUserList";
    }

    // This endpoint deletes the specified User
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deletUser(@RequestParam("username") String username,
            Map<String, Object> model) {
        service.deleteUser(username);
        return "redirect:displayUserList";
    }

    @RequestMapping(value = "/displayEditUser", method = RequestMethod.GET)
    public String displayEditUser(HttpServletRequest request, Model model) {
        String userIdParameter = request.getParameter("userId");
        int sidekickId = Integer.parseInt(userIdParameter);

        // get the profile information of the selected Super
        //SuperHuman superProfile = service.getSuperHumanById(superId);
        User userProfile = service.getUserById(sidekickId);

        model.addAttribute("userData", userProfile);

        return "editUser";
    }

    @RequestMapping(value = "/editUserAuthority", method = RequestMethod.POST)
    public String editUser(HttpServletRequest req, Model model) {
        String userIdParameter = req.getParameter("userId");
        int sidekickId = Integer.parseInt(userIdParameter);
        // This example uses a plain HTML form so we must get values 
        // from the request
        User userToEdit = service.getUserById(sidekickId);
        String usernameToEdit = userToEdit.getUsername();

        // Remove previous roles authorized
        service.deleteUserAuthorities(userToEdit);

        // All users have ROLE_USER, only add ROLE_ADMIN if the isAdmin 
        // box is checked
//        List<String> emptyAuthorities = userToEdit.getAuthorities();
//        userToEdit.setAuthorities(emptyAuthorities.clear());
//        emptyAuthorities = Collections.EMPTY_LIST;
//        userToEdit.setAuthorities((ArrayList<String>) emptyAuthorities);
        userToEdit.addAuthority("ROLE_USER");
        if (null != req.getParameter("isAdmin")) {
            userToEdit.addAuthority("ROLE_ADMIN");
        }

        service.updateUserAuthorities(userToEdit);

        return "redirect:displayUserList";
    }

}
