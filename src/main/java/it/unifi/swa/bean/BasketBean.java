package it.unifi.swa.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import it.unifi.swa.domain.Product;

@RequestScoped
@Named
public class BasketBean implements Serializable {

	private Map<Product, Integer> basket = new HashMap<Product, Integer>();

	public Map<Product, Integer> getBasket() {
		return basket;
	}

	public void setBasket(Map<Product, Integer> map) {
		this.basket = map;
	}
	
	

	
}
