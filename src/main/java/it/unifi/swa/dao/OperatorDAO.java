package it.unifi.swa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;

import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.User;

@Dependent
public class OperatorDAO extends BaseDao<Operator>{

	protected OperatorDAO() {
		super(Operator.class);
	}

	public Operator findByLoginInfo(User user) {
	
		  Operator result = entityManager
	                .createQuery("FROM Operator o WHERE o.username = :username AND o.password = :pass", Operator.class)
	                .setParameter("username", user.getUsername()).setParameter("pass", user.getPassword()).setMaxResults(1)
	                .getSingleResult();
		  
	      return result;
	}
	
	public List<Operator> getOperators(Ordine ord, boolean isFood, boolean isDrink) {

		List<Operator> cook = null;
		List<Operator> barman = null;
		List<Operator> operators=new ArrayList<Operator>();

		try {
			

			if (isFood) {

				cook =  entityManager.createQuery("from Operator o where o.oType = :oType AND o.local= :local", Operator.class)
						.setParameter("oType", 'c').
						setParameter("local", ord.getLocal()).
						getResultList();
				
				operators.add(cook.get(0));//prendiamo il primo per semplicità
			}

			if (isDrink) {

				barman = entityManager.createQuery("from Operator o where o.oType = :oType AND o.local= :local", Operator.class)
						.setParameter("oType", 'b').
						setParameter("local", ord.getLocal()).
						getResultList();


				operators.add(barman.get(0));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return operators;
	}
	
	
}
