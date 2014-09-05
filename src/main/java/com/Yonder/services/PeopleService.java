package com.Yonder.services;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.RandomStringUtils;

import com.Yonder.model.People;
import com.Yonder.servlets.EntityManagerLoaderListener;
import com.Yonder.vo.People_;

/**
 * Author: Fuad
 */

@Path("people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleService extends AbstractService<People> {
	
	EntityManager em = null;

    public PeopleService() {
        super(People.class);
    }

    @POST
    @Path("new")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String createNew(People entity) {
    	String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String privateKey = RandomStringUtils.random(50, charset);
        entity.setPrivateKey(privateKey);
        super.create(entity);
        return privateKey;
    }
    
    @POST
    @Override
    @Path("deprecated")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(People entity) {
        throw new NoSuchMethodError("please call this method with the addition 'differentiation' argument");
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, People entity) {
    	if( !(find(id) == null)) {
	    	entity.setPrivateKey(id);
	        super.edit(entity);
    	}
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    public People find(@PathParam("id") String id) {
        People val = getEntityManager().find(People.class, id);
        closeEntityManager();
        return val;
    }
    

    @GET
    @Path("findMatch/{pref}/{id}")
    public People findMatch( @PathParam("pref") String pref, @PathParam("id") String id) {
    	EntityManager em = getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(People.class));
        Root<People> people = cq.from(People.class);
        //get people whose sexes match the requested preference and are active
        char c;
        if(pref != null && pref.length() == 1){
            c = pref.charAt(0);
        } else {
            throw new IllegalArgumentException("must include preference parameter and user id in get arguments!");
        }
        cq.where(people.get(People_.sex).in(c));
        cq.where(people.get(People_.active).in(true));
        //need to:
        //  1. TODO: filter out past matches, 
        //  2. TODO: not allow match to self
        List<People> interests = em.createQuery(cq).getResultList();
        em.close();
        Collections.shuffle(interests);
        return interests.get(0);
    }
    
    @GET
    @Path("findMatch/{pref}/{u_id}/{other_person}/{rate}")
    public People rateAndMatch(@PathParam("pref") String pref, @PathParam("rate") Boolean rate,
            @PathParam("other_person") String other_id,  @PathParam("u_id") String u_id) {
        // Do the following:
        //  1. TODO: add new match to match list
        //  2. Update ranking for other person
        if(rate){
            People other = find(other_id);
            other.incrementRank();
            this.edit(other);
        }
        //  3. return new person to rank
        return findMatch(pref, u_id);
    }
    
    @GET
    @Override
    public List<People> findAll() {
    	//TODO: require the user to pass their id in!!! Maybe get rid of this
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    public List<People> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        throw new NoSuchMethodError();
    }

    @GET
    @Path("count")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("complete/{id}")	
    public boolean profileComplete(@PathParam("id") String id){
        People person = this.find(id);
        if(null == person) {
            return false;
        }
        else if (null!= person.getSexualPreference() &&
                null!= person.getSex() &&
                null != person.getPicUrl1() &&
                null!= person.getName()) {
            return true;
        }
        return false;
    }

    @Override
    protected EntityManager getEntityManager() {
    	em = EntityManagerLoaderListener.createEntityManager();
        return em;
    }  
    
    @Override
    protected void closeEntityManager() {
    	em.close();
    }
}