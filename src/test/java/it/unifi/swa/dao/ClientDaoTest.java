package it.unifi.swa.dao;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.Client;

public class ClientDaoTest extends DaoTest {

	Client client;
	ClientDAO clientDao;

	@Override
	protected void init() throws InitializationError {

		client = new Client();
		client.setAddress("Via kal");
		client.setBankData(648533);
		client.setName("Riccardo");
		client.setSurname("papucci");
		client.setPhoneNumber(33311029);
		client.setUsername("user");
		client.setPassword("password");

		clientDao = new ClientDAO();
		try {
			FieldUtils.writeField(clientDao, "entityManager", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
	}


	@Test
	public void testFindByLoginInfo() {
		
		entityManager.persist(client);
		Client client2= clientDao.findByLoginInfo(client);
        assertEquals(client,client2);
	}
	
	@Test
	public void saveClientTest(){
		
		clientDao.saveClient(client);
		
		assertEquals(client, clientDao.findByLoginInfo(client));
		
	}

}
