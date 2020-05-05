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
import sendEmail.SendEmail;

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

    /**
     *
     */
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
    
    /**
     *
     */
    public void initGameById(){
        LOG.info("edit game..." + this.game.getId());
        game = gameServ.find(this.game.getId());
                
        LOG.info("edit game...after find!!!" + this.game.toString());
    }
    
    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public String saveGame(){
        LOG.info("Inside saveGame method with " + game.toString());
        gameServ.addTeamForGame(game, opponentTeamSelected);
        gameServ.addVenueForGame(game, venueSelected);
        
        gameServ.addTeamForGame(game, ourTeam);
        
        gameServ.create(game);
  
//        send email notifying the teams about the game
        SendEmail.toAddress = opponentTeamSelected.getCoach().getUser().getUserName();
        SendEmail.messageToSend = "Hello from Sport Connect!"
                + "Your game has been scheduled. Scheduled by : " + coach.getUser().getUserName() + " ( " + coach.getFirstName()
                + " " + coach.getLastName() + " )"
                + "Game Info:---"
                + "Between: " + ourTeam.getTeamName() + " and " + opponentTeamSelected.getTeamName()
                + " on " + game.getDateOfGame()
                + " Thank You!!!";
        
        SendEmail.sendMailToCoach();
        
        
        SendEmail.toAddress = coach.getUser().getUserName();
        SendEmail.messageToSend = "Hello from Sport Connect!"
                + "Your game has been scheduled. Scheduled by : " + coach.getUser().getUserName() + " ( " + coach.getFirstName()
                + " " + coach.getLastName() + " )"
                + "Game Info:---"
                + "Between: " + ourTeam.getTeamName() + " and " + opponentTeamSelected.getTeamName()
                + " on " + game.getDateOfGame()
                + " Thank You!!!";
        
        SendEmail.sendMailToCoach();
        
//        get the data to this page while navigating!!
        return "/coach/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     *
     * @return
     */
    public String editGame(){
//        need to implement edit functionality
        gameServ.addTeamForGame(game, ourTeam);
        gameServ.addTeamForGame(game, opponentTeamSelected);
        gameServ.editGame(game);
        return "/coach/welcome.xhtml";
    }
    
    /**
     *
     * @return
     */
    public String deleteGame(){
        LOG.info("Inside deleteGame method with " + game.toString());
//        need to implement delete functionality
        gameServ.deleteGame(game);
        return "/coach/welcome.xhtml";
    }
    
    /**
     *
     * @param t
     * @return
     */
    public String addStatToTeam(Team t){
        LOG.info("Stats of the team.." + t.toString());
        this.setOurTeam(t);
        return "/coach/teamStat.xhtml";
    }
    
    /**
     *
     * @return
     */
    public Game getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     *
     * @return
     */
    public Coach getCoach() {
        return coach;
    }

    /**
     *
     * @param coach
     */
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    /**
     *
     * @return
     */
    public Team getOurTeam() {
        return ourTeam;
    }

    /**
     *
     * @param ourTeam
     */
    public void setOurTeam(Team ourTeam) {
        this.ourTeam = ourTeam;
    }

    /**
     *
     * @return
     */
    public Team getOpponentTeamSelected() {
        return opponentTeamSelected;
    }

    /**
     *
     * @param opponentTeamSelected
     */
    public void setOpponentTeamSelected(Team opponentTeamSelected) {
        this.opponentTeamSelected = opponentTeamSelected;
    }

    /**
     *
     * @return
     */
    public Venue getVenueSelected() {
        return venueSelected;
    }

    /**
     *
     * @param venueSelected
     */
    public void setVenueSelected(Venue venueSelected) {
        this.venueSelected = venueSelected;
    }
}
