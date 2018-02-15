package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.Menu;
import java.util.List;
import java.util.Map;

@Dependent
public class MenuDAO extends BaseDao<Menu> {

	protected MenuDAO() {
		super(Menu.class);
	}

	public List<Product> getListOfProduct(Pub pub) {

		List<Product> result = null;

		try {
			result = entityManager.createQuery("from Product p where p.menu = :menu", Product.class)
					.setParameter("menu", pub.getMenu()).getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}

	public List<Product> getVirginProduct(Pub pub, Map<Product, Integer> basket) {

		List<Product> virginProducts = null;
		List<Product> allProducts = null;

		try {
			allProducts = entityManager.createQuery("from Product p where p.menu = :menu", Product.class)
					.setParameter("menu", pub.getMenu()).getResultList();
			virginProducts=allProducts;
			
			for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
				for (Product element : allProducts) {
					if (entry.getKey().equals(element)) {
						virginProducts.remove(element);
					}

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return virginProducts;

	}

}
