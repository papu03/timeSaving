package it.unifi.swa.controller;

import static org.mockito.Mockito.mock;

import javax.enterprise.context.Conversation;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.domain.Client;

public class RegisterControllerTest {
	
	 private ClientDAO clientDao;
	 private RegisterController registerController;
	 private Client client;

	 
	@Before
	public void init() throws InitializationError {
		
		clientDao = mock(ClientDAO.class);

		registerController=new RegisterController();
		
		client=new Client();
		  client.setName("riccardo");
	        client.setSurname("papucci");
	        client.setAddress( "via di qui");
	        client.setBankData(777);
//	        client.setUsername(username);
//	        client.setPassword(password);
		
		try {
			FieldUtils.writeField(registerController, "clientDao", clientDao, true);
		
//			FieldUtils.writeField(registerController, "client", client, true);
//			FieldUtils.writeField(registerController, "name", "riccardo", true);
//			FieldUtils.writeField(registerController, "surname", "papucci", true);
//			FieldUtils.writeField(registerController, "address", "via di qui", true);
//			FieldUtils.writeField(registerController, "bankData", 777, true);


		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testRegister() throws InitializationError {
		
//		try {
//		
//			FieldUtils.writeField(registerController, "username", "papu", true);
//			FieldUtils.writeField(registerController, "password", "pass", true);
//			FieldUtils.writeField(registerController, "password_check", "pass", true);
//	
//		} catch (IllegalAccessException e) {
//			throw new InitializationError(e);
//		}
        client.setUsername("papu");
        client.setPassword("pass");
		
		
	}

}
