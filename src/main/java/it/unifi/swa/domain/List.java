package it.unifi.swa.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class List {

	@Id
	OrderProduct op;
	private int quantita;
	
	public OrderProduct getOp() {
		return op;
	}
	public void setOp(OrderProduct op) {
		this.op = op;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
}
