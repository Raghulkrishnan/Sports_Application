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
import edu.iit.sat.itmd4515.rbalasubramanian1.web.TeamServlet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    
    private static final Logger LOG = Logger.getLogger(StartUpDatabaseLoaderService.class.getName());
        
    @EJB CoachService coachServ;
    @EJB TeamService teamServ;
    @EJB VenueOwnerService voServ;
    @EJB VenueService venueServ;
    @EJB GameService gameServ;
    @EJB StatService statServ;
    
    public StartUpDatabaseLoaderService() {
    }
    
    @PostConstruct
    private void postConstruct(){
        
//      coaches
        Coach c1 = new Coach("Ian", "Chapell", LocalDate.of(2015, Month.APRIL, 14));
        Coach c2 = new Coach("Tom", "Blundell", LocalDate.of(2016, Month.APRIL, 19));
        Coach c3 = new Coach("Will", "Smith", LocalDate.of(2015, Month.MAY, 20));
        Coach c4 = new Coach("Tim", "Bresnan", LocalDate.of(2016, Month.JANUARY, 20));
            
        coachServ.create(c1);
        coachServ.create(c2);
        coachServ.create(c3);
        coachServ.create(c4);
        
        LOG.info("Created coach detail 1 -->>> " + c1.toString());
        LOG.info("Created coach detail 2 -->>> " + c2.toString());
        LOG.info("Created coach detail 3 -->>> " + c3.toString());
        LOG.info("Created coach detail 4 -->>> " + c4.toString());
        
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
        
        teamServ.create(t1);
        teamServ.create(t2);
        teamServ.create(t3);
        teamServ.create(t4);
        
        LOG.info("Created team detail 1 -->>> " + t1.toString());
        LOG.info("Created team detail 2 -->>> " + t2.toString());
        LOG.info("Created team detail 3 -->>> " + t3.toString());
        LOG.info("Created team detail 4 -->>> " + t4.toString());
        
//        Venue Owners
        VenueOwner vo1 = new VenueOwner("Raghul", "Krish", LocalDate.of(2016, Month.APRIL, 9));
        VenueOwner vo2 = new VenueOwner("Scott", "S", LocalDate.of(2016, Month.JANUARY, 19));
        VenueOwner vo3 = new VenueOwner("Thomas", "E", LocalDate.of(2016, Month.DECEMBER, 11));
        
        voServ.create(vo1);
        voServ.create(vo2);
        voServ.create(vo3);
        
        LOG.info("Created venue owner detail 1 -->>> " + vo1.toString());
        LOG.info("Created venue owner detail 2 -->>> " + vo2.toString());
        LOG.info("Created venue owner detail 3 -->>> " + vo3.toString());

//        Venues
        Venue v1 = new Venue("EDEN");
        Venue v2 = new Venue("Grand Arena");
        Venue v3 = new Venue("Lords");
        Venue v4 = new Venue("Sports Park");
        
//      bi directional many to one relationship
        v1.addVenueOwner(vo1);
        v2.addVenueOwner(vo2);
        v3.addVenueOwner(vo3);
        v4.addVenueOwner(vo3);
        
        venueServ.create(v1);
        venueServ.create(v2);
        venueServ.create(v3);
        venueServ.create(v4);
        
        LOG.info("Created venue detail 1 -->>> " + v1.toString());
        LOG.info("Created venue detail 2 -->>> " + v2.toString());
        LOG.info("Created venue detail 3 -->>> " + v3.toString());

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
        
        gameServ.create(g1);
        gameServ.create(g2);
        gameServ.create(g3);
        gameServ.create(g4);
        
        LOG.info("Created game detail 1 -->>> " + g1.toString());
        LOG.info("Created game detail 2 -->>> " + g2.toString());
        LOG.info("Created game detail 3 -->>> " + g3.toString());
        
//        Stats Integer.valueOf(), Integer.valueOf(),Integer.valueOf()
        Stat s1 = new Stat(Integer.valueOf(15), Integer.valueOf(10),Integer.valueOf(5));
        Stat s2 = new Stat(Integer.valueOf(24), Integer.valueOf(13),Integer.valueOf(11));
        Stat s3 = new Stat(Integer.valueOf(12), Integer.valueOf(10),Integer.valueOf(2));
        Stat s4 = new Stat(Integer.valueOf(22), Integer.valueOf(17),Integer.valueOf(5));
        
        s1.addTeam(t1);
        s2.addTeam(t2);
        s3.addTeam(t3);
        s4.addTeam(t4);
        
        statServ.create(s1);
        statServ.create(s2);
        statServ.create(s3);
        statServ.create(s4);
        
        LOG.info("Created stat 1 -->>> " + s1.toString());
        LOG.info("Created stat 2 -->>> " + s2.toString());
        LOG.info("Created stat 3 -->>> " + s3.toString());
        
    }
}
