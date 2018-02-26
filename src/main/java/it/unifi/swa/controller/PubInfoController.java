package it.unifi.swa.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.PubBean;
import it.unifi.swa.domain.Pub;
import java.io.Serializable;

@Named
@ViewScoped
public class PubInfoController implements Serializable {

    @Inject
    private PubBean pubBean;
    
    private Pub selectedPub;

    @PostConstruct
	public void init() {
    	System.out.println("Init Pub info controller");

    	selectedPub=pubBean.getSelectedPub();
    	System.out.println("Info del pub "+selectedPub.getNome());

    }
	
	public String toSelectPub(){
		
		pubBean.endConversation();
        return "selectPub?&faces-redirect=true";
	}


	public Pub getSelectedPub() {
		return selectedPub;
	}


	public void setSelectedPub(Pub selectedPub) {
		this.selectedPub = selectedPub;
	}


}
