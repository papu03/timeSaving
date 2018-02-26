/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.controller;

import it.unifi.swa.bean.PubBean;
import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SelectPubController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PubDAO pubDao;

	@Inject
	private SelectPubBean selectPubBean;

	@Inject
	private PubBean pubBean;

	private Pub selectedPub;
	private List<Pub> pubList;

	public String select() {

		selectPubBean.setPub(selectedPub);

		return "menu?&faces-redirect=true";
	}

	public String showInfo() {

		pubBean.initConversation();
		pubBean.setSelectedPub(selectedPub);
		return "pubInfo?&faces-redirect=true";

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

}
