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
import javax.transaction.Transactional;

import it.unifi.swa.bean.BasketBean;
import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.dao.OrderProductDAO;
import it.unifi.swa.dao.UserAssoDAO;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.User;

@Named
@ViewScoped
public class SummaryController implements Serializable {

	@Inject
	private BasketBean basketBean;

	@Inject
	private SelectPubBean selectPubBean;

	@Inject
	private UserSessionBean userSessionBean;

//	@Inject
//	private BaseStrategy baseStrategy;

	@Inject
	private OrderDAO orderDao;
	
	@Inject
	private UserAssoDAO userAssoDao;
	
	@Inject
	private OrderProductDAO orderProductDao;

	private List<Product> productList;
	private Map<Product, Integer> basket;
	private Map<Product, Integer> basketNotNull;


	@PostConstruct
	public void init() {
		System.out.println("Init Summary Controller");

		productList = new ArrayList<Product>();
		basket = new HashMap<Product, Integer>();
		basketNotNull = new HashMap<Product, Integer>();

		basket = basketBean.getBasket();
		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
			if (entry.getValue() > 0) {
				basketNotNull.put(entry.getKey(), entry.getValue());
			}
		}

		for (Map.Entry<Product, Integer> entry : basketNotNull.entrySet()) {

			System.out.println(entry.getKey().getProdName() + "," + entry.getValue());
			productList.add(entry.getKey());
		}

	}

	public String toEditPage() {
		return "edit?&faces-redirect=true";
	}

	@Transactional
	public String saveOrder() {
		User client = userSessionBean.getUser();
		Pub pub = selectPubBean.getPub();

		boolean isFood = false;
		boolean isDrink = false;

		for (Map.Entry<Product, Integer> entry : basketNotNull.entrySet()) {

			if (entry.getKey().getTpProduct() == 'f') {
				isFood = true;
			}
			if (entry.getKey().getTpProduct() == 'd') {
				isDrink = true;
			}
		}

		 Ordine ord=orderDao.insertOrder(pub);
		 userAssoDao.insertUserAssociation(ord,client,isFood,isDrink);
		 orderProductDao.insertProdAssociation(ord, basketNotNull);

		 return "orderList?&faces-redirect=true";
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
