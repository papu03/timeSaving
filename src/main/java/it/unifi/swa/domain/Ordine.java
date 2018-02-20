package it.unifi.swa.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ordine {

	private int idOrdine;
	//private List<User> users = new ArrayList<User>();
	private Pub local;
	
	private List<OPAssociation> products;
	private List<UserAssociation> users;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	
//	@ManyToMany 
//	@JoinTable(name="JOIN_ORDINE_USER",
//				joinColumns={@JoinColumn(name="idOrdine")},
//				inverseJoinColumns={@JoinColumn(name="idUser")})
//	public List<User> getUsers() {
//		return users;
//	}
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//	
	@ManyToOne
	@JoinColumn(name="idLocale_FK")
	public Pub getLocal() {
		return local;
	}
	public void setLocal(Pub local) {
		this.local = local;
	}
	
	@OneToMany(mappedBy="order")
	public List<OPAssociation> getProducts() {
		return products;
	}
	public void setProducts(List<OPAssociation> products) {
		this.products = products;
	}
	
	public OPAssociation addProduct(Product product, int quantity) {
		OPAssociation association = new OPAssociation();
	    association.setProduct(product);
	    association.setOrder(this);
	    association.setIdProduct(product.getIdProduct());
	    association.setIdOrder(this.getIdOrdine());
	    association.setQuantity(quantity);
	    if(this.products == null)
	       this.products = new ArrayList<>();
	    
	    this.products.add(association);
	    product.getOrders().add(association);
	    
	    return association;
	  }
	
//	@OneToMany(targetEntity=UserAssociation.class, mappedBy="order",
//			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@OneToMany(mappedBy="order")
	public List<UserAssociation> getUsers() {
		return users;
	}
	public void setUsers(List<UserAssociation> users) {
		this.users = users;
	}
	
	public UserAssociation addUser(User user) {
	    UserAssociation association = new UserAssociation();
	    association.setUser(user);
	    association.setOrder(this);
	    association.setIdUser(user.getIdUser());
	    association.setIdOrdine(this.getIdOrdine());
	    if(this.users == null)
	       this.users = new ArrayList<>();
	    
	    this.users.add(association);
	    user.getOrders().add(association);
	    
	    return association;
	  }
	
	

	
	
	

}
