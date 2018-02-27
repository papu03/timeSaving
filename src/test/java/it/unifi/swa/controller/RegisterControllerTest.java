package it.unifi.swa.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNull;

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
		client.setUsername("papu");
		client.setPassword("pass");
		
		  client.setName("riccardo");
	        client.setSurname("papucci");
	        client.setAddress( "via di qui");
	        client.setBankData(777);
	        
	        when(clientDao.findByLoginInfo(any(Client.class))).thenReturn(client);

//	        client.setUsername(username);
//	        client.setPassword(password);
		
		try {
			FieldUtils.writeField(registerController, "clientDao", clientDao, true);
		
			//FieldUtils.writeField(registerController, "client", client, true);
			FieldUtils.writeField(registerController, "name", "riccardo", true);
			FieldUtils.writeField(registerController, "surname", "papucci", true);
			FieldUtils.writeField(registerController, "address", "via di qui", true);
			FieldUtils.writeField(registerController, "bankData", 777, true);


		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testRegisterWorks() throws InitializationError {
		
		String password="pass";
		String password_check=password;
		
		try {
		
			FieldUtils.writeField(registerController, "username", "papu", true);
			FieldUtils.writeField(registerController, "password", password, true);
			FieldUtils.writeField(registerController, "password_check", password_check, true);
		

			registerController.register();

			assertEquals(client.getUsername(), registerController.getClient().getUsername());
			assertEquals(client.getPassword(), registerController.getClient().getPassword());
			assertEquals(client.getName(), registerController.getClient().getName());
			
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
	@Test
	public void testRegisterFails() throws InitializationError {
		
		String password="pass";
		String password_check="differentPass";
		
		try {
		
			FieldUtils.writeField(registerController, "username", "papu", true);
			FieldUtils.writeField(registerController, "password", password, true);
			FieldUtils.writeField(registerController, "password_check", password_check, true);
		
			registerController.register();

			assertNull(registerController.getClient());
			
			
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}

}
