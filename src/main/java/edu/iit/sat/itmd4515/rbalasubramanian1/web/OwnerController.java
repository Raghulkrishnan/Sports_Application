/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Game;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.VenueOwnerService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author raghul
 */
@Named
@RequestScoped
public class OwnerController {

    private static final Logger LOG = Logger.getLogger(OwnerController.class.getName());

    private VenueOwner owner;
    private Game game;
    
    @EJB VenueOwnerService ownerServ;
    @Inject LoginController loginController;
    
    public OwnerController() {
    }
    
    @PostConstruct
    public void init(){
//        first thing to do--->> find the business obj by the logged in user
        owner = ownerServ.findByUsername(loginController.getUserName());
        LOG.info("Owner Controller post construct method...." + owner.toString());
        
    }
    
//    public List<Game> getOurTeamGames(){
//        List<Game> ourVenueGames= new ArrayList<>();
//        Venue v = owner.getVenue();
//        
//        owner.getVenue().getGames().forEach((g) -> {
//            ourVenueGames.add(g);
//        });
//        LOG.info("Games in the venue--->> " + ourVenueGames);
//        return ourVenueGames;
//    }
    
//    action method
    public String selectGame(Game g){
        LOG.info("Owner Controller selectGame method..." + g.toString());
        this.game = g;
        return "/owner/game.xhtml";
    }
    
    public VenueOwner getOwner() {
        return owner;
    }
    public void setOwner(VenueOwner owner) {
        this.owner = owner;
    }
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
