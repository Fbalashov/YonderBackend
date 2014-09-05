package com.Yonder.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;

import javax.persistence.EntityManager;

/**
 *
 * @author Fuad
 */
public abstract class AbstractService<T> {
    private Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
    
    protected abstract void closeEntityManager();

    public void create(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(entity)) {
            // persist object - add to entity manager
            em.persist(entity);
            // flush em - save to DB
            em.flush();
        }
        // commit transaction at all
        em.getTransaction().commit();
        closeEntityManager();
    }

    public void edit(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        // persist object - merge to entity manager
        em.merge(entity);
        // flush em - save to DB
        em.flush();
        // commit transaction at all
        em.getTransaction().commit();
        closeEntityManager();
    }

    public void remove(T entity) {
    	if(entity == null) {return;}
    	EntityManager em = getEntityManager();
        em.getTransaction().begin();
    	em.remove(em.contains(entity) ? entity : em.merge(entity));
        // flush em - save to DB
        em.flush();
        // commit transaction at all
        em.getTransaction().commit();
        closeEntityManager();
    }

    public T find(Object id) {
        T val = getEntityManager().find(entityClass, id);
        closeEntityManager();
        return val;
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> vals = getEntityManager().createQuery(cq).getResultList();
        closeEntityManager();
        return vals;
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        List<T> vals = q.getResultList();
        closeEntityManager();
        return vals;
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        int count = ((Long) q.getSingleResult()).intValue();
        closeEntityManager();
        return count;
    }
    
}

