/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author raghul
 */
@Stateless
public class VenueOwnerService extends AbstractService<VenueOwner> {

    public VenueOwnerService() {
        super(VenueOwner.class);
    }

    @Override
    public List<VenueOwner> findAll() {
        return em.createNamedQuery("VenueOwner.findAll", entityClass).getResultList();
    }
    
}
