/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

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
    
    @Override
    public String toString() {
        return "Coach{" + "id=" + id + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + '}';
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
  
}
