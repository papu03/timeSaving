package it.unifi.swa.dao;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Operator;

public class OperatorDaoTest extends DaoTest {

	private OperatorDAO operatorDao;
	private Operator operator;
	
	
	
	@Override
	protected void init() throws InitializationError {
		
		operator= new Operator();
		operator.setUsername("Cuoco");
		operator.setoType('c');
		operator.setPassword("password");

		operatorDao=new OperatorDAO();
		try {
			FieldUtils.writeField(operatorDao, "entityManager", entityManager, true);
		} catch (IllegalAccessException e) {
			throw new InitializationError(e);
		}
		
	}
	
	@Test
	public void findByLoginInfoTest(){
		entityManager.persist(operator);
		Operator operator2= operatorDao.findByLoginInfo(operator);
        assertEquals(operator,operator2);
	}

}
