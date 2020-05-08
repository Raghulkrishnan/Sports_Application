/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.service.VenueService;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *venue converter class for single drop-down list
 * @author raghul
 */
@FacesConverter(value="venueConverter", managed=true)
public class VenueConverter implements Converter<Venue> {

    @EJB private VenueService venueServ;
    
    @Override
    public Venue getAsObject(FacesContext context, UIComponent component, String value) {
       
        if(value== null || value.isEmpty()){
            return null;
        }
        
        return venueServ.find(Long.valueOf(value)); 
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Venue value) {
        
        if(value == null){
            return "";
        }
        
        return String.valueOf(value.getId());
    }
    
}
