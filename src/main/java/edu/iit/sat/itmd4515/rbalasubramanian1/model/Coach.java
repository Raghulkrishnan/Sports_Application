/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDate;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author raghul
 */
@Entity
@NamedQuery(name = "Coach.findAll", query = "select c from Coach c")
@NamedQuery(name = "Coach.findById", query = "select c from Coach c where c.id = :id")
@NamedQuery(name = "Coach.findByLastName", query = "select c from Coach c where c.lastName = :lastName")
@NamedQuery(name = "Coach.findByUserName", query = "select c from Coach c where c.user.userName = :userName")
public class Coach extends Person {

    private static final Logger LOG = Logger.getLogger(Coach.class.getName());

//    Bidirectional OneToOne
    @OneToOne(mappedBy = "coach")
    private Team team;

    /**
     *
     */
    public Coach() {

    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param dateOfJoining
     */
    public Coach(String firstName, String lastName, LocalDate dateOfJoining) {
        super(firstName, lastName, dateOfJoining);
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
        this.getTeam();
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + '}';
    }

}
