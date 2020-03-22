/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author raghul
 */
@Entity
public class Game extends AbstractEntity {
    
//  bidirectional ManyToOne/ OneToMany
    @ManyToOne
    private Venue venue;

//    Unidirectional ManyToMany
    @ManyToMany
    @JoinTable(name = "game_played_by_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "teams_id"))
    private List<Team> teams = new ArrayList<>();

    public Game() {

    }

    /**
     * Get the value of venue
     *
     * @return the value of venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Set the value of venue
     *
     * @param venue new value of venue
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public List<Team> getTeams() {
        return teams;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
}
