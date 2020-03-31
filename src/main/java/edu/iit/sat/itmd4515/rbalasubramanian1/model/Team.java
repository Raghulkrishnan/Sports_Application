/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

/**
 *
 * @author raghul
 */
@Entity
@NamedQuery(name = "Team.findByTeamName", query = "select t from Team t where t.teamName = :teamName")
@NamedQuery(name = "Team.findAll", query = "select t from Team t")
public class Team extends AbstractEntity {

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(unique = true, nullable = false)
    private String teamName;

    @NotBlank
    @Column(nullable = false)
    private String captainName;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String contact;

    @Enumerated(EnumType.STRING)
    private Level level;

//    Bidirectional OneToOne
    @OneToOne
    private Coach coach;

//    Bidirectional OneToOne
    @OneToOne(mappedBy = "team")
    private Stat stat;

    public Team() {
    }

    public Team(String teamName, String captainName, String contact, Level level) {
        this.teamName = teamName;
        this.captainName = captainName;
        this.contact = contact;
        this.level = level;
    }

//    Add or Remove Coach
    public void addCoach(Coach c) {
        if (this.coach == null) {
            this.setCoach(c);
        }
        if (c.getTeam() == null) {
            c.setTeam(this);
        }
    }

    public void removeCoach(Coach c) {
        if (this.coach != null) {
            this.setCoach(null);
        }

        if (c.getTeam() != null) {
            c.setTeam(null);
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCaptainName() {
        return captainName;
    }

    public String getContact() {
        return contact;
    }

    public Level getLevel() {
        return level;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.teamName);
        return hash;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        final Team other = (Team) that;
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", teamName=" + teamName + ", captainName=" + captainName + ", contact=" + contact + ", level=" + level + '}';
    }

}
