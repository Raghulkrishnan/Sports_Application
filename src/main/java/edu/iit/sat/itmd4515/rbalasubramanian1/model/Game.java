/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDate;
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
    
//  unidirectional ManyToOne/ OneToMany
    @ManyToOne
    private Venue venue;
    
    private LocalDate dateOfGame;

//    Unidirectional ManyToMany
//    This side is the only side of the relationship and hence the owning side too. This has the foreign key
    @ManyToMany
    @JoinTable(name = "game_played_by_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "teams_id"))
    private List<Team> teams = new ArrayList<>();

    public Game() {

    }
    
//    add venue helper method
    public void addVenue(Venue v){
        if(this.venue == null){
            this.setVenue(v);
        }
//        if(!v.getGames().contains(this)){
//            v.getGames().add(this);
//        }
    }
  
//    remove venue helper method
    public void removeVenue(Venue v){
        if(this.venue != null){
            this.setVenue(null);
        }
//        if(v.getGames().contains(this)){
//            v.getGames().remove(this);
//        }
    }

//    add team helper method
    public void addTeam(Team t){
        if(!this.teams.contains(t)){
            this.teams.add(t);
        }
    }
  
//    remove team helper method
    public void removeTeam(Team t){
        if(this.teams.contains(t)){
            this.teams.remove(t);
        }
    }
    
    public Game(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
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
    
    public LocalDate getDateOfGame() {
        return dateOfGame;
    }
    
    public void setDateOfGame(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

}
