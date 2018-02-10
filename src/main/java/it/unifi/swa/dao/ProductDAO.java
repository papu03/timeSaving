package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.Client;
import it.unifi.swa.domain.Product;

@Dependent
public class ProductDAO extends BaseDao<Product>{

	protected ProductDAO() {
		
		super(Product.class);
	}

}
