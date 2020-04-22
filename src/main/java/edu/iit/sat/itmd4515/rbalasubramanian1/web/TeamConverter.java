/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.TeamService;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author raghul
 */
@FacesConverter(value="teamConverter", managed=true)
public class TeamConverter implements Converter<Team> {

    @EJB private TeamService teamServ;
    
    @Override
    public Team getAsObject(FacesContext context, UIComponent component, String value) {
       
        if(value== null || value.isEmpty()){
            return null;
        }
        
        return teamServ.find(Long.valueOf(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Team value) {
        
        if(value == null){
            return "";
        }
        
        return String.valueOf(value.getId());
    }
    
}
