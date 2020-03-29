/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Game;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Level;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Stat;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Venue;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.VenueOwner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author raghul
 */
@Startup
@Singleton
public class StartUpDatabaseLoaderService {

    @PersistenceContext(name = "itmd4515PU")
    private EntityManager em;
    
    public StartUpDatabaseLoaderService() {
    }
    
    @PostConstruct
    private void postConstruct(){
        
//      coaches
        Coach c1 = new Coach("Ian", "Chapell", LocalDate.of(2015, Month.APRIL, 14));
        Coach c2 = new Coach("Tom", "Blundell", LocalDate.of(2016, Month.APRIL, 19));
        Coach c3 = new Coach("Will", "Smith", LocalDate.of(2015, Month.MAY, 20));
        Coach c4 = new Coach("Tim", "Bresnan", LocalDate.of(2016, Month.JANUARY, 20));
            
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);
        
//      teams
        Team t1 = new Team("Thunders CC", "Ponting", "4574226636", Level.BEG);
        Team t2 = new Team("Bolts CC", "Smith", "5987433002", Level.BEG);
        Team t3 = new Team("Sunrisers CC", "Kohli", "3620014599", Level.BEG);
        Team t4 = new Team("CSK", "Dhoni", "4757487777", Level.BEG);
        
//      bi directional ONE TO ONE relationship
        t1.addCoach(c1);
        t2.addCoach(c2);
        t3.addCoach(c3);
        t4.addCoach(c4);
        
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);
        
//        Venue Owners
        VenueOwner vo1 = new VenueOwner("Raghul", "Krish", LocalDate.of(2016, Month.APRIL, 9));
        VenueOwner vo2 = new VenueOwner("Scott", "S", LocalDate.of(2016, Month.JANUARY, 19));
        VenueOwner vo3 = new VenueOwner("Thomas", "E", LocalDate.of(2016, Month.DECEMBER, 11));
        
        em.persist(vo1);
        em.persist(vo2);
        em.persist(vo3);

//        Venues
        Venue v1 = new Venue("EDEN");
        Venue v2 = new Venue("Grand Arena");
        Venue v3 = new Venue("Lords");
        Venue v4 = new Venue("Sports Park");
        
//      bi directional relationship
        v1.addVenueOwner(vo1);
        v2.addVenueOwner(vo2);
        v3.addVenueOwner(vo3);
        v4.addVenueOwner(vo3);
        
        em.persist(v1);
        em.persist(v2);
        em.persist(v3);
        em.persist(v4);

//      Games
        Game g1 = new Game(LocalDate.of(2019,Month.MARCH, 22));
        Game g2 = new Game(LocalDate.of(2019,Month.MARCH, 23));
        Game g3 = new Game(LocalDate.of(2019,Month.MARCH, 24));
        Game g4 = new Game(LocalDate.of(2019,Month.MARCH, 24));

//      unidirectional many to one
        g1.addVenue(v4);
        g2.addVenue(v1);
        g3.addVenue(v2);
        g4.addVenue(v3);
        
//      unidirectional many to many relationship

        g1.addTeam(t3);
        g1.addTeam(t1);
        
        g2.addTeam(t2);
        g2.addTeam(t4);
        
        g3.addTeam(t2);
        g3.addTeam(t1);
        
        g4.addTeam(t3);
        g4.addTeam(t4);
        
        em.persist(g1);
        em.persist(g2);
        em.persist(g3);
        em.persist(g4);
        
//        Stats
        Stat s1 = new Stat(15,10,5);
        Stat s2 = new Stat(24,13,11);
        Stat s3 = new Stat(12,10,2);
        Stat s4 = new Stat(22,17,5);
        
        s1.addTeam(t1);
        s2.addTeam(t2);
        s3.addTeam(t3);
        s4.addTeam(t4);
        
        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(s4);
        
    }
}
