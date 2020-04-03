/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
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

    public TeamService() {
        super(Team.class);
    }

    @Override
    public List<Team> findAll() {
        return em.createNamedQuery("Team.findAll", entityClass).getResultList();
    }
    
}
