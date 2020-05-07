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
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.CoachService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.GameService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.TeamService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.UserService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.VenueOwnerService;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.VenueService;
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
public class CoachTeamController {

    private static final Logger LOG = Logger.getLogger(CoachTeamController.class.getName());
    
    private Coach coach;
    private Team team;
    private User user;

    @Inject 
    @ManagedProperty(value = "#{param.id}")
    Long coachId;

    @EJB TeamService teamServ;
    @EJB CoachService coachServ;
    @EJB UserService userServ;

    @Inject LoginController loginController;
    
    /**
     *
     */
    public CoachTeamController() {
    }
   
//    initialization methods below

    /**
     *
     */
    @PostConstruct
    public void init(){
        LOG.info("CoachTeamController.. post Construct");
        if (coachId == null) {
            coach = new Coach();
        } else{
          coach = coachServ.find(coachId);
        }
//        coach = coachServ.findByUsername(loginController.getUserName());
        user = new User();
    }

//    action methods

    /**
     *
     * @return
     */
    public String editTeamInfo(){
        LOG.info("!!!!!!!!edit....." + coach.getTeam());
//        need to implement edit game
        coachServ.editTeamInfo(coach);
        
        return "/coach/welcome.xhtml?faces-redirect=true";
    }
    
    /**
     *
     * @return
     */
    public String changePwd(){
        user.setEnabled(true);
//        user.setUserName(coach.getUser().getUserName());
        
        coachServ.editCoachPwd(user, coach);
          
        return "/coach/welcome.xhtml?faces-redirect=true";
    }
    
    
//    accessors and mutators

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
    public Team getTeam() {
        return team;
    }

    /**
     *
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
