package it.unifi.swa.bean.startup;

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
    
    @PostConstruct
    public void init() {
        
        Client papu = new Client();
        papu.setUsername("papu");
        papu.setPassword("password");
        papu.setEmail("papu@gmail.com");
        
        Client ale= new Client();
		ale.setName("Alessandro");
		ale.setSurname("Baroni");
		ale.setUsername("b4r0");
		ale.setPassword("a");
		ale.setEmail("ale@gmail.com");
        
        clientDao.save(papu);
        clientDao.save(ale);
        
        
        Menu menu1 = new Menu();
        menu1.setDescr("Solo drink");
        menuDao.save(menu1);
        
        Menu menu2 = new Menu();
        menu2.setDescr("Drinks & foods");
        menuDao.save(menu2);
      
        //Menu menu = menuDao.findById(1);

        Pub pub = new Pub();
        pub.setIndirizzo("Via la polizia 69");
        pub.setNome("Beautiful Pub");
        pub.setMenu(menu1);
        pubDao.save(pub);
        
        Pub pub2 = new Pub();
        pub2.setIndirizzo("Via di qui 22");
        pub2.setNome("Pub Rivederci");
        pub2.setDescrizione("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur.");
        pub2.setMenu(menu1);
        pubDao.save(pub2);
        
        Pub pub3 = new Pub();
        pub3.setIndirizzo("Via verdi 59");
        pub3.setNome("Pub Lo Picasso");
        pub3.setMenu(menu2);
        pubDao.save(pub3);

        Pub pub4 = new Pub();
        pub4.setIndirizzo("Via kal 124");
        pub4.setNome("Pub E");
        pub4.setMenu(menu2);
        pubDao.save(pub4);
        
        
        Operator cuoco = new Operator();
        cuoco.setUsername("cuoco1");
        cuoco.setPassword("cuoco");
        cuoco.setLocal(pub);
        cuoco.setoType('c');
        cuoco.setEmail("cuoco1@gmail.com");
        
        Operator cuoco2 = new Operator();
        cuoco2.setUsername("cuoco2");
        cuoco2.setPassword("cuoco");
        cuoco2.setLocal(pub2);
        cuoco2.setoType('c');
        cuoco2.setEmail("cuoco2@gmail.com");
        
        Operator barista = new Operator();
        barista.setUsername("barista1");
        barista.setPassword("barista");
        barista.setLocal(pub);
        barista.setoType('b');
        barista.setEmail("barista1@gmail.com");

        Operator barista2 = new Operator();
        barista2.setUsername("barista2");
        barista2.setPassword("b");
        barista2.setLocal(pub2);
        barista2.setoType('b');
        barista2.setEmail("barista2@gmail.com");
        
        Operator barista3 = new Operator();
        barista3.setUsername("barista3");
        barista3.setPassword("barista");
        barista3.setLocal(pub3);
        barista3.setoType('b');
        barista3.setEmail("barista3@gmail.com");
        
        Operator barista4 = new Operator();
        barista4.setUsername("barista4");
        barista4.setPassword("barista");
        barista4.setLocal(pub4);
        barista4.setoType('b');
        barista4.setEmail("barista4@gmail.com");
        
        Product moretti = new Product();
        moretti.setProdName("Moretti");
        moretti.setPrice(3.0);
        moretti.setTpProduct('d');
        moretti.setMenu(menu2);
        moretti.setImage("http://www.unmondodisapori.it/wp-content/uploads/2017/10/moretti.jpg");
        moretti.setTmpExe(3);
        productDao.save(moretti);
        
        Product tennets = new Product();
        tennets.setProdName("tennets");
        tennets.setPrice(4.5);
        tennets.setTpProduct('d');
        tennets.setMenu(menu2);
        tennets.setImage("http://www.imolabevande.it/wp-content/uploads/2015/03/536birra-tennents-super-bottiglia.jpg");
        tennets.setTmpExe(3);
        productDao.save(tennets);

        Product corona = new Product();
        corona.setProdName("Corona");
        corona.setPrice(4.0);
        corona.setTpProduct('d');
        corona.setMenu(menu2);
        corona.setImage("https://www.drinksupermarket.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/o/corona-premium-mexican-lager-beer-12x-710ml-4-6-abv_temp.jpg");
        corona.setTmpExe(3);
        productDao.save(corona);

        Product cuba = new Product();
        cuba.setProdName("cuba libre");
        cuba.setPrice(6.0);
        cuba.setTpProduct('d');
        cuba.setMenu(menu2);
        cuba.setImage("https://images.eatsmarter.com/sites/default/files/styles/max_size/public/cuba-libre-with-mint-626296.jpg");
        cuba.setTmpExe(5);
        productDao.save(cuba);
        
        Product invisibile = new Product();
        invisibile.setProdName("invisibile");
        invisibile.setPrice(6.5);
        invisibile.setTpProduct('d');
        invisibile.setMenu(menu1);
        invisibile.setImage("https://www.preparacocktails.it/wp-content/uploads/2017/01/preparazione-ricetta-cocktail-invisibile.jpg");
        invisibile.setTmpExe(10);
        productDao.save(invisibile);
        
        Product spritz = new Product();
        spritz.setProdName("spritz");
        spritz.setPrice(6.5);
        spritz.setTpProduct('d');
        spritz.setMenu(menu1);
        spritz.setImage("https://dk24rv5jt17s8.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/a/p/aperol_baloon_2013_1.png");
        spritz.setTmpExe(7);
        productDao.save(spritz);

        Product panino = new Product();
        panino.setProdName("panino");
        panino.setPrice(6.0);
        panino.setTpProduct('f');
        panino.setMenu(menu1);
        panino.setImage("https://images.lacucinaitaliana.it/wp-content/uploads/2016/06/panino-tonno-e-pomodoro.jpg");
        panino.setTmpExe(15);
        productDao.save(panino);
        
        Product pizza = new Product();
        pizza.setProdName("pizza");
        pizza.setPrice(7.0);
        pizza.setTpProduct('f');
        pizza.setMenu(menu1);
        pizza.setImage("http://www.teleischia.com/wp-content/uploads/2017/08/pizza-napoletana.jpg");
        pizza.setTmpExe(13);
        productDao.save(pizza);
        
        Product schiacciata = new Product();
        schiacciata.setProdName("Schiacciata");
        schiacciata.setPrice(6.0);
        schiacciata.setTpProduct('f');
        schiacciata.setMenu(menu1);
        schiacciata.setImage("https://www.biancolievito.it/wp-content/uploads/2016/05/shutterstock_138483227.jpg");
        schiacciata.setTmpExe(9);
        productDao.save(schiacciata);
        
        operatorDao.save(barista);
        operatorDao.save(cuoco);
        operatorDao.save(barista2);
        operatorDao.save(cuoco2);
        operatorDao.save(barista3);
        operatorDao.save(barista4);



    }

}
