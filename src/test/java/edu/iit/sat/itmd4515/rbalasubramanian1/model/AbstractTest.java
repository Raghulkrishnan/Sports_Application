/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.model;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author raghul
 */
abstract class AbstractTest {
    private static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction et;
    
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

        Team td = new Team("TestMav",
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
        Team td
                //= em.createQuery("select t from Team t where t.teamName = :teamName", Team.class)
                = em.createNamedQuery("Team.findByTeamName", Team.class)
                        .setParameter("teamName", "TestMav")
                        .getSingleResult();

        et.begin();
        em.remove(td);
        et.commit();
        em.close();
    }
}
