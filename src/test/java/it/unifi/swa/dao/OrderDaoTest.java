package it.unifi.swa.dao;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Pub;

public class OrderDaoTest extends DaoTest {

	private OrderDAO orderDao;
	private Pub pub;
	
	@Override
	protected void init() throws InitializationError {
		
		pub= new Pub();
		pub.setDescrizione("descr descr");
		
		entityManager.persist(pub);
		orderDao=new OrderDAO();
		
		try {
			FieldUtils.writeField(orderDao, "entityManager", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
	@Test
	public void insertOrderTest(){
		
		Ordine order=orderDao.insertOrder(pub,true,false);
		assertEquals(order,orderDao.findById(order.getIdOrder()));
		
	}

}
