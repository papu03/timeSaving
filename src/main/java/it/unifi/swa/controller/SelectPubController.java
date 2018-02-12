/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.controller;

import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.dao.PubDAO;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alessandro
 */
@Named
@SessionScoped
public class SelectPubController implements Serializable {

    @Inject
    private PubDAO pubDao;
    
//    @Inject
//    private MenuDAO menuDao;
//    
//    @Inject
//    private MenuBean menuBean;
    @Inject
    private SelectPubBean selectPubBean;

    private Pub selectedPub;

    public String select() {
        
//        try{
//            List<Product> productList = menuDao.getListOfProduct(selectedPub);
//            if(!productList.isEmpty()){
//                menuBean.setProductList(productList);
//            }
//            
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
    	selectPubBean.setPub(selectedPub);
        
        return "menu?&faces-redirect=true";
    }

    public String showInfo() {

        return "pubInfo?&faces-redirect=true";

    }

    public Pub getSelectedPub() {
        return selectedPub;
    }

    public void setSelectedPub(Pub selectedPub) {
        this.selectedPub = selectedPub;
    }

}
