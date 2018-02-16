package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.BasketBean;
import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.domain.Product;

@Named
@ViewScoped
public class EditController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private BasketBean basketBean;

	@Inject
	private SelectPubBean pubBean;

	@Inject
	private MenuDAO menuDAO;

	private List<Product> productList;
	private Map<Product, Integer> basket;

	@PostConstruct
	public void init() {
		System.out.println("Init Edit Controller");

		productList = new ArrayList<Product>();
		basket = new HashMap<Product, Integer>();

		basket = basketBean.getBasket();
		try {

			for (Map.Entry<Product, Integer> entry : basket.entrySet()) {

				System.out.println(entry.getKey().getProdName() + "," + entry.getValue());
				productList.add(entry.getKey());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void addItem(Product p) {

		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {

			if (entry.getKey().equals(p)) {
				int increment = entry.getValue() + 1;
				entry.setValue(increment);
				System.out.println("Quantità " + entry.getValue());

			}
		}
		System.out.println("Item " + p.getProdName() + " aggiunto");

	}
	
	public String toSummaryPage(){
		return "summary";
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Map<Product, Integer> getBasket() {
		return basket;
	}

	public void setBasket(Map<Product, Integer> basket) {
		this.basket = basket;
	}
}
