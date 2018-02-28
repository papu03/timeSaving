package it.unifi.swa.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.Menu;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;

public class MenuDaoTest extends DaoTest {

	private MenuDAO menuDao;
	private Menu menu;
	
	private Product p1;
	private Product p2;
	private Product p3;
	
	private List<Product> list;
	private Pub pub;

	
	@Override
	protected void init() throws InitializationError {
		
		menu=new Menu();
		menu.setDescr("Menu favoloso!!!");

		pub=new Pub();
		pub.setMenu(menu);
		
		p1=new Product();
		p1.setMenu(menu);
		p2=new Product();
		p2.setMenu(menu);
		p3=new Product();
		p3.setMenu(menu);

		list=new ArrayList<Product>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		menuDao= new MenuDAO();
		
		entityManager.persist(menu);
		
		try {
			FieldUtils.writeField(menuDao, "entityManager", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
	@Test
	public void getListOfProductTest(){
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.persist(p3);
		entityManager.persist(pub);
		
        assertEquals(list,menuDao.getListOfProduct(pub));
	}
	
}
