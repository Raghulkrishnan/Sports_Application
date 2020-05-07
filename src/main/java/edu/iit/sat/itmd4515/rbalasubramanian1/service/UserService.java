/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
import java.util.List;
import javax.ejb.Stateless;

/**
 *UserService class can be used to perform operations on the user table
 * @author raghul
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     *default constructor
     */
    public UserService() {
        super(User.class);
    }

    /**
     *returns the list of users available
     * @return
     */
    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", entityClass).getResultList();
    }
    
    /**
     *returns the user object that matches the username
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return em.find(User.class, username);
    }
}
