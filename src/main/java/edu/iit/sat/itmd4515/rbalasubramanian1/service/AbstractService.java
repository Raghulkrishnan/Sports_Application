/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rbalasubramanian1.service;

import edu.iit.sat.itmd4515.rbalasubramanian1.model.Coach;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *The abstract service class has methods to perform the CRUD operations in the database
 * i.e. to persist, merge or remove values from the db.
 * @author raghul
 */
abstract class AbstractService<T> {
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;
    
    protected Class<T> entityClass;
    /**
     *default constructor
     */
    public AbstractService(Class entityClass) {
        this.entityClass = entityClass;
    }
    /**
     *creates new entity
     */
    public void create(T entity){
        em.persist(entity);
    }
    /**
     *updates the entity
     */
    public void update(T entity){
        em.merge(entity);
    }
    /**
     *removes the entity from the db
     */
    public void remove(T entity){
        em.remove(em.merge(entity));
    }
    /**
     *find method uses the id to retrieve the entity row that matches the id
     */
    public T find(Long id){
        return em.find(entityClass, id);
    }
    /**
     *findAll method to get a entity table records
     */
    public abstract List<T> findAll();

}
