/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author raghul
 */
@Named
@Stateless
public class VenueService extends AbstractService<Venue> {

    /**
     *
     */
    public VenueService() {
        super(Venue.class);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Venue> findAll() {
        return em.createNamedQuery("Venue.findAll", entityClass).getResultList();
    }
}
