/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author raghul
 */
@Entity
@NamedQuery(name = "Venue.findAll", query = "select v from Venue v")
public class Venue extends AbstractEntity {
    
//  inverse side of unidirectional ManyToOne/OneToMany
    @OneToMany(mappedBy = "venue")
    private List<Game> games = new ArrayList<>();
    
    @OneToOne
    private VenueOwner venueOwner;
    
    private String venueName;

    /**
     *
     */
    public Venue() {
    }

    /**
     *
     * @param venueName
     */
    public Venue(String venueName) {
        this.venueName = venueName;
    }
    
//    add venue owner helper method

    /**
     *
     * @param vo
     */
    public void addVenueOwner(VenueOwner vo){
        if(this.venueOwner == null || this.venueOwner != vo){
            this.setVenueOwner(vo);
        }
        if(vo.getVenue() == null){
            vo.setVenue(this);
        }
    }
  
//    remove venue owner helper method

    /**
     *
     * @param vo
     */
    public void removeVenueOwner(VenueOwner vo){
        if(this.venueOwner != null){
            this.setVenueOwner(null);
        }
        if(vo.getVenue() != null){
            vo.setVenue(null);
        }
    }
    
    /**
     * Get the value of games
     *
     * @return the value of games
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * Set the value of games
     *
     * @param games new value of games
     */
    public void setGames(List<Game> games) {
        this.games = games;
    }

    /**
     *
     * @return
     */
    public VenueOwner getVenueOwner() {
        return venueOwner;
    }

    /**
     *
     * @param venueOwner
     */
    public void setVenueOwner(VenueOwner venueOwner) {
        this.venueOwner = venueOwner;
    }

    /**
     *
     * @return
     */
    public String getVenueName() {
        return venueName;
    }

    /**
     *
     * @param venueName
     */
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @Override
    public String toString() {
        return "Venue{"+ "id=" + id +", venueName=" + venueName + '}';
    }


}
