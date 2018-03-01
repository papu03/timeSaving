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
	private int utenteId;
	
	@Id
	private int ordineId;
	
	private char typeUser; //u utilizzatore c cuoco b barista
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="IDUSER", referencedColumnName="idUser")
	private User utente;

	@ManyToOne
	@PrimaryKeyJoinColumn(name="IDORDINE", referencedColumnName="idOrder")
	private Ordine ordine;

	public int getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(int utenteId) {
		this.utenteId = utenteId;
	}

	public int getOrdineId() {
		return ordineId;
	}

	public void setOrdineId(int ordineId) {
		this.ordineId = ordineId;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	public char getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(char typeUser) {
		this.typeUser = typeUser;
	}

}
