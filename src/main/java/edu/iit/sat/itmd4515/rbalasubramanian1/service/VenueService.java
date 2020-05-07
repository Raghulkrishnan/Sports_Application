/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Game;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *Used to perform CRUD operations on the venue table in the db
 * @author raghul
 */
@Named
@Stateless
public class VenueService extends AbstractService<Venue> {

    /**
     *default constructor
     */
    public VenueService() {
        super(Venue.class);
    }

    /**
     *returns all venues
     * @return
     */
    @Override
    public List<Venue> findAll() {
        return em.createNamedQuery("Venue.findAll", entityClass).getResultList();
    }
    
    /**
     *used to remove a venue object from the table
     * @param v
     */
    public void deleteVenue(Venue v){
        Venue currentRowFromDatabase = em.find(Venue.class, v.getId());
        em.remove(currentRowFromDatabase);
    } 
    
    /**
     *used to remove owner from the venue
     * @param v
     * @param vo
     */
    public void removeOwnerFromVenue(Venue v, VenueOwner vo){
        Venue currentRowFromDatabase = em.find(Venue.class, v.getId());
        VenueOwner currentRow = em.find(VenueOwner.class, vo.getId());
        
        currentRowFromDatabase.removeVenueOwner(vo);
        em.merge(currentRowFromDatabase);
    }
    
    /**
     *used to remove venue from the game
     * @param v
     * @param games
     */
    public void removeVenueFromGame(Venue v, List<Game> games){
        Venue currentRowFromDatabase = em.find(Venue.class, v.getId());
        
        for(Game g : games){
            g = em.getReference(Game.class, g.getId());
            g.removeVenue(currentRowFromDatabase);
            em.merge(g);
            em.remove(g);
        }
        
    }
    
    /**
     *used to add owner to the venue
     * @param v
     * @param vo
     */
    public void addOwnerToVenue(Venue v, VenueOwner vo){
        Venue currentRowFromDatabase = em.find(Venue.class, v.getId());
        VenueOwner currentRow = em.find(VenueOwner.class, vo.getId());
        
        currentRowFromDatabase.addVenueOwner(vo);
        currentRow.setVenue(v);
        em.merge(currentRow);
        em.merge(currentRowFromDatabase);
    }
}
