package it.unifi.swa.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	private int idProduct;
	private Menu menu;
	
	
	private String prodName;
	private double price;
	private char tpProduct;
	private int tmpExe;
	private int quantity;
	private String image;
	
	@Id
	@GeneratedValue
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public char getTpProduct() {
		return tpProduct;
	}
	public void setTpProduct(char tpProduct) {
		this.tpProduct = tpProduct;
	}
	public int getTmpExe() {
		return tmpExe;
	}
	public void setTmpExe(int tmpExe) {
		this.tmpExe = tmpExe;
	}
	

	@ManyToOne
	@JoinColumn(name="idMenu_FK")
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
