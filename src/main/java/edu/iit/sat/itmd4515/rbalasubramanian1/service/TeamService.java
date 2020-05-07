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
import javax.inject.Named;

/**
 *team service class to perform crud operations on the team table
 * @author raghul
 */
@Named
@Stateless
public class TeamService extends AbstractService<Team> {

    /**
     *default constructor
     */
    public TeamService() {
        super(Team.class);
    }

    /**
     *returns the entire list of teams present in the table
     * @return
     */
    @Override
    public List<Team> findAll() {
        return em.createNamedQuery("Team.findAll", entityClass).getResultList();
    }
    
    /**
     *used to remove a team record from the database
     * @param t
     */
    public void deleteTeam(Team t){
        Team currentRowFromDatabase = em.find(Team.class, t.getId());
        em.remove(currentRowFromDatabase);
    } 
    
    /**
     *used to add a coachIF foreign key to the team record.
     * @param t
     * @param c
     */
    public void addCoachToTeam(Team t, Coach c){
        Coach currentRow = em.find(Coach.class, c.getId());
        Team currentRowFromDatabase = em.find(Team.class, t.getId());
        
        
        currentRowFromDatabase.addCoach(c);
        currentRow.setTeam(t);
        
        em.merge(currentRow);
        em.merge(currentRowFromDatabase);
        
    }  
    
    /**
     *removes the foreign key coachId from the team record
     * @param t
     * @param c
     */
    public void removeCoachFromTeam(Team t, Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        Team currentTeam = em.find(Team.class, t.getId());
        
        currentTeam.removeCoach(c);
        em.merge(currentTeam);
    }  
}
