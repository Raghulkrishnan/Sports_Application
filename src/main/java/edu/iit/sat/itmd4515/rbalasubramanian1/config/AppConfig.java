/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.config;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author raghul
 */
@Named
@ApplicationScoped
public class AppConfig {

    public AppConfig() {
    }
    
    public Level[] getLevels() {
        return Level.values();
    }
}

