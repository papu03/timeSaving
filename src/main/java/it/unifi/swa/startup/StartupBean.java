package it.unifi.swa.startup;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.dao.ProductDAO;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Menu;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;

@Singleton
@Startup
public class StartupBean {

	@Inject
	MenuDAO menuDao;

	@Inject
	PubDAO pubDao;

	@Inject
	OperatorDAO operatorDao;

	@Inject
	ProductDAO productDao;

	@Inject
	PubDAO pub;
	
	@Inject
	ClientDAO clientDao;

	// @PersistenceContext
	// EntityManager entityManager;
	//
	// @Inject
	// ClientDAO clientDao;

	@PostConstruct
	private void init() {
		// when app is deployed

		// Client papu= new Client();
		// papu.setUsername("user");
		// papu.setPassword("password");
		// clientDao.save(papu);
		// List<Client> result =entityManager.createQuery("from Client where
		// username = :username and password = :password", Client.class)
		// .setParameter("username", "user")
		// .setParameter("password", "password")
		// .getResultList();
		// System.out.println(result.get(0).getUsername());

		Menu menu = new Menu();
		menu.setDescr("Menu favoloso!!!");
		
		Pub pub = new Pub();
		pub.setIdLocale(1);
		pub.setIndirizzo("Via la polizia 69");
		pub.setNome("Beautiful Pub");

		Pub pub2 = new Pub();
		pub2.setIdLocale(2);
		pub2.setIndirizzo("Via di qui 22");
		pub2.setNome("Pub Rivederci");

		Pub pub3 = new Pub();
		pub3.setIdLocale(3);
		pub3.setIndirizzo("Via verdi 59");
		pub3.setNome("Pub Lo Picasso");

		Pub pub4 = new Pub();
		pub4.setIdLocale(4);
		pub4.setIndirizzo("Via kal 124");
		pub4.setNome("Pub Rivederci");
		
		pub.setMenu(menu);

//		Pub undicesimo = new Pub();
//		undicesimo.setNome("Undicesimo");
//		undicesimo.setIndirizzo("Via prulli di sopra");
//		undicesimo.setMenu(menu);

		Operator cuoco = new Operator();
		cuoco.setIdUser(1);
		cuoco.setLocal(pub);

		Operator barista = new Operator();
		barista.setIdUser(2);
		barista.setLocal(pub);

		Product negroni = new Product();
		negroni.setProdName("negroni");
		negroni.setPrice(3.5);
		negroni.setTpProduct('d');
		negroni.setMenu(menu);

		Product panino = new Product();
		panino.setProdName("panino");
		panino.setPrice(5);
		panino.setTpProduct('f');
		panino.setMenu(menu);

		Client papu= new Client();
		papu.setUsername("papu");
		papu.setPassword("password");
		
		clientDao.save(papu);

		menuDao.save(menu);
		productDao.save(negroni);
		productDao.save(panino);
		pubDao.save(pub);
		pubDao.save(pub2);
		pubDao.save(pub3);
		pubDao.save(pub4);

	}

	// @PreDestroy
	// private void cleanUp() {
	// // when app is undeployed
	// }
}
