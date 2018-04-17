package it.unifi.swa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.dao.OrderProductDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.OPAssociation;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class SummaryControllerTest {
	
	private SummaryController summaryController;

	private MenuController menuCtrl;	
	private SelectPubBean selectPubBean;
	private UserSessionBean userSessionBean;

	
	private OrderDAO orderDao;
	private OrderProductDAO orderProductDao;
	
	private Product p1;
	private Product p2;
	private Product p3;

	private List<Product> productList;
	private Map<Product, Integer> basket;
    private List<OPAssociation> opaList;

    private Conversation conversation;
	private User client;
	private Pub pub;
	private Ordine ord;
	
	@Before
	public void init() throws InitializationError {
		
		menuCtrl= new MenuController();
		summaryController= new SummaryController();
		orderDao=mock(OrderDAO.class);
		orderProductDao=mock(OrderProductDAO.class);
		
		basket = new HashMap<Product, Integer>();
		productList = new ArrayList<Product>();
		opaList = new ArrayList<OPAssociation>();
		
		p1=new Product();
		p1.setProdName("p1");
		p2=new Product();
		p2.setProdName("p2");
		p3=new Product();
		p3.setProdName("p3");

		client=new Client();
		pub=new Pub();
		selectPubBean= new SelectPubBean();
		userSessionBean= new UserSessionBean();
		selectPubBean.setPub(pub);
		userSessionBean.setUser(client);
		
		ord=new Ordine();
		ord.setStateOrder('a');
		
		conversation=mock(Conversation.class);
		
		
		try {
			FieldUtils.writeField(summaryController, "orderDao", orderDao, true);
			FieldUtils.writeField(summaryController, "orderProductDao", orderProductDao, true);
			FieldUtils.writeField(summaryController, "selectPubBean", selectPubBean, true);
			FieldUtils.writeField(summaryController, "userSessionBean", userSessionBean, true);
			FieldUtils.writeField(summaryController, "menuCtrl", menuCtrl, true);
			FieldUtils.writeField(menuCtrl, "conversation", conversation, true);

		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
		menuCtrl.initConversation();
		menuCtrl.setOrder(ord);
	}
	
//	@Test
//	public void getProductListTest(){
//
//		basket.put(p1, 0);
//		basket.put(p2, 2);
//		basket.put(p3, 1);
//		basketBean.setBasket(basket);
//		
//		assertNull(summaryController.getProductList());
//		productList.add(p3);
//		productList.add(p2);
//		summaryController.init();
//		assertEquals(productList,summaryController.getProductList());
//	}
	
	@Test
	public void saveOrderTest(){

		opaList.add(ord.addProduct(p1, 0));
		opaList.add(ord.addProduct(p2, 2));
		opaList.add(ord.addProduct(p3, 1));
		menuCtrl.setOpaList(opaList);
		assertNull(summaryController.getOrd());

		basket.put(p1, 0);
		basket.put(p2, 2);
		basket.put(p3, 1);
		menuCtrl.setBasket(basket);
		
//		when(orderDao.insertOrder(pub)).thenReturn(ord);
		summaryController.init();

		assertNull(summaryController.getClient());
		assertNull(summaryController.getPub());

		summaryController.saveOrder();
		
		assertEquals(client,summaryController.getClient());
		assertEquals(pub,summaryController.getPub());
		//assertEquals(ord,summaryController.getOrd());
	}
	
//	@Test
//	public void getBasketNotNullTest(){
//		basket.put(p1, 0);
//		basket.put(p2, 2);
//		basket.put(p3, 1);
//		menuCtrl.setBasket(basket);
//		assertNull(summaryController.getBasketNotNull());
//		
//		basketNotNull.put(p2, 2);
//		basketNotNull.put(p3, 1);
//
//		summaryController.init();
//		assertEquals(basketNotNull,summaryController.getBasketNotNull());
//	}
	@Test
	public void foodOnlyTest(){
		p1.setTpProduct('d');
		p2.setTpProduct('f');
		p3.setTpProduct('f');
		basket.put(p1, 0);
		basket.put(p2, 2);
		basket.put(p3, 1);
		menuCtrl.setBasket(basket);
		
		summaryController.init();

		summaryController.saveOrder();
		
		assertTrue(summaryController.isFood());
		assertFalse(summaryController.isDrink());
	}
//	
	@Test
	public void drinkOnlyTest(){
		p1.setTpProduct('f');
		p2.setTpProduct('d');
		p3.setTpProduct('d');
		basket.put(p1, 0);
		basket.put(p2, 2);
		basket.put(p3, 1);
		menuCtrl.setBasket(basket);
		
		summaryController.init();

		summaryController.saveOrder();
		
		assertFalse(summaryController.isFood());
		assertTrue(summaryController.isDrink());
	}
	

}
