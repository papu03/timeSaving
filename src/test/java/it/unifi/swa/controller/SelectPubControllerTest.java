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

import it.unifi.swa.bean.PubBean;
import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Pub;

public class SelectPubControllerTest {

	private SelectPubBean selectPubBean;
	private SelectPubController selectPubController;
	private Pub selectedPub;

	private PubBean pubBean;
	private Conversation conversation;

	private List<Pub> pubList;
	private PubDAO pubDao;

	@Before
	public void init() throws InitializationError {

		selectPubBean = new SelectPubBean();
		selectPubController = new SelectPubController();

		selectedPub = new Pub();
		selectedPub.setNome("My pub");

		pubBean = new PubBean();
		conversation = mock(Conversation.class);

		pubDao = mock(PubDAO.class);
		pubList = new ArrayList<Pub>();

		try {
			FieldUtils.writeField(selectPubController, "selectPubBean", selectPubBean, true);
			FieldUtils.writeField(selectPubController, "selectedPub", selectedPub, true);

			FieldUtils.writeField(selectPubController, "pubBean", pubBean, true);
			FieldUtils.writeField(pubBean, "conversation", conversation, true);

			FieldUtils.writeField(selectPubController, "pubDao", pubDao, true);

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
	public void testShowInfo() {

		assertNull(pubBean.getSelectedPub());
		selectPubController.showInfo();
		assertEquals(selectedPub, pubBean.getSelectedPub());

	}

	@Test
	public void testGetPubList() {

		Pub p1 = new Pub();
		Pub p2 = new Pub();
		pubList.add(p1);
		pubList.add(p2);

		when(pubDao.getListOfPub()).thenReturn(pubList);

		assertEquals(pubList, selectPubController.getPubList());
	}
	
	@Test
	public void testGetPubListEmpty() {

		
		when(pubDao.getListOfPub()).thenReturn(pubList);
		assertNull(selectPubController.getPubList());
	}

}
