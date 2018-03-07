package it.unifi.swa.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.producer.HttpParam;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Pub;
import java.io.Serializable;

@Named
@ViewScoped
public class PubInfoController implements Serializable {

//    @Inject
//    private PubBean pubBean;
	
    
	@Inject
	@HttpParam("id")
	private String pubId;
	
    @Inject
    private PubDAO pubDao;
	
    private Pub selectedPub;

    @PostConstruct
	public void init() {
    	System.out.println("Init Pub info controller");

    	//selectedPub=pubBean.getSelectedPub();
    	selectedPub=pubDao.getPubById(pubId);

    	System.out.println("Info del pub "+selectedPub.getNome());

    }
    
    @PreDestroy
	public void end() {
		System.out.println("End Pub info Controller");
	}
	
	public String toSelectPub(){
		
        return "selectPub?&faces-redirect=true";
	}


	public Pub getSelectedPub() {
		return selectedPub;
	}


	public void setSelectedPub(Pub selectedPub) {
		this.selectedPub = selectedPub;
	}


}
