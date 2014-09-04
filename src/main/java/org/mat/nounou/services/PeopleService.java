package org.mat.nounou.services;

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
import org.mat.nounou.model.People;
import org.mat.nounou.servlets.EntityManagerLoaderListener;
import org.mat.nounou.vo.People_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Value Object for Appointment
 * AppointmentVO: mlecoutre
 * Date: 28/10/12
 * Time: 11:13
 */

@Path("people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleService extends AbstractService<People> {

    private EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(PeopleService.class);

    public PeopleService() {
        super(People.class);
        em = EntityManagerLoaderListener.createEntityManager();
    }

    @POST
    @Path("new")
    @Consumes({"application/xml", "application/json"})
    public String createNew(People entity) {
        String privateKey = RandomStringUtils.random(50);
        entity.setPrivateKey(privateKey);
        super.create(entity);
        return privateKey;
    }
    
    @POST
    @Override
    @Path("deprecated")
    @Consumes({"application/xml", "application/json"})
    public void create(People entity) {
        throw new NoSuchMethodError("please call this method with the addition 'differentiation' argument");
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, People entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    public People find(@PathParam("id") String id) {
        return super.find(id);
    }
    

    @GET
    @Path("findMatch/{pref}/{id}")
    public People findMatch( @PathParam("pref") String pref, @PathParam("id") String id) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
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
        //  1.filter out past matches, 
        //  2. not allow match to self
        List<People> interests = getEntityManager().createQuery(cq).getResultList();
        Collections.shuffle(interests);
        return interests.get(0);
    }
    
    @GET
    @Path("findMatch/{pref}/{u_id}/{other_person}/{rate}")
    public People rateAndMatch(@PathParam("pref") String pref, @PathParam("rate") Boolean rate,
            @PathParam("other_person") String other_id,  @PathParam("u_id") String u_id) {
        // Do the following:
        //  *1. add new match to match list*
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
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    public List<People> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
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
}