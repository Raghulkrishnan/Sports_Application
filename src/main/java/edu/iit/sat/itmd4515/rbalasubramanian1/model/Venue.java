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
import javax.persistence.OneToMany;

/**
 *
 * @author raghul
 */
@Entity
public class Venue extends AbstractEntity {

//  inverse side of bidirectional ManyToOne/OneToMany
    @OneToMany(mappedBy = "venue")
    private List<Game> games = new ArrayList<>();
    
    @ManyToOne
    private VenueOwner venueOwner;

    public Venue() {
    }
    
//    add game helper method
    public void addGame(Game g){
        if(!this.games.contains(g)){
            this.games.add(g);
        }
        if(g.getVenue() == null){
            g.setVenue(this);
        }
    }
  
//    remove game helper method
    public void removeGame(Game g){
        if(this.games.contains(g)){
            this.games.remove(g);
        }
        if(g.getVenue() != null){
            g.setVenue(null);
        }
    }

    public Venue(VenueOwner venueOwner) {
        this.venueOwner = venueOwner;
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


}
