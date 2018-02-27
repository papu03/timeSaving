package it.unifi.swa.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


import it.unifi.swa.bean.BasketBean;
import it.unifi.swa.bean.ProductBean;
import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;

public class MenuControllerTest {
	
	
	private SelectPubBean selectPubBean;
	private BasketBean basketBean;
	private ProductBean productBean;

	@Inject
	private MenuDAO menuDao;
	private MenuController menuController;

	private List<Product> list;
	private Map<Product, Integer> basket;
	
	private Product p1;
	private Product p2;
	private Product p3;
	
    private Product selectedProduct;	
    private Conversation conversation;

    
	@Before
	public void init() throws InitializationError {
		
		menuController=new MenuController();
		selectPubBean=new SelectPubBean();
		basketBean=new BasketBean();
		productBean=new ProductBean();
		
		selectPubBean.setPub(new Pub());
		menuDao=mock(MenuDAO.class);
		list= new ArrayList<Product>();
		basket = new HashMap<Product, Integer>();

		p1=new Product();
		p2=new Product();
		p3=new Product();

		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		for (Product element : list) {
			basket.put(element, 0);
		}
		
		selectedProduct=p1;
		productBean=new ProductBean();
		conversation = mock(Conversation.class);
		
		basketBean=new BasketBean();

		try {
			FieldUtils.writeField(menuController, "selectPubBean", selectPubBean, true);
			FieldUtils.writeField(menuController, "menuDao", menuDao, true);
			FieldUtils.writeField(menuController, "productBean", productBean, true);
			FieldUtils.writeField(menuController, "selectedProduct", selectedProduct, true);
			FieldUtils.writeField(menuController, "basketBean", basketBean, true);
			FieldUtils.writeField(productBean, "conversation", conversation, true);


		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testGetProductList() {


		when(menuDao.getListOfProduct(selectPubBean.getPub())).thenReturn(list);
		menuController.init();
		assertEquals(list, menuController.getProductList());
	}
	
	@Test
	public void testInitFails() {
		
		when(menuDao.getListOfProduct(selectPubBean.getPub())).thenReturn(null);
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
			menuController.init();
		});
		assertNull(menuController.getProductList());
		basket.clear();
		assertEquals(basket,menuController.getBasket());

	}
	
	@Test
	public void testGetBasket() {
		
		when(menuDao.getListOfProduct(selectPubBean.getPub())).thenReturn(list);
		menuController.init();
		assertEquals(basket,menuController.getBasket());
	}
	
	@Test
	public void addItemTest() {
		
		when(menuDao.getListOfProduct(selectPubBean.getPub())).thenReturn(list);
		menuController.init();
		
		for (Map.Entry<Product, Integer> entry : menuController.getBasket().entrySet()) {
			assertTrue(entry.getValue().equals(0));
		}
		
		menuController.addItem(p1);
		
		for (Map.Entry<Product, Integer> entry : menuController.getBasket().entrySet()) {
			
			if (entry.getKey().equals(p1)) {
				assertTrue(entry.getValue().equals(1));
			}else{
				assertTrue(entry.getValue().equals(0));
			}
		}
		
		menuController.addItem(p1);
		
		for (Map.Entry<Product, Integer> entry : menuController.getBasket().entrySet()) {
			
			if (entry.getKey().equals(p1)) {
				assertTrue(entry.getValue().equals(2));
			}else{
				assertTrue(entry.getValue().equals(0));
			}
		}
		
	}

	@Test
	public void showProductDetailsTest(){
		assertNull(productBean.getProduct());
		menuController.showProductDetails();
		assertEquals(selectedProduct, productBean.getProduct());
	}
	
	@Test
	public void toSummaryPageTest(){
		when(menuDao.getListOfProduct(selectPubBean.getPub())).thenReturn(list);
		menuController.init();
		
		assertTrue(basketBean.getBasket().isEmpty());
		menuController.toSummaryPage();
		assertEquals(basket, basketBean.getBasket());
	}


}
