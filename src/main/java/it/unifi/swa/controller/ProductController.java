/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.ProductBean;
import it.unifi.swa.domain.Product;


@Named
@ViewScoped
public class ProductController implements Serializable {

    
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductBean productBean;
	
    private Product selectedProduct;
    
    
	@PostConstruct
	public void init() {
		System.out.println("Init Product Controller");
		selectedProduct=productBean.getProduct();
		//System.out.println("Immagine: "+selectedProduct.getImage());
	}

	public String toMenu(){

		productBean.endConversation();
		return "menu?&faces-redirect=true";
		
	}
	
    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    
    
  
}
