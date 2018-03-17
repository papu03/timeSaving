package it.unifi.swa.domain;


import java.io.Serializable;
import javax.persistence.Entity;
 
public class OrderProduct implements Serializable{

	private int idOrder;
	private int idProduct;
	
	public int hashCode() {
	    return (int)(idProduct + idOrder);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof OrderProduct) {
	    	OrderProduct otherId = (OrderProduct) object;
	      return (otherId.idProduct == this.idProduct) && (otherId.idOrder == this.idOrder);
	    }
	    return false;
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
