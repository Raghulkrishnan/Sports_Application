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
import edu.iit.sat.itmd4515.rbalasubramanian1.service.GameService;
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
public class OwnerVenueController {

    private static final Logger LOG = Logger.getLogger(OwnerVenueController.class.getName());
    
    private Venue venue;
    private VenueOwner owner;

    @Inject 
    @ManagedProperty(value = "#{param.id}")
    Long ownerId;

    @EJB VenueService venueServ;
    @EJB VenueOwnerService ownerServ;

    /**
     *
     */
    public OwnerVenueController() {
    }
   
//    initialization methods below

    /**
     *
     */
    @PostConstruct
    public void init(){
        LOG.info("OwnerVenueController.. post Construct");
        if (ownerId == null) {
            owner = new VenueOwner();
        } else{
          owner = ownerServ.find(ownerId);
        }
    }

//    action methods

    /**
     *
     * @return
     */
    public String editVenueInfo(){
        LOG.info("!!!!!!!!edit....." + owner.getVenue());
//        need to implement edit game
        ownerServ.editVenueInfo(owner);
        
        return "/owner/welcome.xhtml?faces-redirect=true";
    }
    
//    accessors and mutators
    
    public Venue getVenue() {
        return venue;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public VenueOwner getOwner() {
        return owner;
    }
    public void setOwner(VenueOwner owner) {
        this.owner = owner;
    }
}
