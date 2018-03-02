package it.unifi.swa.bean.startup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.dao.ProductDAO;
import it.unifi.swa.dao.PubDAO;
import static org.mockito.Mockito.mock;

import org.apache.commons.lang3.reflect.FieldUtils;


public class StartupBeanTest {
	
	private StartupBean startupBean;
    private MenuDAO menuDao;
    private OperatorDAO operatorDao;
    private ProductDAO productDao;
    private ClientDAO clientDao;
    private PubDAO pubDao;

    @Before
	public void init() throws InitializationError {
    	
    	startupBean=new StartupBean();
    	
    	menuDao=mock(MenuDAO.class);
    	operatorDao=mock(OperatorDAO.class);
    	productDao=mock(ProductDAO.class);
    	clientDao=mock(ClientDAO.class);
    	pubDao=mock(PubDAO.class);

    	 try {
             FieldUtils.writeField(startupBean, "menuDao", menuDao, true);
             FieldUtils.writeField(startupBean, "operatorDao", operatorDao, true);
             FieldUtils.writeField(startupBean, "productDao", productDao, true);
             FieldUtils.writeField(startupBean, "clientDao", clientDao, true);
             FieldUtils.writeField(startupBean, "pubDao", pubDao, true);
         } catch (IllegalAccessException e) {
             throw new InitializationError(e);
         }
    	
    }
    
    @Test
    public void  initTest() {
    	
        startupBean.init();
    }
}
