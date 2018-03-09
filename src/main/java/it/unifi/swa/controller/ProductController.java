/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.controller;

import it.unifi.swa.bean.UserSessionBean;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.producer.HttpParam;
import it.unifi.swa.dao.ProductDAO;
import it.unifi.swa.domain.Product;

@Named
@ViewScoped
public class ProductController implements Serializable {


    private static final long serialVersionUID = 1L;

    @Inject
    @HttpParam("idProd")
    private String productId;

    @Inject
    private ProductDAO productDao;
    @Inject
    private UserSessionBean userSessionBean;
    @Inject
    private MenuController menuCtrl;

    private boolean isOperatore;
    private boolean isClient;

    private Product selectedProduct;

    @PostConstruct
    public void init() {
        System.out.println("Init Product Controller");

        if (productId != null) {
            selectedProduct = productDao.getProductById(productId);
        }

        if (userSessionBean.getType() != 'u') {
            setIsOperatore(true);
        } else {
            setIsClient(true);
        }

    }

    @PreDestroy
    public void end() {
        //productId=null;
        System.out.println("End Product Controller");
    }
    
    public String goToOrders() {

    	menuCtrl.endConversation();

        return "orderList?&faces-redirect=true";

    }

    public String goToHomePage() {

    	menuCtrl.endConversation();

        return "selectPub?&faces-redirect=true";

    }

    public String logOut() {

        userSessionBean = null;
        menuCtrl.endConversation();

        return "login?&faces-redirect=true";

    }

    public String toMenu() {

        //menuCtrl.initConversation();
        return "menu?&faces-redirect=true";

    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
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

    public void setIsClient(boolean isClient) {
        this.isClient = isClient;
    }

    
}
