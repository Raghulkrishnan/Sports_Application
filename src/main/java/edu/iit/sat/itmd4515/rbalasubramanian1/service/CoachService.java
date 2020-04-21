/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author raghul
 */
@Stateless
public class CoachService {

    @PersistenceContext(name = "itmd4515PU")
    EntityManager em;
    
    public CoachService() {
    }
    
    public void create(Coach c){
        em.persist(c);
    }
    
    public void update(Coach c){
        em.merge(c);
    }
    
    public void remove(Coach c){
        em.remove(em.merge(c));
    }
    
    public Coach find(Long id){
        return em.find(Coach.class, id);
    }
    
    public List<Coach> findAll(){
//        TypedQuery tq = em.createNamedQuery("Coach.findAll", Coach.class);
//        return tq.getResultList();
        
        return em.createNamedQuery("Coach.findAll", Coach.class).getResultList();
    }
    
    public Coach findByUsername(String username){
        return em.createNamedQuery("Coach.findByUserName", Coach.class)
                .setParameter("userName", username)
                .getSingleResult();
    }
    
}
