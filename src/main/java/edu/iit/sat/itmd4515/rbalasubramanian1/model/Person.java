/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

/**
 *
 * @author raghul
 */
@MappedSuperclass
public class Person extends AbstractEntity {
    
    /**
     *
     */
    protected String firstName;

    /**
     *
     */
    protected String lastName;

    /**
     *
     */
    protected LocalDate dateOfJoining;
    
    @Transient
    private int durationOfStay;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *
     */
    public Person() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param dateOfJoining
     */
    public Person(String firstName, String lastName, LocalDate dateOfJoining) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfJoining = dateOfJoining;
    }
    
    @PostLoad
    private void calcAge(){
        if(this.dateOfJoining != null){
            this.durationOfStay = Period.between(dateOfJoining, LocalDate.now()).getYears();
        }
    }
    
    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }
    
    /**
     *
     * @param dateOfJoining
     */
    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + '}';
    }

    /**
     *
     * @return
     */
    public int getDurationOfStay() {
        return durationOfStay;
    }

    /**
     *
     * @param durationOfStay
     */
    public void setDurationOfStay(int durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
  
}
