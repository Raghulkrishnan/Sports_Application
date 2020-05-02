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
 *
 * @author raghul
 */
@Named
@Stateless
public class TeamService extends AbstractService<Team> {

    /**
     *
     */
    public TeamService() {
        super(Team.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Team> findAll() {
        return em.createNamedQuery("Team.findAll", entityClass).getResultList();
    }
    
    public void deleteTeam(Team t){
        Team currentRowFromDatabase = em.find(Team.class, t.getId());
        em.remove(currentRowFromDatabase);
    } 
    
    public void addCoachToTeam(Team t, Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        Team currentTeam = em.find(Team.class, t.getId());
        
        currentTeam.addCoach(c);
        em.merge(currentTeam);
    }  
    
    public void removeCoachFromTeam(Team t, Coach c){
        Coach currentRowFromDatabase = em.find(Coach.class, c.getId());
        Team currentTeam = em.find(Team.class, t.getId());
        
        currentTeam.removeCoach(c);
        em.merge(currentTeam);
    }  
}
