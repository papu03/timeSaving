package it.unifi.swa.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ordine {

	private int idOrdine;
	private List<User> users = new ArrayList<User>();
	private Pub local;
	

	@Id
	@GeneratedValue
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	
	@ManyToMany 
	@JoinTable(name="JOIN_ORDINE_USER",
				joinColumns={@JoinColumn(name="idOrdine")},
				inverseJoinColumns={@JoinColumn(name="idUser")})
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@ManyToOne
	@JoinColumn(name="idLocale_FK")
	public Pub getLocal() {
		return local;
	}
	public void setLocal(Pub local) {
		this.local = local;
	}
	
	
	
	
	

	
	
	

}
