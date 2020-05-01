/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.CoachService;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author raghul
 */
@FacesConverter(value="coachConverter", managed=true)
public class CoachConverter implements Converter<Coach> {

    @EJB private CoachService coachServ;
    
    @Override
    public Coach getAsObject(FacesContext context, UIComponent component, String value) {
       
        if(value== null || value.isEmpty()){
            return null;
        }
        
        return coachServ.find(Long.valueOf(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Coach value) {
        
        if(value == null){
            return "";
        }
        
        return String.valueOf(value.getId());
    }
    
}
