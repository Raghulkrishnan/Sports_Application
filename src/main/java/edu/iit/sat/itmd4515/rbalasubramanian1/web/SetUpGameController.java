/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Game;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.CoachService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.GameService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *This is a backing bean to handle Game functionality to teams.
 * A game is not assigned to all the teams by default.
 * A coach of a team, sets up a game against another team for a given date.
 * 
 * @author raghul
 */
@Named
@RequestScoped
public class SetUpGameController {
    private static final Logger LOG = Logger.getLogger(SetUpGameController.class.getName());
    
    private Game game;
    private Coach coach;
    
    private Team ourTeam;
    private Team opponentTeamSelected;
    private Venue venueSelected;
    
    @EJB CoachService coachServ;
    @EJB GameService gameServ;
    
    @Inject LoginController loginController;


    public SetUpGameController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        game = new Game();
        opponentTeamSelected = new Team();
        venueSelected = new Venue();
//      Find the coach entity of the logged in user.
        coach = coachServ.findByUsername(loginController.getUserName());
        ourTeam = coach.getTeam();
        LOG.info("Our team iss... " + ourTeam);
    }
    
    public void initGameById(){
//        List<Team> teamsInGame = new ArrayList<>();
        
        LOG.info("edit game..." + this.game.getId());
        game = gameServ.find(this.game.getId());
        
//        game.getTeams().forEach((t) -> {
//            teamsInGame.add(t);
//        });
        
        LOG.info("edit game...after find!!!" + this.game.toString());
    }
    
    public List<Game> getOurTeamGames(){
        List<Game> ourTeamGames= new ArrayList<>();
        Team t = coach.getTeam();
        
        gameServ.findAll().forEach((g) -> {
            g.getTeams().forEach((t1) ->{   
                if(t1.getId().equals(t.getId())){
                    ourTeamGames.add(g);
                }
            });
        });
        LOG.info("Our games---->> " + ourTeamGames);
        return ourTeamGames;
    }
    
//    action methods
    public String saveGame(){
        LOG.info("Inside saveGame method with " + game.toString());
        gameServ.addTeamForGame(game, opponentTeamSelected);
        gameServ.addVenueForGame(game, venueSelected);
        
        gameServ.addTeamForGame(game, ourTeam);
        
        gameServ.create(game);
  
//        get the data to this page while navigating!!
//    can be done by - 1. refresh the instance of coach from the service OR
//        coach = coachServ.findByUsername(loginController.getUserName());
//  2 - force JSF initiate a new HTTP req/res cycle
        return "/coach/welcome.xhtml?faces-redirect=true";
    }
    
    public String editGame(){
//        need to implement edit functionality
        gameServ.addTeamForGame(game, ourTeam);
        gameServ.addTeamForGame(game, opponentTeamSelected);
        gameServ.editGame(game);
        return "/coach/welcome.xhtml";
    }
    
    public String deleteGame(){
        LOG.info("Inside deleteGame method with " + game.toString());
//        need to implement delete functionality
        gameServ.deleteGame(game);
        return "/coach/welcome.xhtml";
    }
    
    public String addStatToTeam(Team t){
        LOG.info("Stats of the team.." + t.toString());
        this.setOurTeam(t);
        return "/coach/teamStat.xhtml";
    }
    
    
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public Coach getCoach() {
        return coach;
    }
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    public Team getOurTeam() {
        return ourTeam;
    }
    public void setOurTeam(Team ourTeam) {
        this.ourTeam = ourTeam;
    }
    public Team getOpponentTeamSelected() {
        return opponentTeamSelected;
    }
    public void setOpponentTeamSelected(Team opponentTeamSelected) {
        this.opponentTeamSelected = opponentTeamSelected;
    }
    public Venue getVenueSelected() {
        return venueSelected;
    }
    public void setVenueSelected(Venue venueSelected) {
        this.venueSelected = venueSelected;
    }
}
