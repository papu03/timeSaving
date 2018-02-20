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
import it.unifi.swa.controller.strategy.BaseStrategy;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.User;
import it.unifi.swa.domain.UserAssociation;

@Named
@ViewScoped
public class SummaryController implements Serializable {

	@Inject
	private BasketBean basketBean;
	
	@Inject
	private SelectPubBean selectPubBean;
	
	@Inject
	private UserSessionBean userSessionBean;
	
	@Inject
	private BaseStrategy baseStrategy;
	
	@Inject
	private OrderDAO orderDao;

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
	
	@Transactional
	public String saveOrder(){
		User client=userSessionBean.getUser();
		Pub pub=selectPubBean.getPub();
		
		boolean isFood=false;
		boolean isDrink=false;
		
		for (Map.Entry<Product, Integer> entry : basketNotNull.entrySet()) {
			
			if(entry.getKey().getTpProduct()=='f'){
				isFood=true;
			}
			if(entry.getKey().getTpProduct()=='d'){
				isDrink=true;
			}
		}
		
		//orderDao.insertOrder(client,pub,isFood,isDrink);
		Ordine ord= new Ordine();
		ord.setLocal(pub);
		
//		ord.getUsers().add(client);
//		
		baseStrategy.saveOrder(ord);

		List<Operator> barman=  orderDao.getBarman();
		Operator op= new Operator();
		op=barman.get(0);

		//System.out.println("Locale ------------------"+op.getLocal().getNome());
		//Operator op2=baseStrategy.getOperator(op);
		//baseStrategy.saveOperator(op);
		UserAssociation a1=ord.addUser(client);
		UserAssociation a2=ord.addUser(op);

		//System.out.println("op order ------------------"+op.getOrders().get(0).getIdUser());

		baseStrategy.saveUserAsso(a1);
		baseStrategy.saveUserAsso(a2);

//		baseStrategy.updateOrder(ord);
		//orderDao.update(ord);
		return "detailOrder";
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
