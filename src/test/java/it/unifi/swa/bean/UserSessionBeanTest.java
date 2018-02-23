package it.unifi.swa.bean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import it.unifi.swa.domain.User;



public class UserSessionBeanTest {
	
	private UserSessionBean userSession;
	
	 @Before
	    public void setUp() {
	        userSession = new UserSessionBean();
	    }
	 
	 @Test
	    public void testIsLoggedIn() {
	        assertFalse(userSession.isLogged());

	        userSession.setUser(new User());
	        assertTrue(userSession.isLogged());
	    }

}
