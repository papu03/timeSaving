package it.unifi.swa.dao;

import java.util.List;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.User;

@Dependent
public class OperatorDAO extends BaseDao<Operator>{

	protected OperatorDAO() {
		super(Operator.class);
	}

	public Operator findByLoginInfo(User user) {
	
		  List<Operator> result = entityManager
	                .createQuery("FROM Operator o WHERE o.username = :username AND o.password = :pass", Operator.class)
	                .setParameter("username", user.getUsername()).setParameter("pass", user.getPassword()).setMaxResults(1)
	                .getResultList();
	        if (!result.isEmpty())
	            return result.get(0);
	        return null;
	}
}
