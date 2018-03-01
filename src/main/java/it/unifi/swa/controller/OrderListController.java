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
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.dao.UserAssoDAO;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.User;
import it.unifi.swa.domain.UserAssociation;
import javax.transaction.Transactional;

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

    @Inject
    private OrderDAO orderDao;
    
    private List<Ordine> orderList;

    private boolean isOperatore;
    private boolean isClient;

    @PostConstruct
    public void init() {
        System.out.println("Init Order List Controller");

        orderList = new ArrayList<Ordine>();
        User userSession = userSessionBean.getUser();
        isOperatore=false;
        isClient=false;

        for (UserAssociation userAssoc : userAssoDao.getUserAssocByUser(userSession)) {
            Ordine ord = userAssoc.getOrdine();
            orderList.add(ord);
        }
        
        if(userSessionBean.getType() != 0){
        	isOperatore=true;
        }else{
        	isClient=true;
        }
    }

    public String toDetailOrder(Ordine order) {

        orderBean.initConversation();
        orderBean.setOrder(order);

        return "detailOrder?&faces-redirect=true";
    }
    
    @Transactional
    public void changeOrderState(Ordine order){
        
        if(order.getStateOrder() == 'a'){
            order.setStateOrder('b'); 
        }else if(order.getStateOrder() == 'b'){
            order.setStateOrder('c');
        }
        
        try{
            orderDao.update(order);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public List<Ordine> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Ordine> orderList) {
        this.orderList = orderList;
    }

    public boolean isIsOperatore() {
        return isOperatore;
    }

    public void setIsOperatore(boolean isOperatore) {
        this.isOperatore = isOperatore;
    }

	public boolean isIsClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}

}
