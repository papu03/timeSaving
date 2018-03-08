package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.User;
import javax.transaction.Transactional;

@Named
@ViewScoped
public class OrderListController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserSessionBean userSessionBean;

	@Inject
	private OrderDAO orderDao;

	private List<Ordine> orderList;

	private boolean isOperatore;
	private boolean isClient;
	private int changeStateCount;

	@PostConstruct
	public void init() {
		System.out.println("Init Order List Controller");

		orderList = new ArrayList<Ordine>();
		User userSession = userSessionBean.getUser();
		isOperatore = false;
		isClient = false;
		changeStateCount = 0;


		if (userSessionBean.getType() == 'c') {
			for (Ordine ord : orderDao.getOrderByCook(userSession)) {
				orderList.add(ord);
			}
			isOperatore = true;
		} else if (userSessionBean.getType() == 'b') {

			for (Ordine ord : orderDao.getOrderByBarman(userSession)) {
				orderList.add(ord);
			}
			isOperatore = true;

		} else {

			for (Ordine ord : orderDao.getOrderByClient(userSession)) {
				orderList.add(ord);
			}

			isClient = true;
		}
	}

	public String toDetailOrder(Ordine order) {

		return "detailOrder?id=" + order.getIdOrder()+ "&faces-redirect=true";

	}

	@Transactional
	public void changeOrderState(Ordine order) {

		if (changeStateCount != 2) {

			if (!order.getBarman().equals(null) && !order.getCook().equals(null)) {
				
				if (order.getStateOrder() == 'a') {
					order.setStateOrder('b');
				} else if (order.getStateOrder() == 'b') {

					if (userSessionBean.getType() == 'c') { // cuoco
						order.setStateOrder('c');
					} else if (userSessionBean.getType() == 'b') { // barista
						order.setStateOrder('d');
					}

				} else {

					order.setStateOrder('e');

				}

			} else {

				if (order.getStateOrder() == 'a') {
					order.setStateOrder('b');
				} else {
					order.setStateOrder('e');
				}
			}

			try {
				orderDao.update(order);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			changeStateCount++;
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

	public int getChangeStateCount() {
		return changeStateCount;
	}

	public void setChangeStateCount(int changeStateCount) {
		this.changeStateCount = changeStateCount;
	}

}
