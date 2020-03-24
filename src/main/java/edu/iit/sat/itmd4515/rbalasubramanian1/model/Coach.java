/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author raghul
 */
@Entity
public class Coach extends Person {

//    Bidirectional OneToOne
    @OneToOne(mappedBy = "coach")
    private Team team;

    public Coach() {

    }

    public Coach(String firstName, String lastName, LocalDate dateOfJoining) {
        super(firstName, lastName, dateOfJoining);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + '}';
    }

}
