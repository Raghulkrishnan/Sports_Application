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
    BEG("Beginner"),
    AVG("Average"),
    ACP("Accomplished"),
    BRL("Brilliant"),
    FAN("Fantastic"),
    DGD("DemiGod");
    
    private Level(String label){
        this.label = label;
    }
    
    private String label;
    
    public String getLabel(){
        return label;
    }
}
