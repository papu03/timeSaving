package it.unifi.swa.dao;

import java.util.List;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.User;

@Dependent
public class ClientDAO extends BaseDao<Client>{

	
	private static final long serialVersionUID = 1L;
	

	public ClientDAO(){
        super(Client.class);
	}
	
	public Client findByLoginInfo(User user) {

		  List<Client> result =entityManager.createQuery("from Client c where c.username = :username and c.password = :password", Client.class)
					.setParameter("username", user.getUsername())
					.setParameter("password", user.getPassword())
					.getResultList();
	        if (!result.isEmpty())
	            return result.get(0);
	        return null;
	}
	
}


