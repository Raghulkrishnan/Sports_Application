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
    
    @ManyToOne
    private VenueOwner venueOwner;
    
    private String venueName;

    public Venue() {
    }

    public Venue(String venueName) {
        this.venueName = venueName;
    }
    
//    add venue owner helper method
    public void addVenueOwner(VenueOwner vo){
        if(this.venueOwner == null || this.venueOwner != vo){
            this.setVenueOwner(vo);
        }
        if(!vo.getVenues().contains(this)){
            vo.getVenues().add(this);
        }
    }
  
//    remove venue owner helper method
    public void removeVenueOwner(VenueOwner vo){
        if(this.venueOwner != null){
            this.setVenueOwner(null);
        }
        if(vo.getVenues().contains(this)){
            vo.getVenues().remove(this);
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
    public VenueOwner getVenueOwner() {
        return venueOwner;
    }
    public void setVenueOwner(VenueOwner venueOwner) {
        this.venueOwner = venueOwner;
    }
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }


}
