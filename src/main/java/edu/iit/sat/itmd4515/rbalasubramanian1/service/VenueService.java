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
 *
 * @author raghul
 */
@Named
@Stateless
public class VenueService extends AbstractService<Venue> {

    /**
     *
     */
    public VenueService() {
        super(Venue.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Venue> findAll() {
        return em.createNamedQuery("Venue.findAll", entityClass).getResultList();
    }
    
    public void deleteVenue(Venue v){
        Venue currentRowFromDatabase = em.find(Venue.class, v.getId());
        em.remove(currentRowFromDatabase);
    } 
    
    public void removeOwnerFromVenue(Venue v, VenueOwner vo){
        Venue currentRowFromDatabase = em.find(Venue.class, v.getId());
        VenueOwner currentRow = em.find(VenueOwner.class, vo.getId());
        
        currentRowFromDatabase.removeVenueOwner(vo);
        em.merge(currentRowFromDatabase);
    }
    
    public void removeGameFromVenue(Venue v, List<Game> games){
        Venue currentRowFromDatabase = em.find(Venue.class, v.getId());
        
        for(Game g : games){
            g = em.getReference(Game.class, g.getId());
            g.removeVenue(currentRowFromDatabase);
            em.merge(g);
            em.remove(g);
        }
        
    }
}
