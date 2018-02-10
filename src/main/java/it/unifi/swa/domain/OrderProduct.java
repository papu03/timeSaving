package it.unifi.swa.domain;


import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrderProduct implements Serializable{

	private int idOrder;
	private int idProduct;
	
	public OrderProduct(){
		
	}
	
	public OrderProduct(int idOrder,int idProduct){
		this.idOrder=idOrder;
		this.idProduct=idProduct;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	
	
	
	
}
