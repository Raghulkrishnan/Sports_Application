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
 * @author raghu
 */
public class TeamDetailTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public TeamDetailTest() {
    }

    @BeforeAll
    public static void doThisBeforeStartingTests() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    @AfterAll
    public static void doThisAfterAllTests() {
        emf.close();
    }

    @BeforeEach
    public void doThisBeforeEachTest() {
        em = emf.createEntityManager();
        et = em.getTransaction();

        TeamDetail td = new TeamDetail("TestMav",
                "John",
                "3129974652",
                Level.BEG,
                LocalDateTime.now());
        et.begin();
//      actions here - like persist a new/transient entity
        em.persist(td);
        et.commit();
    }

    @AfterEach
    public void doThisAfterEachTest() {
        TeamDetail td = em.createQuery("select t from TeamDetail t where t.teamName = :teamName", TeamDetail.class)
                .setParameter("teamName", "TestMav")
                .getSingleResult();

        et.begin();
        em.remove(td);
        et.commit();
        em.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testingCreatePassCase() {
        TeamDetail td = new TeamDetail("Warriors",
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
        TeamDetail td = new TeamDetail("TestMav",
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
        TeamDetail td = em.createQuery("select t from TeamDetail t where t.teamName = :teamName", TeamDetail.class)
                .setParameter("teamName", "TestMav")
                .getSingleResult();

        assertNotNull(td);
        assertTrue(td.getId() >= 1l); // 1 L
        assertEquals("TestMav", td.getTeamName());

    }

    @Test
    public void testingUpdate() {
        TeamDetail td = em.createQuery("select t from TeamDetail t where t.teamName = :teamName", TeamDetail.class)
                .setParameter("teamName", "TestMav")
                .getSingleResult();

        et.begin();
        td.setLevel(Level.ACP);
        td.setCaptainName("Tim");
        et.commit();

        TeamDetail reFindTheEntity = em.find(TeamDetail.class, td.getId());

        System.out.println("Original entity updated with set methods: " + td.toString());
        System.out.println("Entity re found from the db: " + reFindTheEntity.toString());

        assertEquals(td.getLevel(), reFindTheEntity.getLevel());
        assertEquals(td.getCaptainName(), reFindTheEntity.getCaptainName());
    }

    @Test
    public void testingDelete() {
        TeamDetail td = new TeamDetail("TestMav1",
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
        
        System.out.println("testingDelete method removed: "+ td.toString());
        
        TeamDetail reFindTheEntityFromDatabase = em.find(TeamDetail.class, td.getId());
        assertNull(reFindTheEntityFromDatabase);

    }
}
