/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Game;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 * GameService is used to perform crud operations on the game table in the db
 * @author raghul
 */
@Stateless
public class GameService extends AbstractService<Game> {

    private static final Logger LOG = Logger.getLogger(GameService.class.getName());

    /**
     *default constructor
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
     *returns game object based on ID
     * @param id
     * @return
     */
    public Game findById(Long id) {
         return em.createNamedQuery("Game.findById", Game.class)
                .setParameter("id", id)
                .getSingleResult();
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
    
    /**
     * edits the game record by adding values to the wonBy and lostBy columns of the table
     * @param g
     */
    public void addResultToGame(Game g){
        Game currentRowFromDatabase = em.find(Game.class, g.getId());
        
        currentRowFromDatabase.setWonBy(g.getWonBy());
        currentRowFromDatabase.setLostBy(g.getLostBy());
        em.merge(currentRowFromDatabase);
    }
    
    /**
     *used to edit game record in the table
     * @param g
     */
    public void editGame(Game g){
//        same pattern as create
//        managed related entities being passed as a whole or with ID using get Ref
        
        Venue v = em.getReference(Venue.class, g.getVenue().getId());
        
//        the param is carrying the changed value. dont try merging it to the persistence context
//        Get the current row, set the changed values.
        Game currentRowFromDatabase = em.find(Game.class, g.getId());
        
        List<Team> teams = g.getTeams();
        for(Team t : teams){
//             managed related entities being passed as a whole or with ID using get Ref
            t = em.getReference(Team.class, t.getId());
//            now set the relationship
            LOG.info("new teams.." + t);
            currentRowFromDatabase.addTeam(t);
        }
//        now we have the current row. now set the relationship
        currentRowFromDatabase.addVenue(v);
//        and then set the possible changed values. in this case => date is the extra attribute
        currentRowFromDatabase.setDateOfGame(g.getDateOfGame());
        
        
        LOG.info("Finally-->" +v);
        LOG.info("Finally-->" +g);
        LOG.info("Finally-->" +currentRowFromDatabase);
        
//        for edit
        em.merge(currentRowFromDatabase);        
    }
    
    /**
     *deletes the game record from the Game table
     * @param g
     */
    public void deleteGame(Game g){
        Game currentRowFromDatabase = em.find(Game.class, g.getId());
        em.remove(currentRowFromDatabase);
    } 
    
}
