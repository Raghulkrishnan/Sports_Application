/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author raghul
 */
@Entity
public class VenueOwner extends Person {
    
//  inverse side of bidirectional ManyToOne/OneToMany
    @OneToMany(mappedBy = "venueOwner")
    private List<Venue> venues = new ArrayList<>();

    public VenueOwner() {
        
    }
    
    @Override
    public String toString() {
        return "VenueOwner{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + '}';
    }
    public List<Venue> getVenues() {
        return venues;
    }
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
