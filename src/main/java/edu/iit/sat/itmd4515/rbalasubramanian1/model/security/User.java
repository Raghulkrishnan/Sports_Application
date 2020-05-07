/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model.security;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *User class is used to create a secure user.
 * @author raghul
 */
@Entity
@Table(name = "sec_user")
@EntityListeners(UserListener.class)
@NamedQuery(name = "User.findAll", query = "select u from User u")
@NamedQuery(name = "User.findByUsername", query = "select u from User u where u.userName = :username")
public class User {

    @Id
    @NotBlank(message = "Enter username")
    private String userName;
    @NotBlank(message = "Enter password")
    private String password;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(name = "sec_user_groups",
            joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    /**
     *helper method - used to add group to the user.
     * @param g
     */
    public void addGroup(Group g) {
        if (!this.groups.contains(g)) {
            this.groups.add(g);
        }
        if (!g.getUsers().contains(this)) {
            g.getUsers().add(this);
        }
    }

    /**
     *default constructor
     */
    public User() {
    }

    /**
     *constructor with arguments
     * @param string
     * @param string1
     * @param bln
     */
    public User(String userName, String password, Boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *get password of the user
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *set password for user
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * boolean value to check if the user is ready for login.
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * set boolean value isEnabled
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *returns list of groups
     * @return
     */
    public List<Group> getGroups() {
        return groups;
    }

    /**
     * sets groups
     * @param groups
     */
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", enabled=" + enabled + '}';
    }

}
