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
    
    private int played;
    private int won;
    private int loss;
   
//    Bidirectional OneToOne
    @OneToOne
    private Team team;

    public Stat() {
        
    }

    public Stat(int played, int won, int loss) {
        this.played = played;
        this.won = won;
        this.loss = loss;
    }
    
    
//    Add or Remove Team
    public void addTeam(Team t){
        if(this.team == null){
            this.setTeam(t);
        }
        if(t.getStat() == null){
            t.setStat(this);
        }
    }
  
    public void removeTeam(Team t){
        if(this.team != null){
            this.setTeam(null);
        }
        
        if(t.getStat() != null){
            t.setStat(null);
        }
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
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    @Override
    public String toString() {
        return "Stat{" + "id=" + id + ", played=" + played + ", won=" + won + ", loss=" + loss + '}';
    }

}
