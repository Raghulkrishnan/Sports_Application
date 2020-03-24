/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.lang.reflect.Executable;
import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raghul
 */
public class TeamTest extends AbstractTest {

    public TeamTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testingCreatePassCase() {
        Team td = new Team("Warriors",
                "Sid",
                "3127974892",
                Level.BEG,
                LocalDateTime.now());

        et.begin();

        assertNull(td.getId());
        System.out.println("before persistence: " + td.toString());

        em.persist(td);
        System.out.println("after persistence: " + td.toString());

//        once its done, ending the transaction before starting it again somewhere else
        et.commit();

        assertNotNull(td.getId());
        assertTrue(td.getId() > 0);
        System.out.println("after COMMIT: " + td.toString());

//        cleanUp this data from the test method
        et.begin();
        em.remove(td);
        et.commit();
    }

    @Test
    public void testingCreateFailCaseWithExpectedException() {
        Team td = new Team("TestMav",
                "John",
                "3129974652",
                Level.BEG,
                LocalDateTime.now());

        assertThrows(RollbackException.class, () -> {
            et.begin();
            em.persist(td);
            et.commit();
        });
    }

    @Test
    public void testingRead() {
        Team td = em.createQuery("select t from Team t where t.teamName = :teamName", Team.class)
                .setParameter("teamName", "TestMav")
                .getSingleResult();

        assertNotNull(td);
        assertTrue(td.getId() >= 1l); // 1 L
        assertEquals("TestMav", td.getTeamName());

    }

    @Test
    public void testingUpdate() {
        Team td = em.createQuery("select t from Team t where t.teamName = :teamName", Team.class)
                .setParameter("teamName", "TestMav")
                .getSingleResult();

        et.begin();
        td.setLevel(Level.ACP);
        td.setCaptainName("Tim");
        et.commit();

        Team reFindTheEntity = em.find(Team.class, td.getId());

        System.out.println("Original entity updated with set methods: " + td.toString());
        System.out.println("Entity re found from the db: " + reFindTheEntity.toString());

        assertEquals(td.getLevel(), reFindTheEntity.getLevel());
        assertEquals(td.getCaptainName(), reFindTheEntity.getCaptainName());
    }

    @Test
    public void testingDelete() {
        Team td = new Team("TestMav1",
                "John",
                "3129974752",
                Level.BEG,
                LocalDateTime.now());

        et.begin();
        em.persist(td);
        et.commit();

//        assert created successfully before we commit it
        assertNotNull(td.getId());

        et.begin();
        em.remove(td);
        et.commit();

        System.out.println("testingDelete method removed: " + td.toString());

        Team reFindTheEntityFromDatabase = em.find(Team.class, td.getId());
        assertNull(reFindTheEntityFromDatabase);

    }
}
