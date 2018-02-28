package it.unifi.swa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.model.InitializationError;

public abstract class DaoTest {

	private static EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	@BeforeClass
	public static void setUpClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("test-1");
	}

	@Before
	public void setUp() throws InitializationError {
		entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		init();
		entityManager.getTransaction().commit();
		//entityManager.clear(); 
		entityManager.getTransaction().begin();
	}

	@After
	public void tearDown() {
		if (entityManager.getTransaction().isActive())
			entityManager.getTransaction().rollback();

		entityManager.close();
	}

	@AfterClass
	public static void tearDownClass() {
		entityManagerFactory.close();
	}

	protected abstract void init() throws InitializationError;

	
}
