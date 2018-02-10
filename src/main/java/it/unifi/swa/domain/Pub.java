package it.unifi.swa.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Pub {


	private int idLocale;
	private long partitaIva;
	private String nome;
	private String indirizzo;
        private String descrizione;
	
	private Menu menu;
	private List<Ordine> orders;
	private List<Operator> operators = new ArrayList<Operator>();

	
	@Id
	@GeneratedValue
	public int getIdLocale() {
		return idLocale;
	}
	public void setIdLocale(int idLocale) {
		this.idLocale = idLocale;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public long getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(long partitaIva) {
		this.partitaIva = partitaIva;
	}
        public String getDescrizione() {
            return descrizione;
        }

        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="menu_FK")	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
//	@OneToMany(targetEntity=Ordine.class, mappedBy="local",
//			cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@OneToMany(targetEntity=Ordine.class, mappedBy="local",
	cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Ordine> getOrders() {
		return orders;
	}
	public void setOrders(List<Ordine> orders) {
		this.orders = orders;
	}
	
	@OneToMany(targetEntity=Operator.class, mappedBy="local",
			cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Operator> getOperators() {
		return operators;
	}
	public void setOperators(List<Operator> operators) {
		this.operators = operators;
	}

	
	
	
}
