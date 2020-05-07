/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author raghul
 */
public class TestRelationship extends AbstractTest {
    
    public TestRelationship(){
    }
    
    @Test
    public void manyGames_To_ManyTeams_Unidirectional_Test(){
//        many teams
//        List<Team> teams = new ArrayList<>();
        Team t1 = new Team("United CC",
                "Matt",
                "5050483422",
                Level.BEG);
        
        Team t2 = new Team("Demolishers",
                "Henry",
                "8948233333",
                Level.BEG);
        
//        teams.add(t1);
//        teams.add(t2);
        
//        many games
        VenueOwner vo1 = new VenueOwner("Mani", "K", LocalDate.of(2015, Month.JANUARY, 11), 22);    
        Venue v1 = new Venue("Super Sports Park");
        
        v1.addVenueOwner(vo1);
        
        Game g1 = new Game(LocalDate.of(2021, Month.JANUARY, 21));
        
        g1.addVenue(v1);
        
//        game is the owning and the only side in the relationship - unidirectional.
//        only game can set teams. not vice versa
        g1.getTeams().add(t1);
        g1.getTeams().add(t2);
        
        et.begin();
        
        em.persist(t1);        
        em.persist(t2);
        em.persist(vo1);
        em.persist(v1);
        em.persist(g1);
        
        et.commit();
        
        assertTrue(t1.getId() > 0);
        assertTrue(t2.getId() > 0);
        assertTrue(g1.getId() > 0);
        
    }
    
    @Test
     public void manyGames_To_OneVenue_UniDirectional_Test(){  
//      one venue   
        VenueOwner vo = new VenueOwner("Raghul", "Bala", LocalDate.of(2016, Month.JANUARY, 1), 23);
        Venue v = new Venue("MCG");
        
        v.addVenueOwner(vo);
        
//      many games  
        Game g1 = new Game(LocalDate.of(2021, Month.MARCH, 2));
        Game g2 = new Game(LocalDate.of(2021, Month.MARCH, 22));
        
//      Unidirectional Relationship!!
        g1.addVenue(v);
        g2.addVenue(v);
    
        et.begin();
        em.persist(vo);
        em.persist(v);
        em.persist(g1);
        em.persist(g2);
        et.commit();
        
    }
     
     @Test
     public void oneCoach_To_OneTeam_BiDirectional_Test(){  
        
        Coach c = new Coach("Vishal", "A", LocalDate.of(2015, Month.JANUARY, 2), 25);
        
        Team t = new Team("Kings CC",
                "Matt",
                "7363336474",
                Level.BEG);
        
//      Bidirectional Relationship. Team is the owning side.
        t.addCoach(c);
    
        et.begin();
        em.persist(c);
        em.persist(t);
        et.commit();
    }
}
