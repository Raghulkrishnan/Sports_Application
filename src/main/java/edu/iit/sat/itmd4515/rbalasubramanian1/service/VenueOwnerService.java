/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *VenueOwnerService class is used to perform CRUD operations on the database
 * @author raghul
 */
@Stateless
public class VenueOwnerService extends AbstractService<VenueOwner> {

    private static final Logger LOG = Logger.getLogger(VenueOwnerService.class.getName());

    /**
     *default constructor
     */
    public VenueOwnerService() {
        super(VenueOwner.class);
    }
    
    /**
     *used to find the venueowner record based on id
     * @param id
     * @return
     */
    @Override
    public VenueOwner find(Long id){
        LOG.info("========ID========" + id);
        return em.find(VenueOwner.class, id);
    }

    /**
     *returns all venueowners
     * @return
     */
    @Override
    public List<VenueOwner> findAll() {
        return em.createNamedQuery("VenueOwner.findAll", entityClass).getResultList();
    }
    
    /**
     *returns record based on username
     * @param username
     * @return
     */
    public VenueOwner findByUsername(String username){
        return em.createNamedQuery("VenueOwner.findByUserName", VenueOwner.class)
                .setParameter("userName", username)
                .getSingleResult();
    }
    
    /**
     *used to edit venueowner info and the foreign attribute venue
     * @param vo
     */
    public void editVenueInfo(VenueOwner vo) {
        VenueOwner currentRowFromDatabase = em.find(VenueOwner.class, vo.getId());
        
        currentRowFromDatabase.setFirstName(vo.getFirstName());
        currentRowFromDatabase.setLastName(vo.getLastName());
        currentRowFromDatabase.setAge(vo.getAge());
        
        if(vo.getVenue() != null){
            Venue rowFromDB = em.find(Venue.class, vo.getVenue().getId());
            LOG.info("venuowner service is..." + vo.getVenue());
            rowFromDB.setVenueName(vo.getVenue().getVenueName());
            em.merge(rowFromDB);
        }
            
        em.merge(currentRowFromDatabase);
        
    }
    
    /**
     *used to edit the venue owner details
     * @param vo
     */
    public void editOwner(VenueOwner vo){
        VenueOwner currentRowFromDatabase = em.find(VenueOwner.class, vo.getId());
        
        currentRowFromDatabase.setFirstName(vo.getFirstName());
        currentRowFromDatabase.setLastName(vo.getLastName());
        currentRowFromDatabase.setDateOfJoining(vo.getDateOfJoining());
        currentRowFromDatabase.setAge(vo.getAge());
        
        em.merge(currentRowFromDatabase);
    }
    
    /**
     *used to change the owner user password
     * @param u
     * @param vo
     */
    public void editOwnerPwd(User u, VenueOwner vo){
        VenueOwner currentRowFromDatabase = em.find(VenueOwner.class, vo.getId());
        
        currentRowFromDatabase.getUser().setPassword(u.getPassword());
        em.merge(currentRowFromDatabase);
    }
    
    /**
     *used to remove the record from the database
     * @param vo
     */
    public void deleteOwner(VenueOwner vo){
        VenueOwner currentRowFromDatabase = em.find(VenueOwner.class, vo.getId());
        em.remove(currentRowFromDatabase);
    }
    
}
