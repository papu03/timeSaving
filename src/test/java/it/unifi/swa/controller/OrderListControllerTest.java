package it.unifi.swa.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;

public class OrderListControllerTest {
	
	private OrderListController orderListController;
	private UserSessionBean userSessionBean;
	private OrderDAO orderDao;
	
	
	private List<Ordine> clientOrderList;
	private List<Ordine> cookOrderList;
	private List<Ordine> barmanOrderList;
	
    private Ordine o1;
    private Ordine o2;
    private Ordine o3;
    
    private Client client;
    private Operator cook;
    private Operator barman;

	
	@Before
	public void init() throws InitializationError {
		
		userSessionBean=new UserSessionBean();
		orderListController=new OrderListController();
		
		client= new Client();
		cook= new Operator();
		barman= new Operator();
		
		o1=new Ordine();
		o2=new Ordine();
		o3=new Ordine();
		
		clientOrderList=new ArrayList<Ordine>();
		cookOrderList=new ArrayList<Ordine>();
		barmanOrderList=new ArrayList<Ordine>();

		clientOrderList.add(o1);
		clientOrderList.add(o2);
		clientOrderList.add(o3);
		
		cookOrderList.add(o1);
		
		barmanOrderList.add(o2);
		barmanOrderList.add(o3);
		
		orderDao= mock(OrderDAO.class);

		when( orderDao.getOrderByClient(client)).thenReturn(clientOrderList);
		when( orderDao.getOrderByCook(cook)).thenReturn(cookOrderList);
		when( orderDao.getOrderByBarman(barman)).thenReturn(barmanOrderList);

		try {
			FieldUtils.writeField(orderListController, "userSessionBean", userSessionBean, true);
			FieldUtils.writeField(orderListController, "orderDao", orderDao, true);
		
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
	@Test
	public void getClientOrderListTest(){
		
		assertNull(orderListController.getOrderList());
		
		userSessionBean.setUser(client);
		userSessionBean.setType('u');

		orderListController.init();
		assertEquals(orderListController.getOrderList(),clientOrderList);

	}
	@Test
	public void getCookOrderListTest(){
		
		/*assertNull(orderListController.getOrderList());
		
		userSessionBean.setUser(cook);
		userSessionBean.setType('c');

		orderListController.init();
		assertEquals(orderListController.getOrderList(),cookOrderList);*/

	}
	@Test
	public void getBarmanOrderListTest(){
		
		/*assertNull(orderListController.getOrderList());
		
		userSessionBean.setUser(barman);
		userSessionBean.setType('b');

		orderListController.init();
		assertEquals(orderListController.getOrderList(),barmanOrderList);*/

	}

}
