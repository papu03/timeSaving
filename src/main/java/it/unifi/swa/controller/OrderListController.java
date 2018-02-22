package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.OrderBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.UserAssoDAO;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.User;
import it.unifi.swa.domain.UserAssociation;

@Named
@ViewScoped
public class OrderListController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private UserSessionBean userSessionBean;
	
	@Inject
	private OrderBean orderBean;
	
	@Inject
	private UserAssoDAO userAssoDao;
	
	private List<Ordine> orderList;

	
	@PostConstruct
	public void init() {
		System.out.println("Init Order List Controller");
		
		orderList= new ArrayList<Ordine>();
		User userSession=userSessionBean.getUser();
		
		for (UserAssociation userAssoc : userAssoDao.getUserAssocByUser(userSession)) {
			Ordine ord=userAssoc.getOrdine();
			orderList.add(ord);
		}
	}


	public String toDetailOrder(Ordine order){
		
		orderBean.initConversation();
		orderBean.setOrder(order);
		
		
		return "detailOrder?&faces-redirect=true";
	}
	
	
	public List<Ordine> getOrderList() {
		return orderList;
	}


	public void setOrderList(List<Ordine> orderList) {
		this.orderList = orderList;
	}



}
