/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

/**
 * The level enum has a set of values that can be provided to the teams
 * based on the number of victories the team has obtained. It has a set of values such as
 * Beginner, Intermediate, Advance and Expert.
 * @author raghul
 */
public enum Level {

    /**
     * BEG is a value in this enumerated class
     */
    BEG("Beginner"), //less than 10 wins

    /**
     *INT is a value in this enumerated class
     */
    INT("Intermediate"), //10 to 20 wins

    /**
     *ADV is a value in this enumerated class
     */
    ADV("Advance"), //20 to 40 wins

    /**
     *EXP is a value in this enumerated class
     */
    EXP("Expert"); //more than 40 wins
    
    /**
     * Constructor
     */
    private Level(String label){
        this.label = label;
    }
    
    private String label;
    
    /**
     * getLabel method returns a label.
     * @return
     */
    public String getLabel(){
        return label;
    }
}
