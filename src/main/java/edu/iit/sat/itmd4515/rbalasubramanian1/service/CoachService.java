/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
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
    
    /**
     *
     */
    public CoachService() {
    }
    
    /**
     *
     * @param c
     */
    public void create(Coach c){
        em.persist(c);
    }
    
    /**
     *
     * @param c
     */
    public void update(Coach c){
        em.merge(c);
    }
    
    /**
     *
     * @param c
     */
    public void remove(Coach c){
        em.remove(em.merge(c));
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Coach find(Long id){
        return em.find(Coach.class, id);
    }
    
    /**
     *
     * @return
     */
    public List<Coach> findAll(){
//        TypedQuery tq = em.createNamedQuery("Coach.findAll", Coach.class);
//        return tq.getResultList();
        
        return em.createNamedQuery("Coach.findAll", Coach.class).getResultList();
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Coach findByUsername(String username){
        return em.createNamedQuery("Coach.findByUserName", Coach.class)
                .setParameter("userName", username)
                .getSingleResult();
    }
    
    public void editCoach(Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        
        currentRowFromDatabase.setFirstName(c.getFirstName());
        currentRowFromDatabase.setLastName(c.getLastName());
        currentRowFromDatabase.setDateOfJoining(c.getDateOfJoining());
        em.merge(currentRowFromDatabase);
    }
    
    public void editTeamInfo(Coach c) {
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        Team rowFromDB = em.find(Team.class, c.getTeam().getId());
        
        currentRowFromDatabase.setFirstName(c.getFirstName());
        currentRowFromDatabase.setLastName(c.getLastName());
        
        rowFromDB.setTeamName(c.getTeam().getTeamName());
        rowFromDB.setCaptainName(c.getTeam().getCaptainName());
        rowFromDB.setContact(c.getTeam().getContact());
        
        
        em.merge(currentRowFromDatabase);
        em.merge(rowFromDB);
    }
    
    public void deleteCoach(Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        em.remove(currentRowFromDatabase);
    } 
}
