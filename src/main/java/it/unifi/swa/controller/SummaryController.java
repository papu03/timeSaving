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

	private User client;
	private Pub pub;
	private boolean isFood;
	private boolean isDrink;
	private Ordine ord;

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
		client = userSessionBean.getUser();
		pub = selectPubBean.getPub();

		isFood = false;
		isDrink = false;

		for (Map.Entry<Product, Integer> entry : basketNotNull.entrySet()) {

	         //System.out.println(entry.getKey().getTpProduct() );

			if (entry.getKey().getTpProduct() == 'f') {
				isFood = true;
			}
			if (entry.getKey().getTpProduct() == 'd') {
				isDrink = true;
			}
		}
         //System.out.println(isFood+" "+isDrink);
		 ord=orderDao.insertOrder(pub,isFood,isDrink);
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

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Pub getPub() {
		return pub;
	}

	public void setPub(Pub pub) {
		this.pub = pub;
	}

	public boolean isFood() {
		return isFood;
	}

	public void setFood(boolean isFood) {
		this.isFood = isFood;
	}

	public boolean isDrink() {
		return isDrink;
	}

	public void setDrink(boolean isDrink) {
		this.isDrink = isDrink;
	}

	public Ordine getOrd() {
		return ord;
	}

	public void setOrd(Ordine ord) {
		this.ord = ord;
	}

	public Map<Product, Integer> getBasketNotNull() {
		return basketNotNull;
	}

	public void setBasketNotNull(Map<Product, Integer> basketNotNull) {
		this.basketNotNull = basketNotNull;
	}

}
