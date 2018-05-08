package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;
import javax.persistence.Query;
import javax.transaction.Transactional;

import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;

@Dependent
public class ProductDAO extends BaseDao<Product> {

	protected ProductDAO() {

		super(Product.class);
	}


	
	public Product getProductById(int idProduct){
  	  
  	
  	Product result = entityManager
	                .createQuery("FROM Product p WHERE p.idProduct = :productId", Product.class)
	                .setParameter("productId", idProduct)
	                .getSingleResult();
		  
	return result;
	}

}
