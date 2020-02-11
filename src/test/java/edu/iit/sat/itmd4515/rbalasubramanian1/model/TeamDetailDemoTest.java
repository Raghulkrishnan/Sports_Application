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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raghu
 */
public class TeamDetailDemoTest {
    
    @Test
    public void demoTest(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        TeamDetail td = new TeamDetail("Mavericks", 
                "John", 
                "3129974652", 
                Level.BEG, 
                LocalDateTime.now());
        
        et.begin();
        
//      actions here - like persist a new/transient entity
        em.persist(td);

        et.commit();
        
        assertTrue(td.getId() > 0);
        assertTrue(td.getCaptainName() != "");
        
        em.close();
        emf.close();
    }
}
