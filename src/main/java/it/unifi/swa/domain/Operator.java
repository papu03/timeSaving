package it.unifi.swa.domain;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Operator extends User{

	private char oType; //b barista c cuoco
	private Pub local;
	
	public char getoType() {
		return oType;
	}
	public void setoType(char oType) {
		this.oType = oType;
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
