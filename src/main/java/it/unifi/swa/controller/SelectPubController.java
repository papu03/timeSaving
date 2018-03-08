/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Pub;

@Named
@ViewScoped
//@Model
public class SelectPubController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PubDAO pubDao;

	@Inject
	private OperatorDAO operatorDao;

	@Inject
	private SelectPubBean selectPubBean;

	@Inject
	private UserSessionBean userSessionBean;

	@Inject
	private MenuController menuCtrl;

	private Pub selectedPub;
	private List<Pub> pubList;

	private Pub operatorPub;

	private boolean isOperatore;
	private boolean isClient;

	@PostConstruct
	public void init() {
		System.out.println("Select Pub Controller Init");

		isOperatore = false;
		isClient = false;

		if (userSessionBean.getType() != 'u') {
			isOperatore = true;
			operatorPub = operatorDao.findByLoginInfo(userSessionBean.getUser()).getLocal();
		} else {
			isClient = true;
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("End SelectPub Controller");
	}

	public String select() {

		selectPubBean.setPub(selectedPub);
		menuCtrl.initConversation();
		return "menu?&faces-redirect=true";
	}

	public String goToOrders() {

		return "orderList?&faces-redirect=true";

	}

	public String showInfo() {

		return "pubInfo?id=" + selectedPub.getIdLocale() + "&faces-redirect=true";

	}

	public Pub getSelectedPub() {
		return selectedPub;
	}

	public void setSelectedPub(Pub selectedPub) {
		this.selectedPub = selectedPub;
	}

	public List<Pub> getPubList() {

		List<Pub> lista = pubDao.getListOfPub();
		if (!lista.isEmpty()) {
			this.pubList = lista;
		}

		return pubList;
	}

	public void setPubList(List<Pub> pubList) {
		this.pubList = pubList;
	}

	public boolean isIsOperatore() {
		return isOperatore;
	}

	public void setOperatore(boolean isOperatore) {
		this.isOperatore = isOperatore;
	}

	public boolean isIsClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}

	public Pub getOperatorPub() {

		return operatorPub;
	}

	public void setOperatorPub(Pub operatorPub) {
		this.operatorPub = operatorPub;
	}

}
