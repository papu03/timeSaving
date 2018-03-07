package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Pub;
import java.util.List;

@Dependent
public class PubDAO extends BaseDao<Pub> {

    protected PubDAO() {
        super(Pub.class);
    }

    public List<Pub> getListOfPub() {

        List<Pub> result = entityManager.createQuery("from Pub p", Pub.class).getResultList();
        if (!result.isEmpty()) {
            return result;
        } else {
            return null;
        }

    }
    
    public Pub getPubById(String idPub){
    	  
    	int pubId= Integer.parseInt(idPub);
    	
    	Pub result = entityManager
	                .createQuery("FROM Pub p WHERE p.idLocale = :idPub", Pub.class)
	                .setParameter("idPub", pubId)
	                .getSingleResult();
		  
	      return result;
    }

}
