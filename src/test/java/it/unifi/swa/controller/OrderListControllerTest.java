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
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;

public class OrderListControllerTest {
	
	private OrderListController orderListController;
	private UserSessionBean userSessionBean;
	private OrderDAO orderDao;
	private OperatorDAO operatorDao;
	
	
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
		cook.setoType('c');
		cook.setIdUser(10);
		barman= new Operator();
		barman.setoType('b');
		barman.setIdUser(20);
		
		o1=new Ordine();
		o1.setIdOrder(1);
		o2=new Ordine();
		o2.setIdOrder(2);
		o3=new Ordine();
		o3.setIdOrder(3);

		
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
		operatorDao= mock(OperatorDAO.class);
		
		when(operatorDao.findById(cook.getIdUser())).thenReturn(cook);
		when(operatorDao.findById(barman.getIdUser())).thenReturn(barman);


		when(orderDao.findById(o1.getIdOrder())).thenReturn(o1);
		when(orderDao.findById(o2.getIdOrder())).thenReturn(o2);
		when(orderDao.findById(o3.getIdOrder())).thenReturn(o3);

		when( orderDao.getOrderByClient(client)).thenReturn(clientOrderList);
		when( orderDao.getOrderByOperator(cook)).thenReturn(cookOrderList);
		when( orderDao.getOrderByOperator(barman)).thenReturn(barmanOrderList);

		try {
			FieldUtils.writeField(orderListController, "userSessionBean", userSessionBean, true);
			FieldUtils.writeField(orderListController, "orderDao", orderDao, true);
			FieldUtils.writeField(orderListController, "operatorDao", operatorDao, true);

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
		
		o1.setStateOrder('a');
		assertNull(orderListController.getOrderList());
		
		userSessionBean.setUser(cook);
		userSessionBean.setType('c');

		orderListController.init();
		assertEquals(orderListController.getOrderList(),cookOrderList);

		orderListController.changeOrderState(o1.getIdOrder());
		cookOrderList.remove(o1);
		
		assertEquals(orderListController.getOrderList(),cookOrderList);
	
		assertEquals(o1.getStateOrder(),'c');
	}
	@Test
	public void getBarmanOrderListTest(){
		
		o2.setStateOrder('a');
		o2.setTipoOrdine('d');
		o3.setStateOrder('a');
		o3.setTipoOrdine('f');
		
		assertNull(orderListController.getOrderList());
		
		userSessionBean.setUser(barman);
		userSessionBean.setType('b');

		orderListController.init();
		assertEquals(orderListController.getOrderList(),barmanOrderList);

		orderListController.changeOrderState(o2.getIdOrder());
		barmanOrderList.remove(o2);
		
		assertEquals(orderListController.getOrderList(),barmanOrderList);
		assertEquals(o2.getStateOrder(),'b');
		assertEquals(o3.getStateOrder(),'a');
		
		orderListController.changeOrderState(o2.getIdOrder());
		orderListController.changeOrderState(o3.getIdOrder());
		orderListController.changeOrderState(o3.getIdOrder());

		assertEquals(o2.getStateOrder(),'g'); //concluso
		assertEquals(o3.getStateOrder(),'g'); //concluso

	}
	
	@Test
	public void barmanOrderStateMTest(){
		
		o2.setStateOrder('c'); //"Ordine Cucina in Esecuzione"
		o2.setTipoOrdine('m');
		o2.setCook(cook);
		
		userSessionBean.setUser(barman);
		userSessionBean.setType('b');

		orderListController.init();
		
		orderListController.changeOrderState(o2.getIdOrder());
		
		assertEquals(o2.getStateOrder(),'d'); //bar e cucina in esecuzione
		
		orderListController.changeOrderState(o2.getIdOrder());
	
		assertEquals(o2.getStateOrder(),'f'); //Ordine Bar concluso, Ordine Cucina in esecuzione

	}
	
	@Test
	public void cookOrderStateMTest(){
		
		o2.setStateOrder('b'); //"Ordine Bar in Esecuzione"
		o2.setTipoOrdine('m');
		o2.setBarman(barman);
		
		userSessionBean.setUser(cook);
		userSessionBean.setType('c');

		orderListController.init();
		
		orderListController.changeOrderState(o2.getIdOrder());
		
		assertEquals(o2.getStateOrder(),'d'); //bar e cucina in esecuzione
		
		orderListController.changeOrderState(o2.getIdOrder());
	
		assertEquals(o2.getStateOrder(),'e'); //Ordine Cucina concluso, Ordine Bar in esecuzione

	}
	

		

}
