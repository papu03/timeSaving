package it.unifi.swa.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.validation.constraints.AssertFalse;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.User;

public class LoginControllerTest {

	private LoginController loginController;
	private ClientDAO clientDao;
	private UserSessionBean userSession;
	private OperatorDAO operatorDao;
	private String username;
	private String password;
	
	private Client client;
	private Operator operator;

	@Before
	public void init() throws InitializationError {

		userSession = new UserSessionBean();
        loginController = new LoginController();

		clientDao = mock(ClientDAO.class);
		operatorDao = mock(OperatorDAO.class);

		username = "username";
		password = "password";
		client = new Client();
		client.setUsername(username);
		client.setPassword(password);
		operator= new Operator();
		operator.setUsername(username);
		operator.setPassword(password);

		loginController.setUsername(username);
		loginController.setPassword(password);
		


		try {
			FieldUtils.writeField(loginController, "clientDao", clientDao, true);
			FieldUtils.writeField(loginController, "operatorDao", operatorDao, true);
			FieldUtils.writeField(loginController, "userSession", userSession, true);

		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}
	

    @Test
    public void testClientLoginSuccess() {
        when(clientDao.findByLoginInfo(any(Client.class))).thenReturn(client);
        when(operatorDao.findByLoginInfo(any(Operator.class))).thenReturn(null);
        loginController.login();
        assertEquals(client, userSession.getUser());
        assertTrue(userSession.isLogged());
    }
    
    @Test
    public void testOperatorLoginSuccess() {
        when(clientDao.findByLoginInfo(any(Client.class))).thenReturn(null);
        when(operatorDao.findByLoginInfo(any(Operator.class))).thenReturn(operator);
        loginController.login();
        assertEquals(operator, userSession.getUser());
        assertTrue(userSession.isLogged());
    }
    
    @Test
    public void testLoginFail() {
        when(clientDao.findByLoginInfo(any(Client.class))).thenReturn(null);
        when(operatorDao.findByLoginInfo(any(Operator.class))).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            loginController.login();
        });
        
        assertFalse(userSession.isLogged());
        assertNull(userSession.getUser());
    }
    
    @Test
    public void testLogout() {
    	
        when(clientDao.findByLoginInfo(any(Client.class))).thenReturn(client);
    	loginController.login();
    	
    	loginController.logOut();
        
        assertFalse(userSession.isLogged());
        assertNull(userSession.getUser());
    }


}
