package it.unifi.swa.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Conversation;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OrderProductDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;

public class DetailOrderControllerTest {
	
	private DetailOrderController detailOrderController;
	private UserSessionBean userSessionBean;
	private OrderProductDAO orderProductDao;
	private Map<Product, Integer> qntProductMap;
	
	private Product p1;
	private Product p2;
	private Product p3;
	
	private Ordine o1;
	private Integer orderId;

	@Before
	public void init() throws InitializationError {
	
		detailOrderController=new DetailOrderController();
		
		userSessionBean= new UserSessionBean();
		userSessionBean.setType('u');
		
		orderProductDao=mock(OrderProductDAO.class);
		
		
		p1=new Product();
		p2=new Product();
		p3=new Product();
		
		qntProductMap=new HashMap<Product, Integer>();

		qntProductMap.put(p1, 1);
		qntProductMap.put(p2, 2);
		qntProductMap.put(p3, 1);
		
		o1=new Ordine();
		orderId=o1.getIdOrder();
		
		when(orderProductDao.getProdQntByIdOrder(orderId,userSessionBean.getType())).thenReturn(qntProductMap);

		
		try {
			FieldUtils.writeField(detailOrderController, "orderId", orderId.toString(), true);
			FieldUtils.writeField(detailOrderController, "orderProductDao", orderProductDao, true);
			FieldUtils.writeField(detailOrderController, "userSessionBean", userSessionBean, true);

		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void getQntProductMapTest(){
		assertNull(detailOrderController.getQntProductMap());
		detailOrderController.init();
		assertEquals(detailOrderController.getQntProductMap(),qntProductMap);
	}


}
