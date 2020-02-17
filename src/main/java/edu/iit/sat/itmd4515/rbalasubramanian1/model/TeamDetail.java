/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

/**
 *
 * @author raghu
 */
@Entity
public class TeamDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @PastOrPresent
    private LocalDateTime createdTime;

    public TeamDetail() {
    }

    public TeamDetail(String teamName, String captainName, String contact, Level level, LocalDateTime createdTime) {
        this.teamName = teamName;
        this.captainName = captainName;
        this.contact = contact;
        this.level = level;
        this.createdTime = createdTime;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
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
    
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
    
    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
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

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
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
        final TeamDetail other = (TeamDetail) that;
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TeamDetail{" + "id=" + id + ", teamName=" + teamName + ", captainName=" + captainName + ", contact=" + contact + ", level=" + level + ", createdTime=" + createdTime + '}';
    }

}
