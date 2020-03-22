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
public class Stat extends AbstractEntity{
    
    private String teamName;
    private int played;
    private int won;
    private int loss;
   
//    Bidirectional OneToOne
    @OneToOne
    private Team team;

    public Stat() {
        
    }


    
    /**
     * Get the value of teamName
     *
     * @return the value of teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Set the value of teamName
     *
     * @param teamName new value of teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public int getPlayed() {
        return played;
    }
    public void setPlayed(int played) {
        this.played = played;
    }
    public int getWon() {
        return won;
    }
    public void setWon(int won) {
        this.won = won;
    }
    public int getLoss() {
        return loss;
    }
    public void setLoss(int loss) {
        this.loss = loss;
    }

    @Override
    public String toString() {
        return "Stat{" + "id=" + id + "teamName=" + teamName + ", played=" + played + ", won=" + won + ", loss=" + loss + '}';
    }
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

}
