/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.web;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Level;
import edu.iit.sat.itmd4515.rbalasubramanian1.model.Team;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.transaction.UserTransaction;

/**
 *
 * @author raghul
 */
@WebListener
public class ServletContextDatabasePopulator implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ServletContextDatabasePopulator.class.getName());

    @PersistenceContext(name = "itmd4515PU")
    EntityManager em;
    
    @Resource
    UserTransaction tx;
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Servlet Context Destoryed");
        ServletContextListener.super.contextDestroyed(sce); 
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        try {
            LOG.info("Servlet Context Initialized");
//            Team td = new Team("Warriors",
//                    "Sid",
//                    "3127974892",
//                    Level.BEG,
//                    LocalDateTime.now());
//            
//            tx.begin();
//            em.persist(td);
//            tx.commit();
             
            ServletContextListener.super.contextInitialized(sce);
        } catch (Exception e) {
            LOG.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
        }
    }
    
}
