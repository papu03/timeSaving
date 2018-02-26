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
        
        Client papu = new Client();
        papu.setUsername("papu");
        papu.setPassword("password");
        
        Client ale= new Client();
		ale.setName("Alessandro");
		ale.setSurname("Baroni");
		ale.setUsername("Yoloswag96");
		ale.setPassword("numerello");
        
        clientDao.save(papu);
        
        
        Menu menu = new Menu();
        menu.setDescr("Menu favoloso!!!");
        menuDao.save(menu);
      
        //Menu menu = menuDao.findById(1);

        Pub pub = new Pub();
        pub.setIdLocale(1);
        pub.setIndirizzo("Via la polizia 69");
        pub.setNome("Beautiful Pub");
        pub.setMenu(menu);
        pubDao.save(pub);
        
        Pub pub2 = new Pub();
        pub2.setIdLocale(2);
        pub2.setIndirizzo("Via di qui 22");
        pub2.setNome("Pub Rivederci");
        pub2.setDescrizione("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur.");
        pub2.setMenu(menu);
        pubDao.save(pub2);
        
        Pub pub3 = new Pub();
        pub3.setIdLocale(3);
        pub3.setIndirizzo("Via verdi 59");
        pub3.setNome("Pub Lo Picasso");
        pub3.setMenu(menu);
        pubDao.save(pub);

        Pub pub4 = new Pub();
        pub4.setIdLocale(4);
        pub4.setIndirizzo("Via kal 124");
        pub4.setNome("Pub E");
        pub4.setMenu(menu);
        pubDao.save(pub4);

//		Pub undicesimo = new Pub();
//		undicesimo.setNome("Undicesimo");
//		undicesimo.setIndirizzo("Via prulli di sopra");
//		undicesimo.setMenu(menu);
        Operator cuoco = new Operator();
        cuoco.setUsername("sanji");
        cuoco.setPassword("cuoco");
        cuoco.setLocal(pub);
        cuoco.setoType('c');

        Operator barista = new Operator();
        //barista.setIdUser(2);
        barista.setUsername("splendido");
        barista.setPassword("mitico");
        barista.setLocal(pub);
        barista.setoType('b');

        
        
        Product moretti = new Product();
        moretti.setProdName("Moretti");
        moretti.setPrice(3.0);
        moretti.setTpProduct('d');
        moretti.setMenu(menu);
        moretti.setImage("http://www.unmondodisapori.it/wp-content/uploads/2017/10/moretti.jpg");
        productDao.save(moretti);
        
        Product tennets = new Product();
        tennets.setProdName("tennets");
        tennets.setPrice(4.5);
        tennets.setTpProduct('d');
        tennets.setMenu(menu);
        tennets.setImage("http://www.imolabevande.it/wp-content/uploads/2015/03/536birra-tennents-super-bottiglia.jpg");
        productDao.save(tennets);

        Product corona = new Product();
        corona.setProdName("Corona");
        corona.setPrice(4.0);
        corona.setTpProduct('d');
        corona.setMenu(menu);
        corona.setImage("https://www.drinksupermarket.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/o/corona-premium-mexican-lager-beer-12x-710ml-4-6-abv_temp.jpg");
        productDao.save(corona);

        Product cuba = new Product();
        cuba.setProdName("cuba libre");
        cuba.setPrice(6.0);
        cuba.setTpProduct('d');
        cuba.setMenu(menu);
        cuba.setImage("https://images.eatsmarter.com/sites/default/files/styles/max_size/public/cuba-libre-with-mint-626296.jpg");
        productDao.save(cuba);
        
        Product invisibile = new Product();
        invisibile.setProdName("invisibile");
        invisibile.setPrice(6.5);
        invisibile.setTpProduct('d');
        invisibile.setMenu(menu);
        invisibile.setImage("https://www.preparacocktails.it/wp-content/uploads/2017/01/preparazione-ricetta-cocktail-invisibile.jpg");
        productDao.save(invisibile);
        
        Product spritz = new Product();
        spritz.setProdName("spritz");
        spritz.setPrice(6.5);
        spritz.setTpProduct('d');
        spritz.setMenu(menu);
        spritz.setImage("https://dk24rv5jt17s8.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/a/p/aperol_baloon_2013_1.png");
        productDao.save(spritz);


//        Product panino = new Product();
//        panino.setProdName("panino");
//        panino.setPrice(5.0);
//        panino.setTpProduct('f');
//        invisibile.setMenu(menu);

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


        

        
        operatorDao.save(barista);
        operatorDao.save(cuoco);

    }

}
