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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

/**
 * Person is the super class to the Coach and VenueOwner classes.
 * It has the basic attributes of a person such as first name, last name, age
 * and date of joining. In addition it also extends the Abstract Entity class which shall provide the
 * generated ID value and version value to the entities.
 * @author raghul
 */
@MappedSuperclass
public class Person extends AbstractEntity {
    
    /**
     *firstName - To store the first name of the person
     */
    @NotBlank(message = "Enter first name")
    protected String firstName;

    /**
     *lastName - To store the last name of the person
     */
    @NotBlank(message = "Enter last name")
    protected String lastName;

    /**
     *dateOfJoining - To store the joining date of the person
     */
    @PastOrPresent
    protected LocalDate dateOfJoining;
    
    /**
    *age - To store the age of the person
    */
    @Age(value=15)
    protected Integer age;
    
    @Transient
    private int durationOfStay;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;

    /**
     *Default constructor
     */
    public Person() {
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param dateOfJoining
     */
    public Person(String firstName, String lastName, LocalDate dateOfJoining, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfJoining = dateOfJoining;
        this.age = age;
    }
    
    @PostLoad
    private void calcExperience(){
        if(this.dateOfJoining != null){
            this.durationOfStay = Period.between(dateOfJoining, LocalDate.now()).getYears();
        }
    }
    
    /**
     *getFirstName method is used to get the firstName of the person.
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *setFirstName method is used to set the firstName of the person.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *getLastName method is used to get the lastName of the person.
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *setLastName method is used to set the lastName of the person.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *to get the joining date of the person
     * @return
     */
    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }
    
    /**
     *to set the joining date of the person
     * @param dateOfJoining
     */
    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     *this method returns the duration
     * @return
     */
    public int getDurationOfStay() {
        return durationOfStay;
    }

    /**
     *this method is used to set the duration
     * @param durationOfStay
     */
    public void setDurationOfStay(int durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    /**
     * get user object
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *set user object
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * returns the age
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * used to set age
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfJoining=" + dateOfJoining + ", age=" + age + '}';
    }
  
}
