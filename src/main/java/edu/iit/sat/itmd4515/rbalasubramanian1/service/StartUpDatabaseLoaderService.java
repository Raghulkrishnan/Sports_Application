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
import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.Group;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.security.User;
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

    @EJB
    CoachService coachServ;
    @EJB
    TeamService teamServ;
    @EJB
    VenueOwnerService voServ;
    @EJB
    VenueService venueServ;
    @EJB
    GameService gameServ;
//    @EJB
//    StatService statServ;

//    security services
    @EJB
    UserService userServ;
    @EJB
    GroupService groupServ;

    /**
     *
     */
    public StartUpDatabaseLoaderService() {
    }

    @PostConstruct
    private void postConstruct() {

//      seed sercurity domain first
        User admin = new User("admin", "admin", true);
        Group adminGroup = new Group("ADMIN_GROUP", "This is the group for admins");
        admin.addGroup(adminGroup);

        groupServ.create(adminGroup);
        userServ.create(admin);

        Group coachGroup = new Group("COACH_GROUP", "Group of coaches");
        Group venueOwnerGroup = new Group("OWNER_GROUP", "Group of owners");
        groupServ.create(coachGroup);
        groupServ.create(venueOwnerGroup);

//      we are setting the groups. 
//      Have set coach 1 alone as admin & coach. 
//      Rest of the coaches belong to coach group only
        User coach1 = new User("coach1", "coach1", true);
        coach1.addGroup(adminGroup);
        coach1.addGroup(coachGroup);

        User coach2 = new User("coach2", "coach2", true);
        coach2.addGroup(coachGroup);
        coach2.addGroup(venueOwnerGroup);

        User coach3 = new User("coach3", "coach3", true);
        coach3.addGroup(coachGroup);

        User coach4 = new User("coach4", "coach4", true);
        coach4.addGroup(coachGroup);

        User venueOwner1 = new User("owner1", "owner1", true);
        venueOwner1.addGroup(venueOwnerGroup);

        User venueOwner2 = new User("owner2", "owner2", true);
        venueOwner2.addGroup(venueOwnerGroup);

        User venueOwner3 = new User("owner3", "owner3", true);
        venueOwner3.addGroup(venueOwnerGroup);

        User venueOwner4 = new User("owner4", "owner4", true);
        venueOwner4.addGroup(venueOwnerGroup);

        userServ.create(coach1);
        userServ.create(coach2);
        userServ.create(coach3);
        userServ.create(coach4);
        userServ.create(venueOwner1);
        userServ.create(venueOwner2);
        userServ.create(venueOwner3);
        userServ.create(venueOwner4);

//      coaches
        Coach c1 = new Coach("Ian", "Chapell", LocalDate.of(2015, Month.APRIL, 14));
        c1.setUser(coach1);
        Coach c2 = new Coach("Tom", "Blundell", LocalDate.of(2016, Month.APRIL, 19));
        c2.setUser(coach2);
        Coach c3 = new Coach("Will", "Smith", LocalDate.of(2015, Month.MAY, 20));
        c3.setUser(coach3);
        Coach c4 = new Coach("Tim", "Bresnan", LocalDate.of(2016, Month.JANUARY, 20));
        c4.setUser(coach4);

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
        VenueOwner vo4 = new VenueOwner("James", "P", LocalDate.of(2014, Month.MAY, 22));
        VenueOwner vo5 = new VenueOwner( c2.getFirstName(), c2.getLastName(), c2.getDateOfJoining());

        vo1.setUser(venueOwner1);
        vo2.setUser(venueOwner2);
        vo3.setUser(venueOwner3);
        vo4.setUser(venueOwner4);
        vo5.setUser(coach2); 

        voServ.create(vo1);
        voServ.create(vo2);
        voServ.create(vo3);
        voServ.create(vo4);
        voServ.create(vo5);

        LOG.info("Created venue owner detail 1 -->>> " + vo1.toString());
        LOG.info("Created venue owner detail 2 -->>> " + vo2.toString());
        LOG.info("Created venue owner detail 3 -->>> " + vo3.toString());
        LOG.info("Created venue owner detail 4 -->>> " + vo4.toString());
        LOG.info("Created venue owner detail 5 -->>> " + vo5.toString());

//        Venues
        Venue v1 = new Venue("EDEN");
        Venue v2 = new Venue("Grand Arena");
        Venue v3 = new Venue("Lords");
        Venue v4 = new Venue("Sports Park");
        Venue v5 = new Venue("Chepauk");

//      bi directional one to one relationship
        v1.addVenueOwner(vo1);
        v2.addVenueOwner(vo2);
        v3.addVenueOwner(vo3);
        v4.addVenueOwner(vo4);
        v5.addVenueOwner(vo5);

        venueServ.create(v1);
        venueServ.create(v2);
        venueServ.create(v3);
        venueServ.create(v4);
        venueServ.create(v5);

        LOG.info("Created venue detail 1 -->>> " + v1.toString());
        LOG.info("Created venue detail 2 -->>> " + v2.toString());
        LOG.info("Created venue detail 3 -->>> " + v3.toString());
        LOG.info("Created venue detail 4 -->>> " + v4.toString());
        LOG.info("Created venue detail 5 -->>> " + v5.toString());

//      Games
        Game g1 = new Game(LocalDate.of(2019, Month.MARCH, 22));
        Game g2 = new Game(LocalDate.of(2019, Month.MARCH, 23));
        Game g3 = new Game(LocalDate.of(2019, Month.MARCH, 24));
        Game g4 = new Game(LocalDate.of(2019, Month.MARCH, 24));
        Game g5 = new Game(LocalDate.of(2019, Month.APRIL, 24));

//      unidirectional many to one
        g1.addVenue(v4);
        g2.addVenue(v1);
        g3.addVenue(v2);
        g4.addVenue(v5);
        g5.addVenue(v1);

//      directional many to many relationship
        g1.addTeam(t3);
        g1.addTeam(t1);
//        g1.setWonBy(t1);
//        g1.setLostBy(t3);

        g2.addTeam(t2);
        g2.addTeam(t4);
//        g2.setWonBy(t2);
//        g2.setLostBy(t4);

        g3.addTeam(t2);
        g3.addTeam(t1);

        g4.addTeam(t3);
        g4.addTeam(t4);

        g5.addTeam(t1);
        g5.addTeam(t2);

        gameServ.create(g1);
        gameServ.create(g2);
        gameServ.create(g3);
        gameServ.create(g4);
        gameServ.create(g5);

        LOG.info("Created game detail 1 -->>> " + g1.toString());
        LOG.info("Created game detail 2 -->>> " + g2.toString());
        LOG.info("Created game detail 3 -->>> " + g3.toString());
        LOG.info("Created game detail 4 -->>> " + g4.toString());
        LOG.info("Created game detail 5 -->>> " + g5.toString());

//        Stats Integer.valueOf(), Integer.valueOf(),Integer.valueOf()
//        Stat s1 = new Stat(Integer.valueOf(15), Integer.valueOf(10), Integer.valueOf(5));
//        Stat s2 = new Stat(Integer.valueOf(24), Integer.valueOf(13), Integer.valueOf(11));
//        Stat s3 = new Stat(Integer.valueOf(12), Integer.valueOf(10), Integer.valueOf(2));
//        Stat s4 = new Stat(Integer.valueOf(22), Integer.valueOf(17), Integer.valueOf(5));
//
//        s1.addTeam(t1);
//        s2.addTeam(t2);
//        s3.addTeam(t3);
//        s4.addTeam(t4);
//
//        statServ.create(s1);
//        statServ.create(s2);
//        statServ.create(s3);
//        statServ.create(s4);
//
//        LOG.info("Created stat 1 -->>> " + s1.toString());
//        LOG.info("Created stat 2 -->>> " + s2.toString());
//        LOG.info("Created stat 3 -->>> " + s3.toString());

    }
}
