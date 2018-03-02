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

import it.unifi.swa.bean.OrderBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.UserAssoDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.UserAssociation;

public class OrderListControllerTest {
	
	private OrderListController orderListController;
	private UserSessionBean userSessionBean;

	private OrderBean orderBean;
	
	private UserAssoDAO userAssoDao;
	
	private List<Ordine> orderList;
	
	private List<UserAssociation> userAssociations;
    private Conversation conversation;

    private Ordine o1;
    private Ordine o2;
	
	@Before
	public void init() throws InitializationError {
		
		userSessionBean=new UserSessionBean();
		orderBean= new OrderBean();
		orderListController=new OrderListController();
		userAssoDao= mock(UserAssoDAO.class);
		
		o1=new Ordine();
		o2=new Ordine();
		userAssociations=new ArrayList<UserAssociation>();
		
//		Operator cuoco=new Operator();
//		cuoco.setoType('c');
//		
//		Operator barista=new Operator();
//		barista.setoType('b');
		
		Client papu= new Client();
		papu.setName("Riccardo");
		papu.setSurname("Papucci");
		
		userSessionBean.setUser(papu);
		
		//userAssociations.add(o1.addUser(cuoco));
		//userAssociations.add(o1.addUser(barista));
		userAssociations.add(o1.addUser(papu,'u'));
		//userAssociations.add(o2.addUser(cuoco));
		userAssociations.add(o2.addUser(papu,'u'));
		
		orderList=new ArrayList<Ordine>();
		orderList.add(o1);
		orderList.add(o2);
		
		conversation = mock(Conversation.class);

		when(userAssoDao.getUserAssocByUser(userSessionBean.getUser())).thenReturn(userAssociations);

		try {
			FieldUtils.writeField(orderListController, "userSessionBean", userSessionBean, true);
			FieldUtils.writeField(orderListController, "orderBean", orderBean, true);
			FieldUtils.writeField(orderListController, "userAssoDao", userAssoDao, true);
			FieldUtils.writeField(orderBean, "conversation", conversation, true);


		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
	@Test
	public void getClientOrderListTest(){
		
		assertNull(orderListController.getOrderList());
		orderListController.init();
		assertEquals(orderListController.getOrderList(),orderList);

	}

	@Test
	public void toDetailOrder(){
		
		assertNull(orderBean.getOrder());
		orderListController.init();
		assertNull(orderBean.getOrder());

		orderListController.toDetailOrder(o1);
		assertEquals(orderBean.getOrder(),o1);

		
	}
}
