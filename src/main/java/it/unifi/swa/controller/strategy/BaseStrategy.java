package it.unifi.swa.controller.strategy;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.domain.Client;

@Dependent
public class BaseStrategy implements Serializable {

	
	private static final long serialVersionUID = 1L;


	@Inject
	private ClientDAO clientDao;
	
	
	private Client client;
	
	public BaseStrategy(){
		
	}
	
	public void init(Client client){
		this.client=client;
	}
	
	@Transactional
	public void save(Client client) {

		//System.out.println("Il cognome: "+client.getSurname());
		clientDao.save(client);
	}
	
	public void getClientById(int id){
		Client cl=clientDao.findById(id);
		System.out.println("Il cliente è "+cl.getName());
	}
	

}
