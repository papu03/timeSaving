package it.unifi.swa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_PRODUCTS")
@IdClass(OrderProduct.class)
public class OPAssociation {
	
	@Id
	private int idProduct;
	
	@Id
	private int idOrder;
	
	private int quantity;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="IdProduct", referencedColumnName="idProduct")
	private Product product;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="IdOrder", referencedColumnName="idOrder")
	private Ordine order;

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Ordine getOrder() {
		return order;
	}

	public void setOrder(Ordine order) {
		this.order = order;
	}

}
