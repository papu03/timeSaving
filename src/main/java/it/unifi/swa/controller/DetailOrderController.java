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

import it.unifi.swa.bean.OrderBean;
import it.unifi.swa.dao.OrderProductDAO;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;

@Named
@ViewScoped
public class DetailOrderController implements Serializable {
	
//	@Inject
//	private UserSessionBean userSessionBean;
	
	
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderBean orderBean;
	
	@Inject
	private OrderProductDAO orderProductDao;
	
	private Map<Product, Integer> qntProductMap;

	
	@PostConstruct
	public void init() {
		System.out.println("Init DetailOrder Controller");
		
		qntProductMap=new HashMap<Product, Integer>();
		
		//User userSession=userSessionBean.getUser();
		Ordine ord=orderBean.getOrder();
		System.out.println("L'id dell'ordine è "+ord.getIdOrder());
		
		qntProductMap=orderProductDao.getProdQntByOrder(ord);
			
	}
	
	public String toOrderList(){
		
		orderBean.endConversation();
		
		 return "orderList?&faces-redirect=true";

	}


	public Map<Product, Integer> getQntProductMap() {
		return qntProductMap;
	}


	public void setQntProductMap(Map<Product, Integer> qntProductMap) {
		this.qntProductMap = qntProductMap;
	}

	



}
