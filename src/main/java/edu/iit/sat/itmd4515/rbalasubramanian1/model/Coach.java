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
 * The Coach class is used to define the coach entity.
 * It extends the Person class which has basic attributes such as first name, last name, age and date of joining.
 * As a coach is related to a Team and has a one to one bidirectional relationship, team setter and getter is also present.
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
     * Default constructor of Coach class.
     */
    public Coach() {

    }

    /**
     * Constructor with 4 arguments needed for the entity
     * @param firstName
     * @param lastName
     * @param dateOfJoining
     * @param age
     */
    public Coach(String firstName, String lastName, LocalDate dateOfJoining, Integer age) {
        super(firstName, lastName, dateOfJoining, age);
    }
    
    /**
     * The getTeam method is used to get the team object
     * @return
     */
    public Team getTeam() {
        return team;
    }

    /**
     * The setTeam method is used to set the team object values.
     * @param team
     */
    public void setTeam(Team team) {
        this.team = team;
        this.getTeam();
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", dateOfJoining=" + dateOfJoining + '}';
    }

}
