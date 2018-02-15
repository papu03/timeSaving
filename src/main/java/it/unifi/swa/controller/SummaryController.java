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
import it.unifi.swa.domain.Product;

@Named
@ViewScoped
public class SummaryController implements Serializable {

	@Inject
	private BasketBean basketBean;

	private List<Product> productList;
	private Map<Product, Integer> basket;
    private Map<Product, Integer> basketNotNull; 
//	@PostConstruct
//	public void init() {
//		System.out.println("Init Summary Controller");
//
//		productList = new ArrayList<Product>();
//		basket = new HashMap<Product, Integer>();
//
//		basket = basketBean.getBasket();
//
//		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
//
//			System.out.println(entry.getKey().getProdName() + "," + entry.getValue());
//			productList.add(entry.getKey());
//		}
//
//	}
	
	@PostConstruct
	public void init() {
		System.out.println("Init Summary Controller");

		productList = new ArrayList<Product>();
		basket = new HashMap<Product, Integer>();
		basketNotNull=new HashMap<Product, Integer>();
		
		basket = basketBean.getBasket();
		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
			if (entry.getValue()>0) {
				basketNotNull.put(entry.getKey(), entry.getValue());
			}
		}

		for (Map.Entry<Product, Integer> entry : basketNotNull.entrySet()) {

			System.out.println(entry.getKey().getProdName() + "," + entry.getValue());
			productList.add(entry.getKey());
		}

	}
	
	public String toEditPage(){
		return "edit";
	}

	public Map<Product, Integer> getBasket() {
		return basket;
	}

	public void setBasket(Map<Product, Integer> basket) {
		this.basket = basket;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
