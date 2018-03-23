package it.unifi.swa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;

public class OrderDaoTest extends DaoTest {

	private OrderDAO orderDao;
	private Pub pub;
	private Client client;
	private Operator cook;
	private Operator barman;
	private Operator barman2;

	private Ordine order;
	private Ordine order2;

	private List<Ordine> orderList;
	
	@Override
	protected void init() throws InitializationError {
		
		pub= new Pub();
		pub.setDescrizione("descr descr");
		client=new Client();
		cook=new Operator();
		cook.setoType('c');
		cook.setLocal(pub);
		barman=new Operator();
		barman.setoType('b');
		barman.setLocal(pub);
		barman2=new Operator();
		barman2.setoType('b');
		barman2.setLocal(pub);
		
		
		orderList= new ArrayList<Ordine>();
		
		Product p1= new Product();
		Product p2= new Product();
		Product p3= new Product();

		order=new Ordine();
		order2=new Ordine();
		
		order.setClient(client);
		order.setLocal(pub);
		order.addProduct(p1, 1);
		order.addProduct(p2, 2);
		order.addProduct(p3, 1);
		
		order2.setClient(client);
		order2.setLocal(pub);
		order2.addProduct(p1, 2);
		order2.addProduct(p3, 4);
		
	
		//order.setBarman(barman);
		//order.setCook(cook);
		
		entityManager.persist(client);
		entityManager.persist(barman);
		entityManager.persist(barman2);

		entityManager.persist(cook);

		entityManager.persist(order);
		entityManager.persist(order2);

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
	
//	@Test
//	public void getOrderByClientTest(){
//		
//
//		List<Ordine> orderList = orderDao.getOrderByClient(client);
//		
//		for (Ordine ord : orderList) {
//			
//			assertEquals(order,ord);
//		}
//		
//		//assertEquals(order,orderDao.findById(order.getIdOrder()));
//		
//	}
//	

	@Test
	public void getOrderByBarmanTest() {

		order.setTipoOrdine('d');
		order2.setTipoOrdine('f');

		orderDao.update(order);
		orderDao.update(order2);

		orderList.add(order);

		assertEquals(orderDao.getOrderByOperator(barman), orderList);
		assertEquals(orderDao.getOrderByOperator(barman2), orderList);

		order.setBarman(barman);
		assertEquals(orderDao.getOrderByOperator(barman), orderList);
		assertTrue(orderDao.getOrderByOperator(barman2).isEmpty());

		order2.setTipoOrdine('m');
		orderDao.update(order2);

		orderList.add(order2);
		assertEquals(orderDao.getOrderByOperator(barman), orderList);
		assertFalse(orderDao.getOrderByOperator(barman2).isEmpty());

	}
	

}
