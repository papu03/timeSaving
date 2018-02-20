package it.unifi.swa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_USERS")
@IdClass(UserAssociationId.class)
public class UserAssociation {
	

	@Id
	private int idUser;
	
	@Id
	private int idOrdine;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="IdUser", referencedColumnName="idUser")
	private User user;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="IdOrder", referencedColumnName="idOrdine")
	private Ordine order;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ordine getOrder() {
		return order;
	}

	public void setOrder(Ordine order) {
		this.order = order;
	}

}
