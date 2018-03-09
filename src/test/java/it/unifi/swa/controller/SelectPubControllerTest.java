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

import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Pub;

public class SelectPubControllerTest {

	private SelectPubBean selectPubBean;
    private UserSessionBean userSessionBean;

	private SelectPubController selectPubController;
    private MenuController menuCtrl;
    private Conversation conversation;

	private Pub selectedPub;


	private List<Pub> pubList;

	private PubDAO pubDao;
    private OperatorDAO operatorDao;

    private Operator operator;
    private Pub operatorPub;
    
	@Before
	public void init() throws InitializationError {

		selectPubBean = new SelectPubBean();
		userSessionBean=new UserSessionBean();
		selectPubController = new SelectPubController();
		
		menuCtrl= new MenuController();
		conversation=mock(Conversation.class);
		
		selectedPub = new Pub();
		selectedPub.setNome("My pub");

		pubDao = mock(PubDAO.class);
		operatorDao=mock(OperatorDAO.class);
		
		pubList = new ArrayList<Pub>();
		
		operatorPub= new Pub();
		Pub p2 = new Pub();
		pubList.add(operatorPub);
		pubList.add(p2);

		
		operator= new Operator();
		operator.setLocal(operatorPub);
		
		when(pubDao.getListOfPub()).thenReturn(pubList);


		try {
			FieldUtils.writeField(selectPubController, "selectPubBean", selectPubBean, true);
			FieldUtils.writeField(selectPubController, "userSessionBean", userSessionBean, true);
			FieldUtils.writeField(selectPubController, "selectedPub", selectedPub, true);
			FieldUtils.writeField(selectPubController, "pubDao", pubDao, true);
			FieldUtils.writeField(selectPubController, "operatorDao", operatorDao, true);
			FieldUtils.writeField(selectPubController, "menuCtrl", menuCtrl, true);
			FieldUtils.writeField(menuCtrl, "conversation", conversation, true);


		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}

	@Test
	public void testSelect() {

		assertNull(selectPubBean.getPub());
		selectPubController.select();
		assertEquals(selectedPub, selectPubBean.getPub());
	}


	@Test
	public void testGetPubList() {

		assertNull(selectPubController.getPubList());
		userSessionBean.setType('u');

		selectPubController.init();
		assertEquals(pubList, selectPubController.getPubList());
		assertNull(selectPubController.getOperatorPub());
	}
	
	@Test
	public void testGetOperatorPub() {

		assertNull(selectPubController.getOperatorPub());
		userSessionBean.setType('c');
		userSessionBean.setUser(operator);
		
		when(operatorDao.findByLoginInfo(userSessionBean.getUser())).thenReturn(operator);


		selectPubController.init();
		assertEquals(operatorPub, selectPubController.getOperatorPub());
		assertNull(selectPubController.getPubList());
	}


}
