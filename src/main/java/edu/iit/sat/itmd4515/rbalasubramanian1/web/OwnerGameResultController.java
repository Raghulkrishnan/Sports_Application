/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Game;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.GameService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author raghul
 */
@Named
@RequestScoped
public class OwnerGameResultController {

    private static final Logger LOG = Logger.getLogger(OwnerGameResultController.class.getName());
    
    private Game game;
    private List<Team> teamsInGame;
    
    @EJB GameService gameServ;

    public OwnerGameResultController() {
    }
   
//    initialization methods below
    @PostConstruct
    public void init(){
        LOG.info("OwnerGameResultController.. post Construct");
        game = new Game();
    }
    
    public void initGameById(){
        teamsInGame = new ArrayList<>();
        
        LOG.info("init game by id..." + this.game.getId());
        game = gameServ.find(this.game.getId());
        
        game.getTeams().forEach((t) -> {
            teamsInGame.add(t);
        });
        
        LOG.info("init game by id...after find!!!" + this.game.toString());
    }

//    action methods
    public String saveGameResult(){
        LOG.info("!!!!!!!!save game result....." + this.game.toString());
        return "/owner/welcome.xhtml?faces-redirect=true";
    }
    
    public String removeGame(){
        LOG.info("remove this game......." + this.game.toString());
        return "/owner/welcome.xhtml";
    }
    
    
//    accessors and mutators
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public List<Team> getTeamsInGame() {
        return teamsInGame;
    }
    public void setTeamsInGame(List<Team> teamsInGame) {
        this.teamsInGame = teamsInGame;
    }
}
