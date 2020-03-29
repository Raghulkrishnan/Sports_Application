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

    public VenueOwner(String firstName, String lastName, LocalDate dateOfJoining) {
        super(firstName, lastName, dateOfJoining);
    }

//    add venue helper method
//    public void addVenue(Venue v){
//        if(!this.venues.contains(v)){
//            this.venues.add(v);
//        }
//        if(v.getVenueOwner() == null){
//            v.setVenueOwner(this);
//        }
//    }
//  
////    remove venue helper method
//    public void removeVenue(Venue v){
//        if(this.venues.contains(v)){
//            this.venues.remove(v);
//        }
//        if(v.getVenueOwner() != null){
//            v.setVenueOwner(null);
//        }
//    }
    
    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public String toString() {
        return "VenueOwner{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + '}';
    }
}
