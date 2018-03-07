package it.unifi.swa.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User{

	private int idUser;
	private String username;
	private String password;
	
	private List<Ordine> orders;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@OneToMany(targetEntity = Ordine.class, mappedBy = "client", cascade = CascadeType.ALL)
	public List<Ordine> getOrders() {
		if(this.orders == null)
		       this.orders = new ArrayList<>();
		return orders;
	}
	public void setOrders(List<Ordine> orders) {
		this.orders = orders;
	}
}
