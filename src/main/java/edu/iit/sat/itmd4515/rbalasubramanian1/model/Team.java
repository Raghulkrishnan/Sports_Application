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
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

/**
 *Team class extends abstract entity to get the ID and version number
 * Has team properties
 * @author raghul
 */
@Entity
@NamedQuery(name = "Team.findByTeamName", query = "select t from Team t where t.teamName = :teamName")
@NamedQuery(name = "Team.findAll", query = "select t from Team t")
public class Team extends AbstractEntity {

    private static final Logger LOG = Logger.getLogger(Team.class.getName());

    @NotBlank(message = "Please enter team name.")
//    @Size(min = 2, max = 30)
    @Column(unique = true, nullable = false)
    private String teamName;

    @NotBlank(message = "Please enter captain name.")
    @Column(nullable = false)
    private String captainName;

    @NotBlank(message = "Please enter contact number.")
    @Column(unique = true, nullable = false)
    private String contact;
    
//  inverse side of unidirectional ManyToOne/OneToMany
    @ManyToMany(mappedBy = "teams")
    private List<Game> games = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Level level;

//    Bidirectional OneToOne
    @OneToOne
    private Coach coach;

//    Bidirectional OneToOne
    @OneToOne(mappedBy = "team")
    private Stat stat;

    /**
     *default constructor
     */
    public Team() {
    }

    /**
     *
     * @param teamName
     * @param captainName
     * @param contact
     * @param level
     */
    public Team(String teamName, String captainName, String contact, Level level) {
        this.teamName = teamName;
        this.captainName = captainName;
        this.contact = contact;
        this.level = level;
    }

//    Add or Remove Coach

    /**
     *addCoach helper method 
     * handles bidirectional relationship (handles both sides - team and coach)
     * @param c
     */
    public void addCoach(Coach c) {
        if (this.coach == null  || this.coach != c) {
            this.setCoach(c);
        }
        if (c.getTeam() == null || c.getTeam() != this) {
            c.setTeam(this);
        }
    }

    /**
     *removeCoach helper method handles both sides of the relationship
     * between coach and team
     * @param c
     */
    public void removeCoach(Coach c) {
        if (this.coach != null) {
            this.setCoach(null);
        }

        if (c.getTeam() != null) {
            c.setTeam(null);
        }
    }

    /**
     * team name getter function
     * @return
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * captain name getter
     * @return
     */
    public String getCaptainName() {
        return captainName;
    }

    /**
     *contact get method
     * @return
     */
    public String getContact() {
        return contact;
    }

    /**
     *getLevel method
     * @return
     */
    public Level getLevel() {
        return level;
    }

    /**
     *setter of teamName
     * @param teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     *used to set captain name
     * @param captainName
     */
    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    /**
     *used to set contact
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *set level
     * @param level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     *getter method for coach
     * @return
     */
    public Coach getCoach() {
        return coach;
    }

    /**
     * used to set coach
     * @param coach
     */
    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    /**
     *get stat method
     * @return
     */
    public Stat getStat() {
        return stat;
    }

    /**
     *set stat method
     * @param stat
     */
    public void setStat(Stat stat) {
        this.stat = stat;
    }

    /**
     *returns list of games
     * @return
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     *sets list of games
     * @param games
     */
    public void setGames(List<Game> games) {
        this.games = games;
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
