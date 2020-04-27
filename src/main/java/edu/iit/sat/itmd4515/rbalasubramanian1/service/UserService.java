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
 *
 * @author raghul
 */
@Stateless
public class UserService extends AbstractService<User> {

    /**
     *
     */
    public UserService() {
        super(User.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", entityClass).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return em.find(User.class, username);
    }
}
