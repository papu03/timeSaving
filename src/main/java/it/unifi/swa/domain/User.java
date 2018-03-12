package it.unifi.swa.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User{

	private int idUser;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String address;
	private long phoneNumber;
	
	private List<Ordine> orders;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@NotNull
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotNull
	@Column(unique = true)
    //@Size(min = 3, max=30)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull
	@Column(unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
