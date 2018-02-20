package it.unifi.swa.controller.strategy;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.dao.UserAssoDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.UserAssociation;

@Dependent
public class BaseStrategy implements Serializable {

	
	private static final long serialVersionUID = 1L;


	@Inject
	private ClientDAO clientDao;
	
	@Inject
	private OrderDAO orderDao;
	
	@Inject
	private OperatorDAO operatorDao;
	
	@Inject
	private UserAssoDAO userAssoDao;
	
	private Client client;
	
	public BaseStrategy(){
		
	}
	
	public void init(Client client){
		this.client=client;
	}
	
	
	@Transactional
	public void saveUserAsso(UserAssociation ua) {

		//System.out.println("Il cognome: "+client.getSurname());
		userAssoDao.save(ua);
	}
	@Transactional
	public void saveClient(Client client) {

		//System.out.println("Il cognome: "+client.getSurname());
		clientDao.save(client);
	}
	
	@Transactional
	public void updateOperator(Operator operator) {

		operatorDao.update(operator);
	}
	

	@Transactional
	public Operator getOperator(Operator operator) {

		return operatorDao.findById(operator.getIdUser());
	}
	
	@Transactional
	public void saveOrder(Ordine order) {

		orderDao.save(order);
	}
	
	
	
	@Transactional
	public void updateOrder(Ordine order) {

		orderDao.update(order);
	}
	
	public void getClientById(int id){
		Client cl=clientDao.findById(id);
		System.out.println("Il cliente è "+cl.getName());
	}
	

}
