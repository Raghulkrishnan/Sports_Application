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
 *VenueOwner class extends Person class to get firstname, lastname, age and date of joining
 * @author raghul
 */
@Entity
@NamedQuery(name = "VenueOwner.findAll", query = "select vo from VenueOwner vo")
@NamedQuery(name = "VenueOwner.findById", query = "select vo from VenueOwner vo where vo.id = :id")
@NamedQuery(name = "VenueOwner.findByUserName", query = "select vo from VenueOwner vo where vo.user.userName = :userName")
public class VenueOwner extends Person {

//  inverse side of bidirectional ManyToOne/OneToMany
    @OneToOne(mappedBy = "venueOwner")
    private Venue venue;

    /**
     *default constructor
     */
    public VenueOwner() {

    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param dateOfJoining
     * @param age
     */
    public VenueOwner(String firstName, String lastName, LocalDate dateOfJoining, Integer age) {
        super(firstName, lastName, dateOfJoining, age);
    }
    
    /**
     *get venue method
     * @return
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     *used to set venue
     * @param venue
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "VenueOwner{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", dateOfJoining=" + dateOfJoining + '}';
    }
}
