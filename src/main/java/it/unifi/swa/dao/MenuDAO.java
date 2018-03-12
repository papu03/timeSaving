package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Dependent
public class MenuDAO extends BaseDao<Menu> {

	protected MenuDAO() {
		super(Menu.class);
	}

	public List<Product> getListOfProduct(Pub pub) {

		List<Product> result = new ArrayList<Product>();

                if(pub.getMenu() != null){
                    result = entityManager.createQuery("from Product p where p.menu = :menu", Product.class)
                                            .setParameter("menu", pub.getMenu()).getResultList();
                }
                

		
		return result;
	}

	

}
