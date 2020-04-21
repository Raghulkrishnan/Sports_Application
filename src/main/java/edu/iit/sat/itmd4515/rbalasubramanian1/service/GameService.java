/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Game;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author raghul
 */
@Stateless
public class GameService extends AbstractService<Game> {

    /**
     *
     */
    public GameService() {
        super(Game.class);
    }

    /**
     *Returns the list of games in the db by calling the named query in the game class
     * @return
     */
    @Override
    public List<Game> findAll() {
        return em.createNamedQuery("Game.findAll", entityClass).getResultList();
    }
    
    /**
     *Add a team to the game. Manages both sides of relationships.
     * 
     * @param g
     * @param t
     */
    public void addTeamForGame(Game g, Team t){
        Team managedEntityTeam = em.getReference(Team.class, t.getId());
        g.addTeam(t);
//        create(g);
    }
    /**
     *Add a venue to the game. Manages both sides of relationships.
     * 
     * @param g
     * @param v
     */

    public void addVenueForGame(Game g, Venue v){
        Venue managedEntityVenue = em.getReference(Venue.class, v.getId());
        g.addVenue(v);
//        create(g);
    }
}
