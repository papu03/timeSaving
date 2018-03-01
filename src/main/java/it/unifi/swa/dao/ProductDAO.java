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

	@Transactional
	public void updateProduct(Product product) {

		this.update(product);

	}
	
	@Transactional
	public void removeProduct(Product product) {

		this.delete(product);

	}
	
	@Transactional
	public void addProduct(Product product) {

		this.save(product);

	}

}
