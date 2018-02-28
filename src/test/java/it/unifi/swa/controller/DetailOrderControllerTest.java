package it.unifi.swa.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Conversation;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.bean.OrderBean;
import it.unifi.swa.dao.OrderProductDAO;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;

public class DetailOrderControllerTest {
	
	private DetailOrderController detailOrderController;
	private OrderBean orderBean;
	private OrderProductDAO orderProductDao;
	private Map<Product, Integer> qntProductMap;
	
	private Product p1;
	private Product p2;
	private Product p3;
	
	private Conversation conversation;
	private Ordine o1;
	
	@Before
	public void init() throws InitializationError {
	
		detailOrderController=new DetailOrderController();
		orderBean= new OrderBean();
		orderProductDao=mock(OrderProductDAO.class);
		
		p1=new Product();
		p2=new Product();
		p3=new Product();
		
		qntProductMap=new HashMap<Product, Integer>();

		qntProductMap.put(p1, 1);
		qntProductMap.put(p2, 2);
		qntProductMap.put(p3, 1);
		
		o1=new Ordine();
		orderBean.setOrder(o1);
		
		conversation=mock(Conversation.class);
		
		when(orderProductDao.getProdQntByOrder(orderBean.getOrder())).thenReturn(qntProductMap);

		
		try {
			FieldUtils.writeField(detailOrderController, "orderBean", orderBean, true);
			FieldUtils.writeField(detailOrderController, "orderProductDao", orderProductDao, true);
			FieldUtils.writeField(orderBean, "conversation", conversation, true);

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
