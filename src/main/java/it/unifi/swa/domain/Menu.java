package it.unifi.swa.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Menu {

	private int idMenu;
	private String descr;
	
	private List<Product> products;
	private Pub locale;
	
	
	@Id
	@GeneratedValue
	@Column(name="idMenu_PK")
	public int getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
	
	public int getIdProdByName(String prodName){
		
		int id= 0;
		for(Product p: products){
			if(prodName.equals(p.getProdName())){
				id=p.getIdProduct();
			}
		}
		return id;
	}
	
	@OneToMany(targetEntity=Product.class, mappedBy="menu",
			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@OneToOne(mappedBy="menu", cascade=CascadeType.ALL)
	public Pub getLocale() {
		return locale;
	}
	public void setLocale(Pub locale) {
		this.locale = locale;
	}
	
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	

}
