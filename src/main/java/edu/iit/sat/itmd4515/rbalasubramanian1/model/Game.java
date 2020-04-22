/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author raghul
 */
@Entity
@NamedQuery(name = "Game.findAll", query = "select g from Game g")
public class Game extends AbstractEntity {

    private static final Logger LOG = Logger.getLogger(Game.class.getName());
    
//  unidirectional ManyToOne/ OneToMany
    @ManyToOne
    private Venue venue;
    
    private LocalDate dateOfGame;

//    directional ManyToMany
//    This side is the only side of the relationship and hence the owning side too. This has the foreign key
    
    @ManyToMany
    @JoinTable(name = "game_played_by_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "teams_id"))
    private List<Team> teams = new ArrayList<>();
    
    private Team wonBy;
    private Team lostBy;

    public Game() {

    }
    
//    add venue helper method
    public void addVenue(Venue v){
        if(this.venue == null){
            this.setVenue(v);
        }
        
//        check again
        if(!v.getGames().contains(this)){
            v.getGames().add(this);
        }
    }
  
//    remove venue helper method
    public void removeVenue(Venue v){
        if(this.venue != null){
            this.setVenue(null);
        }
        
//        check again
        if(v.getGames().contains(this)){
            v.getGames().remove(this);
        }
    }

//    add team helper method
    public void addTeam(Team t){
        if(!this.teams.contains(t)){
            this.teams.add(t);
        }
        //        check again
        if(!t.getGames().contains(this)){
            t.getGames().add(this);
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
        LOG.info("game is.." + this);
        LOG.info("date is.." + dateOfGame);
        this.dateOfGame = dateOfGame;
    }

    public Team getWonBy() {
        return wonBy;
    }
    public void setWonBy(Team wonBy) {
        this.wonBy = wonBy;
    }
    public Team getLostBy() {
        return lostBy;
    }
    public void setLostBy(Team lostBy) {
        this.lostBy = lostBy;
    }

    @Override 
    public String toString() {
        return "Game{" + "id=" + id + ", venue=" + venue + ", dateOfGame=" + dateOfGame + '}';
    }
}
