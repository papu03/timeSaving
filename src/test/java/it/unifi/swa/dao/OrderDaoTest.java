package it.unifi.swa.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Pub;

public class OrderDaoTest extends DaoTest {

	private OrderDAO orderDao;
	private Pub pub;
	private Client client;
	private Operator cook;
	private Operator barman;
	private Ordine order;
	
	@Override
	protected void init() throws InitializationError {
		
		pub= new Pub();
		pub.setDescrizione("descr descr");
		client=new Client();
		cook=new Operator();
		barman=new Operator();
		order=new Ordine();
		
		order.setClient(client);
		order.setBarman(barman);
		order.setCook(cook);
		
		entityManager.persist(client);
		entityManager.persist(barman);
		entityManager.persist(cook);

		entityManager.persist(order);
		entityManager.persist(pub);
		orderDao=new OrderDAO();
		
		try {
			FieldUtils.writeField(orderDao, "entityManager", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
//	@Test
//	public void insertOrderTest(){
//		
//		Ordine order=orderDao.insertOrder(pub,true,false);
//		assertEquals(order,orderDao.findById(order.getIdOrder()));
//		
//	}
	
	@Test
	public void getOrderByClientTest(){
		

		List<Ordine> orderList = orderDao.getOrderByClient(client);
		
		for (Ordine ord : orderList) {
			
			assertEquals(order,ord);
		}
		
		//assertEquals(order,orderDao.findById(order.getIdOrder()));
		
	}

}
