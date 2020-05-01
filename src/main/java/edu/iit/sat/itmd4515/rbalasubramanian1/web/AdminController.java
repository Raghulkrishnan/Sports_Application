/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.Group;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.CoachService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.GroupService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.UserService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.VenueOwnerService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author raghul
 */
@Named
@RequestScoped
public class AdminController {

    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());
    
    private VenueOwner owner;
    private Coach coach;
    private User user;
    
    @Inject 
    @ManagedProperty(value = "#{param.id}")
    Long coachId;

//
    @EJB CoachService coachServ;
    @EJB VenueOwnerService ownerServ;
    
    
    @EJB UserService userServ;
    @EJB GroupService groupServ;

    /**
     *
     */
    public AdminController() {
    }
   
//    initialization methods below

    /**
     *
     */
    @PostConstruct
    public void init(){
//        user = userServ.findByUsername(loginController.getUserName());
        LOG.info("admin controller.. post Construct");
        if (coachId == null) {
            coach = new Coach();
        } else{
          coach = coachServ.find(coachId);
        }
        
        user = new User();
    }
    
    /**
     *
     */
    public void initCoachById(){
//        teamsInGame = new ArrayList<>();
//        
//        LOG.info("init game by id..." + this.game.getId());
//        game = gameServ.find(this.game.getId());
//        
//        game.getTeams().forEach((t) -> {
//            teamsInGame.add(t);
//        });
//        
//        LOG.info("init game by id...after find!!!" + this.game.toString());
    }
    
    /**
     *
     * @return
     */
    public List<Coach> getAllCoaches(){
        List<Coach> allCoaches= new ArrayList<>();
        
        coachServ.findAll().forEach((g) -> {
            allCoaches.add(g);
        });
        
        LOG.info("All coachess...--->> " + allCoaches);
        return allCoaches;
    }
    
    public List<VenueOwner> getAllOwners(){
        List<VenueOwner> allOwners= new ArrayList<>();
        
        ownerServ.findAll().forEach((o) -> {
            allOwners.add(o);
        });
        
        LOG.info("All coachess...--->> " + allOwners);
        return allOwners;
    }

//    action methods

    /**
     *
     * @return
     */
    public String addCoach(){
//        LOG.info("!!!!!!!!save game result....." + this.game.toString());
        user.setEnabled(true);
        
        groupServ.findAll().forEach((g) -> {
            if(g.getGroupName().equals("COACH_GROUP")){
                user.addGroup(g);
            }
        });
        userServ.create(user);
        coach.setUser(user);
        coachServ.create(coach);
          
        return "/admin/welcome.xhtml?faces-redirect=true";
    }
    
    public String addNewTeam(Coach c){
        LOG.info("add new team..." + c.toString());
        if(c.getTeam() != null){
            return "/admin/welcome.xhtml";
        }
        else{
            return "/admin/newTeam.xhtml";
        }
    }
    
    public String addTeamToCoach(){
//        LOG.info("!!!!!!!!save game result....." + this.game.toString());
////        need to implement edit game
//        gameServ.addResultToGame(game);
        
        return "/admin/welcome.xhtml?faces-redirect=true";
    }
    
    public String addOwner(){
//        LOG.info("!!!!!!!!save game result....." + this.game.toString());
////        need to implement edit game
//        gameServ.addResultToGame(game);
        
        return "/admin/welcome.xhtml?faces-redirect=true";
    }
    
    public String addNewVenue(VenueOwner vo){
        LOG.info("Stats of the team.." + vo.toString());
        if(vo.getVenue() != null){
            return "/admin/welcome.xhtml";
        }
        else{
            return "/admin/newOwner.xhtml";
        }
    }
    
    public String addVenueToOwner(){
//        LOG.info("!!!!!!!!save game result....." + this.game.toString());
////        need to implement edit game
//        gameServ.addResultToGame(game);
        
        return "/admin/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     *
     * @return
     */
    public String removeGame(){
//        LOG.info("remove this game......." + this.game.toString());
//        need to implement delete game
        
        return "/owner/welcome.xhtml";
    }
    
    
//    accessors and mutators
    public VenueOwner getOwner() {
        return owner;
    }
    public void setOwner(VenueOwner owner) {
        this.owner = owner;
    }
    public Coach getCoach() {
        return coach;
    }
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
