/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.controller;

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

    private Product selectedProduct;

    @PostConstruct
    public void init() {
        System.out.println("Init Product Controller");


        if(productId != null){
            selectedProduct = productDao.getProductById(productId);
        }

    }

    @PreDestroy
    public void end() {
        //productId=null;
        System.out.println("End Product Controller");
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

}
