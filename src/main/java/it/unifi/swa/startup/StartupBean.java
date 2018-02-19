package it.unifi.swa.startup;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

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
    OperatorDAO operatorDao;

    @Inject
    ProductDAO productDao;

    @Inject
    ClientDAO clientDao;
    
    @Inject
    PubDAO pubDao;
    
   
    
    private List<Pub> pubList;

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
        pub2.setDescrizione("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur.");
        pub2.setMenu(menu);
        
        Pub pub3 = new Pub();
        pub3.setIdLocale(3);
        pub3.setIndirizzo("Via verdi 59");
        pub3.setNome("Pub Lo Picasso");

        Pub pub4 = new Pub();
        pub4.setIdLocale(4);
        pub4.setIndirizzo("Via kal 124");
        pub4.setNome("Pub Rivederci");


//		Pub undicesimo = new Pub();
//		undicesimo.setNome("Undicesimo");
//		undicesimo.setIndirizzo("Via prulli di sopra");
//		undicesimo.setMenu(menu);
        Operator cuoco = new Operator();
        //cuoco.setIdUser(1);
        cuoco.setLocal(pub);
        cuoco.setoType(2);

        Operator barista = new Operator();
        //barista.setIdUser(2);
        barista.setLocal(pub);
        barista.setoType(1);

        
        
        Product moretti = new Product();
        moretti.setProdName("Moretti");
        moretti.setPrice(3.0);
        moretti.setTpProduct('d');
        moretti.setMenu(menu);
        moretti.setImage("http://www.unmondodisapori.it/wp-content/uploads/2017/10/moretti.jpg");
        
        Product tennets = new Product();
        tennets.setProdName("tennets");
        tennets.setPrice(4.5);
        tennets.setTpProduct('d');
        tennets.setMenu(menu);
        tennets.setImage("http://www.imolabevande.it/wp-content/uploads/2015/03/536birra-tennents-super-bottiglia.jpg");

        Product corona = new Product();
        corona.setProdName("Corona");
        corona.setPrice(4.0);
        corona.setTpProduct('d');
        corona.setMenu(menu);
        corona.setImage("https://www.drinksupermarket.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/o/corona-premium-mexican-lager-beer-12x-710ml-4-6-abv_temp.jpg");

        Product cuba = new Product();
        cuba.setProdName("cuba libre");
        cuba.setPrice(6.0);
        cuba.setTpProduct('d');
        cuba.setMenu(menu);
        cuba.setImage("https://images.eatsmarter.com/sites/default/files/styles/max_size/public/cuba-libre-with-mint-626296.jpg");
        
        Product invisibile = new Product();
        invisibile.setProdName("invisibile");
        invisibile.setPrice(6.5);
        invisibile.setTpProduct('d');
        invisibile.setMenu(menu);
        invisibile.setImage("https://www.preparacocktails.it/wp-content/uploads/2017/01/preparazione-ricetta-cocktail-invisibile.jpg");
        
        Product spritz = new Product();
        spritz.setProdName("spritz");
        spritz.setPrice(6.5);
        spritz.setTpProduct('d');
        spritz.setMenu(menu);
        spritz.setImage("https://dk24rv5jt17s8.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/a/p/aperol_baloon_2013_1.png");


//        Product panino = new Product();
//        panino.setProdName("panino");
//        panino.setPrice(5.0);
//        panino.setTpProduct('f');
//        invisibile.setMenu(menu);

        Client papu = new Client();
        papu.setUsername("papu");
        papu.setPassword("password");
        
        Client ale= new Client();
		ale.setName("Alessandro");
		ale.setSurname("Baroni");
		ale.setUsername("Yoloswag96");
		ale.setPassword("numerello");
        
	//order phase
		
//		Order o1=new Order();
//		Order o2=new Order();
//		
//		o1.getUsers().add(cuoco);
//		o1.getUsers().add(barista);
//		o1.getUsers().add(papu);
//		o1.setLocal(pub);
//		o2.getUsers().add(cuoco);
//		o2.getUsers().add(barista);
//		o2.getUsers().add(ale);
//		o2.setLocal(pub);
//        
//        orderDao.save(o1);
//        orderDao.save(o2);

        clientDao.save(papu);

        menuDao.save(menu);
        productDao.save(moretti);
        productDao.save(tennets);
        productDao.save(corona);
        productDao.save(cuba);
        productDao.save(invisibile);
        productDao.save(spritz);


       // productDao.save(panino);
        pubDao.save(pub);
        pubDao.save(pub2);
        pubDao.save(pub3);
        pubDao.save(pub4);
        
        operatorDao.save(barista);
        operatorDao.save(cuoco);

    }

}
