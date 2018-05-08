package it.unifi.swa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.OPAssociation;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


public class OrderProductDaoTest extends DaoTest {

	private Ordine ord;
	private Product p1;
	private Product p2;
	private Product p3;
	private List<OPAssociation> opaList;	
	private OrderProductDAO orderProductDao;
	
	@Override
	protected void init() throws InitializationError {
		// TODO Auto-generated method stub
		ord=new Ordine();
		
		p1=new Product();
		p1.setTpProduct('f');
		p2=new Product();
		p2.setTpProduct('d');
		p3=new Product();
		p3.setTpProduct('d');

		
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.persist(p3);
		entityManager.persist(ord);

		OPAssociation op1= ord.addProduct(p1, 1);
		OPAssociation op2= ord.addProduct(p2, 2);
		OPAssociation op3= ord.addProduct(p3, 3);
		
		opaList=new ArrayList<OPAssociation>();
		
		opaList.add(op1);
		opaList.add(op2);
		opaList.add(op3);
			
		
		orderProductDao=new OrderProductDAO();
		
		try {
			FieldUtils.writeField(orderProductDao, "entityManager", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}

	
	}
	
	@Test
	public void insertProdAssociationTest(){
		
		List<OPAssociation> opaLista = new ArrayList<OPAssociation>();

		opaLista = entityManager
				.createQuery("from OPAssociation opa", OPAssociation.class)
				.getResultList();
		
		assertTrue(opaLista.isEmpty());
		//System.out.println(ord.getIdOrder());

		orderProductDao.insertProdAssociation(ord, opaList);
		
		opaLista = entityManager
				.createQuery("from OPAssociation opa", OPAssociation.class)
				.getResultList();

		assertEquals(opaLista,opaList);
	}
	
	@Test
	public void getFoodQntByIdOrderTest(){
		
		char type='c'; //Cuoco
		Map<Product, Integer> qntProductMap = new HashMap<Product, Integer>();
		qntProductMap.put(p1, 1);
		
		orderProductDao.insertProdAssociation(ord, opaList);
		
		Map<Product, Integer> qntProductMap2 = orderProductDao.getProdQntByIdOrder(ord.getIdOrder(), type);
		
		assertEquals(qntProductMap,qntProductMap2);
		
	}
	
	@Test
	public void getDrinkQntByIdOrderTest(){
		
		char type='b'; //Barista
		Map<Product, Integer> qntProductMap = new HashMap<Product, Integer>();
		qntProductMap.put(p2, 2);
		qntProductMap.put(p3, 3);

		
		orderProductDao.insertProdAssociation(ord, opaList);
		
		Map<Product, Integer> qntProductMap2 = orderProductDao.getProdQntByIdOrder(ord.getIdOrder(), type);
		
		assertEquals(qntProductMap,qntProductMap2);
		
	}
	
	@Test
	public void getAllProdQntByIdOrderTest(){
		
		char type='u'; //Utilizzatore (cliente)
		Map<Product, Integer> qntProductMap = new HashMap<Product, Integer>();
		qntProductMap.put(p1, 1);
		qntProductMap.put(p2, 2);
		qntProductMap.put(p3, 3);

		
		orderProductDao.insertProdAssociation(ord, opaList);
		
		Map<Product, Integer> qntProductMap2 = orderProductDao.getProdQntByIdOrder(ord.getIdOrder(), type);
		
		assertEquals(qntProductMap,qntProductMap2);
		
	}
	
	
	
	
	

}
