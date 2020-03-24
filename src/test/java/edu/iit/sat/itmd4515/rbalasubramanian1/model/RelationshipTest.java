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
public class RelationshipTest extends AbstractTest {
    
    public RelationshipTest(){
    }
    
    @Test
    public void manyGames_To_ManyTeams_Unidirectional_Test(){
//        many teams
        Team t1 = new Team("United CC",
                "Matt",
                "5050483422",
                Level.BEG,
                LocalDateTime.now());
        
        Team t2 = new Team("Demolishers",
                "Henry",
                "8948233333",
                Level.BEG,
                LocalDateTime.now());
        
//        many games
        VenueOwner vo1 = new VenueOwner("Mani", "K", LocalDate.of(2015, Month.JANUARY, 11));        
        Venue v1 = new Venue(vo1);
        Game g1 = new Game(v1, LocalDate.of(2020, Month.JANUARY, 21));
        
        VenueOwner vo2 = new VenueOwner("Scott", "S", LocalDate.of(2016, Month.JANUARY, 18));        
        Venue v2 = new Venue(vo2);
        Game g2 = new Game(v2, LocalDate.of(2020, Month.MARCH, 1));
        
//        game is the owning and the only side in the relationship - unidirectional.
//        only game can set teams. not vice versa
        g1.getTeams().add(t1);
        g2.getTeams().add(t2);
        
        et.begin();
        
        em.persist(t1);
        em.persist(vo1);
        em.persist(v1);
        em.persist(g1);
        
        em.persist(t2);
        em.persist(vo2);
        em.persist(v2);
        em.persist(g2);
        
        et.commit();
        
        assertTrue(t1.getId() > 0);
        assertTrue(g1.getId() > 0);
        
        assertTrue(t2.getId() > 0);
        assertTrue(g2.getId() > 0);
    }
    
    @Test
     public void manyGames_To_OneVenue_BiDirectional_Test(){  
//      one venue   
        VenueOwner vo = new VenueOwner("Raghul", "Bala", LocalDate.of(2016, Month.JANUARY, 1));
        Venue v = new Venue(vo);
        
//      many games  
        Game g1 = new Game(v, LocalDate.of(2016, Month.MARCH, 2));
        Game g2 = new Game(v, LocalDate.of(2016, Month.MARCH, 22));
        
//     right setting. bidirectional relationship means setting on both sides. 
//      only then the persistence context will be stable. So.. -->>
//      Bi-directional Relationship!!
        g1.addVenue(v); //        g1.setVenue(v);
        g2.addVenue(v);
        v.addGame(g1);  //        v.getGames().add(g1);
    
        et.begin();
        em.persist(vo);
        em.persist(v);
        em.persist(g1);
        em.persist(g2);
        et.commit();
        
//        output section
        Game foundGame = em.find(Game.class, g1.getId());
        Venue foundVenue = em.find(Venue.class, v.getId());
        
//        assertTrue(foundGame.getId() > 0);
//        assertTrue(foundVenue.getGames().size() == 1);
    }
     
     @Test
     public void oneCoach_To_OneTeam_BiDirectional_Test(){  
        
        Coach c = new Coach("Vishal", "A", LocalDate.of(2015, Month.JANUARY, 2));
        
        Team t = new Team("Kings CC",
                "Matt",
                "7363336474",
                Level.BEG,
                LocalDateTime.now());
        
        t.setCoach(c);
        c.setTeam(t);
    
        et.begin();
        em.persist(c);
        em.persist(t);
        et.commit();
    }
}
