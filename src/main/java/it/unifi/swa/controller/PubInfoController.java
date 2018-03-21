package it.unifi.swa.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.bean.producer.HttpParam;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Pub;

import java.beans.Transient;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

@Named
@ViewScoped
public class PubInfoController implements Serializable {

    @Inject
    @HttpParam("id")
    private String pubId;

    @Inject
    private PubDAO pubDao;

    @Inject
    private UserSessionBean userSessionBean;
    private boolean isOperatore;
    private boolean isClient;

    private Pub selectedPub;
    private String description;

    @PostConstruct
    public void init() {
        System.out.println("Init Pub info controller");

        //selectedPub=pubBean.getSelectedPub();
        if(pubId != null){
            selectedPub = pubDao.getPubById(pubId);
            description = selectedPub.getDescrizione();
            
        }

        if (userSessionBean.getType() != 'u') {
            isOperatore = true;
        } else {
            isClient = true;
        }

    }

    @PreDestroy
    public void end() {
        System.out.println("End Pub info Controller");
    }

    @Transactional
    public String updateDescription() {
        selectedPub.setDescrizione(description);
        pubDao.update(selectedPub);
        return "pubInfo?id=" + selectedPub.getIdLocale() + "&faces-redirect=true";

    }

    public String toSelectPub() {

        return "selectPub?&faces-redirect=true";
    }

    public Pub getSelectedPub() {
        return selectedPub;
    }

    public void setSelectedPub(Pub selectedPub) {
        this.selectedPub = selectedPub;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
