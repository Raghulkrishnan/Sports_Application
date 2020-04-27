/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

/**
 *
 * @author raghul
 */
public enum Level {

    /**
     *
     */
    BEG("Beginner"), //less than 10 wins

    /**
     *
     */
    INT("Intermediate"), //10 to 20 wins

    /**
     *
     */
    ADV("Advance"), //20 to 40 wins

    /**
     *
     */
    EXP("Expert"); //more than 40 wins
    
    private Level(String label){
        this.label = label;
    }
    
    private String label;
    
    /**
     *
     * @return
     */
    public String getLabel(){
        return label;
    }
}
