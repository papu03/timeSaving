package it.unifi.swa.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.UserAssociation;

public class UserAssoDaoTest extends DaoTest {

	private UserAssoDAO userAssoDao;
	private OperatorDAO operatorDao;
	private ClientDAO clientDao;
	
	private Client client;
	private Operator cuoco;
	private Operator barista;
	private Pub pub;
	private List<UserAssociation> userAssociations;
	private Ordine o1;
	private Ordine o2;

	
	@Override
	protected void init() throws InitializationError {
		
		pub=new Pub();
		
		client=new Client();
		
		cuoco=new Operator();
		cuoco.setoType('c');
		cuoco.setLocal(pub);
		
		barista=new Operator();
		barista.setoType('b');
		barista.setLocal(pub);
		
		entityManager.persist(pub);
		entityManager.persist(client);
		entityManager.persist(cuoco);
		entityManager.persist(barista);
		
		o1=new Ordine();
		o2=new Ordine();
		
		entityManager.persist(o1);
		entityManager.persist(o2);
		
//		UserAssociation uassociation1=o1.addUser(cuoco);
//		UserAssociation uassociation2=o1.addUser(barista);
		
//		UserAssociation uassociation5=o2.addUser(barista);
		
		userAssociations=new ArrayList<UserAssociation>();
//		userAssociations.add(uassociation1);
//		userAssociations.add(uassociation2);
		
//		userAssociations.add(order2.addUser(cuoco));

		
//		entityManager.persist(uassociation1);
//		entityManager.persist(uassociation2);
//		entityManager.persist(uassociation5);

		
		userAssoDao= new UserAssoDAO();
		
		try {
			FieldUtils.writeField(userAssoDao, "entityManager", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
	@Test
	public void  insertUserAssociationDrinkOnlyTest(){
		
		boolean isFood=false;
		boolean isDrink=true;
		
		List<UserAssociation> myUserAssociations= userAssoDao.insertUserAssociation(o1, client, isFood, isDrink);
		
		UserAssociation uassociation1=o1.addUser(client);
		UserAssociation uassociation2=o1.addUser(barista);

		userAssociations.add(uassociation1);
		userAssociations.add(uassociation2);


//		List<UserAssociation> myUserAssociations2=userAssoDao.getUserAssocByOrder(o1);
//		for (UserAssociation element : myUserAssociations2) {
//			System.out.println(element.getOrdineId()+" "+element.getUtenteId());
//
//		}
		assertEquals(userAssociations.get(0).getOrdineId(),myUserAssociations.get(0).getOrdineId());
		assertEquals(userAssociations.get(0).getUtenteId(),myUserAssociations.get(0).getUtenteId());
//		assertEquals(userAssociations.get(1).getOrdineId(),myUserAssociations.get(1).getOrdineId());
//		assertEquals(userAssociations.get(1).getUtenteId(),myUserAssociations.get(1).getUtenteId());

		//assertEquals(userAssociations,userAssoDao.getUserAssocByOrder(o1));

	}
	
	@Test
	public void  insertUserAssociationFoodOnlyTest(){
		
		boolean isFood=true;
		boolean isDrink=false;
		
		List<UserAssociation> myUserAssociations= userAssoDao.insertUserAssociation(o1, client, isFood, isDrink);
		
		UserAssociation uassociation1=o1.addUser(client);
		UserAssociation uassociation2=o1.addUser(cuoco);

		userAssociations.add(uassociation1);
		userAssociations.add(uassociation2);

		assertEquals(userAssociations.get(0).getOrdineId(),myUserAssociations.get(0).getOrdineId());
		assertEquals(userAssociations.get(0).getUtenteId(),myUserAssociations.get(0).getUtenteId());
//		assertEquals(userAssociations.get(1).getOrdineId(),myUserAssociations.get(1).getOrdineId());
//		assertEquals(userAssociations.get(1).getUtenteId(),myUserAssociations.get(1).getUtenteId());


	}
	
	@Test
	public void  insertUserAssociationFoodAndDrinkTest(){
		
		boolean isFood=true;
		boolean isDrink=true;
		
		List<UserAssociation> myUserAssociations= userAssoDao.insertUserAssociation(o1, client, isFood, isDrink);
		
		UserAssociation uassociation1=o1.addUser(client);
		UserAssociation uassociation2=o1.addUser(cuoco);
		UserAssociation uassociation3=o1.addUser(barista);

		userAssociations.add(uassociation1);
		userAssociations.add(uassociation2);
		userAssociations.add(uassociation3);

		assertEquals(userAssociations.get(0).getOrdineId(),myUserAssociations.get(0).getOrdineId());
		assertEquals(userAssociations.get(0).getUtenteId(),myUserAssociations.get(0).getUtenteId());
//		assertEquals(userAssociations.get(1).getOrdineId(),myUserAssociations.get(1).getOrdineId());
//		assertEquals(userAssociations.get(1).getUtenteId(),myUserAssociations.get(1).getUtenteId());
//		assertEquals(userAssociations.get(2).getOrdineId(),myUserAssociations.get(2).getOrdineId());
//		assertEquals(userAssociations.get(2).getUtenteId(),myUserAssociations.get(2).getUtenteId());


	}


	@Test
	public void  getUserAssocByClientTest(){
		
		UserAssociation uassociation1=o1.addUser(client);
		UserAssociation uassociation2=o2.addUser(client);
		
		userAssociations.add(uassociation1);
		userAssociations.add(uassociation2);
		
		entityManager.persist(uassociation1);
		entityManager.persist(uassociation2);
		
		assertEquals(userAssociations,userAssoDao.getUserAssocByUser(client));
		
	}
	
	@Test
	public void  getUserAssocByOperatorTest(){
		
		UserAssociation uassociation1=o1.addUser(cuoco);
		UserAssociation uassociation2=o2.addUser(barista);
		
		userAssociations.add(uassociation1);
		
		entityManager.persist(uassociation1);
		entityManager.persist(uassociation2);
		
		assertEquals(userAssociations,userAssoDao.getUserAssocByUser(cuoco));
		
	}
	
	@Test
	public void getUserAssocByOrderTest(){
		UserAssociation uassociation1=o1.addUser(client);
		UserAssociation uassociation2=o1.addUser(cuoco);
		UserAssociation uassociation3=o1.addUser(barista);
		
		userAssociations.add(uassociation1);
		userAssociations.add(uassociation2);
		userAssociations.add(uassociation3);

		entityManager.persist(uassociation1);
		entityManager.persist(uassociation2);
		entityManager.persist(uassociation3);
		
		assertEquals(userAssociations,userAssoDao.getUserAssocByOrder(o1));

		
		


	}
}
