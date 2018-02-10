package it.unifi.swa.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User {

	private int idUser;
	private String username;
	private String password;
	private List<Ordine> orders = new ArrayList<Ordine>();

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToMany 
	@JoinTable(name="JOIN_ORDINE_USER",
				joinColumns={@JoinColumn(name="idUser")},
				inverseJoinColumns={@JoinColumn(name="idOrdine")})
	public List<Ordine> getOrders() {
		return orders;
	}
	public void setOrders(List<Ordine> orders) {
		this.orders = orders;
	}
	
	
}
