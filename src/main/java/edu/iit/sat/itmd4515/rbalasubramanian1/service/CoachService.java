/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *Coach service has methods to perform CRUD operations in the coach table of the database.
 * @author raghul
 */
@Stateless
public class CoachService {

    @PersistenceContext(name = "itmd4515PU")
    EntityManager em;
    
    /**
     *default constructor
     */
    public CoachService() {
    }
    
    /**
     *create method that will create a new coach record in the database.
     * @param c
     */
    public void create(Coach c){
        em.persist(c);
    }
    
    /**
     *update method that will update a coach record in the database.
     * @param c
     */
    public void update(Coach c){
        em.merge(c);
    }
    
    /**
     *remove method is used to remove a row from the coach table in the db
     * @param c
     */
    public void remove(Coach c){
        em.remove(em.merge(c));
    }
    
    /**
     *find method finds and returns the coach object based on its id.
     * @param id
     * @return
     */
    public Coach find(Long id){
        return em.find(Coach.class, id);
    }
    
    /**
     *findAll method is used to return all the coach records in the table
     * @return
     */
    public List<Coach> findAll(){
//        TypedQuery tq = em.createNamedQuery("Coach.findAll", Coach.class);
//        return tq.getResultList();
        
        return em.createNamedQuery("Coach.findAll", Coach.class).getResultList();
    }
    
    /**
     *findByUsername method returns the coach object based on the username
     * @param username
     * @return
     */
    public Coach findByUsername(String username){
        return em.createNamedQuery("Coach.findByUserName", Coach.class)
                .setParameter("userName", username)
                .getSingleResult();
    }
    
    /**
     *editCoach method is used to edit the coach record in the table
     * @param c
     */
    public void editCoach(Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        
        currentRowFromDatabase.setFirstName(c.getFirstName());
        currentRowFromDatabase.setLastName(c.getLastName());
        currentRowFromDatabase.setDateOfJoining(c.getDateOfJoining());
        currentRowFromDatabase.setAge(c.getAge());
        
        em.merge(currentRowFromDatabase);
    }
    
    /**
     *edit team info is used to edit team details of the coach's team
     * @param c
     */
    public void editTeamInfo(Coach c) {
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        Team rowFromDB = em.find(Team.class, c.getTeam().getId());
        
        currentRowFromDatabase.setFirstName(c.getFirstName());
        currentRowFromDatabase.setLastName(c.getLastName());
        currentRowFromDatabase.setAge(c.getAge());
        
        rowFromDB.setTeamName(c.getTeam().getTeamName());
        rowFromDB.setCaptainName(c.getTeam().getCaptainName());
        rowFromDB.setContact(c.getTeam().getContact());
        
        
        em.merge(currentRowFromDatabase);
        em.merge(rowFromDB);
    }
    
    /**
     *this method is used to change the password of this coach user
     * @param u
     * @param c
     */
    public void editCoachPwd(User u, Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        
        currentRowFromDatabase.getUser().setPassword(u.getPassword());
        em.merge(currentRowFromDatabase);
    }
    
    /**
     *deleteCoach method removes the record from the table.
     * @param c
     */
    public void deleteCoach(Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        em.remove(currentRowFromDatabase);
    } 
}
