/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author raghul
 */
@Entity
@NamedQuery(name = "VenueOwner.findAll", query = "select vo from VenueOwner vo")
@NamedQuery(name = "VenueOwner.findByUserName", query = "select vo from VenueOwner vo where vo.user.userName = :userName")
public class VenueOwner extends Person {

//  inverse side of bidirectional ManyToOne/OneToMany
    @OneToOne(mappedBy = "venueOwner")
    private Venue venue;

    public VenueOwner() {

    }

    public VenueOwner(String firstName, String lastName, LocalDate dateOfJoining) {
        super(firstName, lastName, dateOfJoining);
    }
    
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "VenueOwner{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + '}';
    }
}
