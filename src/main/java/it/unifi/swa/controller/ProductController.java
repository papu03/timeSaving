/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import it.unifi.swa.domain.Product;

/**
 *
 * @author Alessandro
 */
@Named
@SessionScoped
public class ProductController implements Serializable {

    
    private Product selectedProduct;
    private String qnt;
    
    public ProductController(){
    	this.qnt="1";
    }
    
    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    public int addItem(){
    	return 1;
    	//selectedProduct.setQuantity(selectedProduct.getQuantity()+1); 
    	//System.out.println("La quantità di "+selectedProduct.getProdName()+ " è "+ selectedProduct.getQuantity());
    	//return "menu";
    	
    }

	public String getQnt() {
		return qnt;
	}

	public void setQnt(String qnt) {
		this.qnt = qnt;
	}
    
  
}
