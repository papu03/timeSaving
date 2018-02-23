package it.unifi.swa.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MenuTest {

	private Menu menu;
	private Pub undicesimo;
	private Product negroni;
	private Product panino;

	@Before
	public void setUp() {

		menu = new Menu();

		undicesimo = new Pub();
		undicesimo.setNome("Undicesimo");
		undicesimo.setIndirizzo("Via prulli di sopra");
		undicesimo.setMenu(menu);

		negroni = new Product();
		negroni.setIdProduct(1);
		negroni.setProdName("negroni");
		negroni.setPrice(3.5);
		negroni.setTpProduct('d');
		negroni.setMenu(menu);

		panino = new Product();
		panino.setIdProduct(2);
		panino.setProdName("panino");
		panino.setPrice(5);
		panino.setTpProduct('f');
		panino.setMenu(menu);

		menu.getProducts().add(negroni);
		menu.getProducts().add(panino);
		menu.setLocale(undicesimo);
	}
	
    @Test
	public void getIdProdByNameTest(){
    	
    	int paninoId=menu.getIdProdByName(panino.getProdName());
    	assertEquals(panino.getIdProduct(),paninoId);
    	
    	int negroniId=menu.getIdProdByName(negroni.getProdName());
    	assertEquals(negroni.getIdProduct(),negroniId);
		
	}

}
