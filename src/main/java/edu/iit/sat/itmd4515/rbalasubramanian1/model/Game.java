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
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;

/**
 * The Game class is used to create the game entity in the database and extends the 
 * AbstractEntity class to set an ID and version against each game record.
 * @author raghul
 */
@Entity
@NamedQuery(name = "Game.findAll", query = "select g from Game g")
@NamedQuery(name = "Game.findById", query = "select g from Game g where g.id = :id")
public class Game extends AbstractEntity {

    private static final Logger LOG = Logger.getLogger(Game.class.getName());
    
//  unidirectional ManyToOne/ OneToMany
    @ManyToOne
    private Venue venue;
    
    @FutureOrPresent
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

    /**
     * Game() is the default Game class constructor
     */
    public Game() {

    }
    
//    add venue helper method

    /**
     * The addVenue method is used to add a venue for the game as it has a Many to One relationship with the Venue entity.
     * 
     * @param v
     */
    public void addVenue(Venue v){
//        if(this.venue == null){
            this.setVenue(v);
//        }
        
//        check again
        if(!v.getGames().contains(this)){
            v.getGames().add(this);
        }
    }
  
//    remove venue helper method

    /**
     * The removeVenue method is used to remove the venue object from the game-venue relationship.
     * @param v
     */
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

    /**
     * The addTeam method accepts a team object and is used to add a team to the game as game and team entities
     * have a many to many relationship.
     * @param t
     */
    public void addTeam(Team t){
        if(!this.teams.contains(t) && this.teams.size() < 2){
            this.teams.add(t);
        }
        else{
            this.removeGameTeams();
            this.teams.add(t);
        }
        //        check again
        if(!t.getGames().contains(this)){
            t.getGames().add(this);
        }
    }
  
//    remove team helper method

    /**
     * The removeTeam method accepts a team object and removes that team from the game-team relationship
     * @param t
     */
    public void removeTeam(Team t){
        if(this.teams.contains(t)){
            this.teams.remove(t);
        }
        //        check again
        if(t.getGames().contains(this)){
            t.getGames().remove(this);
        }
    }
    
    /**
     *
     */
    public void removeGameTeams(){
        LOG.info("aaaaaaaaa----" + this.getTeams());
        for(Team t : this.getTeams()){
            this.teams.remove(t);
            LOG.info("dsfisdnfiuhuwehfo----" + this.teams);
        } 
    }
    
    /**
     * The Game constructor with date parameter.
     * @param ld
     */
    public Game(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }
    
    /**
     * The getVenue method is used to get the value of venue
     *
     * @return the value of venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * The setVenue method is used to set the value of venue
     *
     * @param venue new value of venue
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * getTeams method returns the list of teams of a game.
     * @return
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * setTeams method is used to set a list of teams in a game.
     * @param teams
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    /**
     * getDateOfGame is used to get the dateOfGame value of the game entity.
     * @return
     */
    public LocalDate getDateOfGame() {
        return dateOfGame;
    }
    
    /**
     * setDateOfGame is used to set the dateOfGame value of the game entity.
     * @param dateOfGame
     */
    public void setDateOfGame(LocalDate dateOfGame) {
        LOG.info("game is.." + this);
        LOG.info("date is.." + dateOfGame);
        this.dateOfGame = dateOfGame;
    }

    /**
     * It is used to get the wonBy value of a game - the value is a team object.
     * @return
     */
    public Team getWonBy() {
        return wonBy;
    }

    /**
     *It is used to set the wonBy value of a game - the value is a team object.
     * @param wonBy
     */
    public void setWonBy(Team wonBy) {
        this.wonBy = wonBy;
    }

    /**
     *It is used to get the lost value of a game - the value is a team object.
     * @return
     */
    public Team getLostBy() {
        return lostBy;
    }

    /**
     *It is used to set the lostBy value of a game - the value is a team object.
     * @param lostBy
     */
    public void setLostBy(Team lostBy) {
        this.lostBy = lostBy;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ",dateOfGame=" + dateOfGame + ", wonBy=" + wonBy + ", lostBy=" + lostBy + '}';
    }
}
