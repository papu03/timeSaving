package it.unifi.swa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ordine {

	private int idOrder;
	private char stateOrder; // "a" arrivato "b" in esecuzione "c" 1 tipo
								// concluso "d" 2 tipi conclusi
	private char sizeOrder; // 'a' o food o drink 'b' sia food sia drink

	private Pub local;
	private List<OPAssociation> products;
	private List<UserAssociation> users;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	@ManyToOne
	@JoinColumn(name = "idLocale_FK")
	public Pub getLocal() {
		return local;
	}

	public void setLocal(Pub local) {
		this.local = local;
	}

	@OneToMany(mappedBy = "order")
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
		association.setIdOrder(this.getIdOrder());
		association.setQuantity(quantity);
		if (this.products == null) {
			this.products = new ArrayList<>();
		}

		this.products.add(association);
		product.getOrders().add(association);

		return association;
	}

	// @OneToMany(mappedBy="ordine")
	@OneToMany(targetEntity = UserAssociation.class, mappedBy = "ordine", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<UserAssociation> getUsers() {
		return users;
	}

	public void setUsers(List<UserAssociation> users) {
		this.users = users;
	}

	public UserAssociation addUser(User user) {
		UserAssociation association = new UserAssociation();
		association.setUtente(user);
		association.setOrdine(this);
		association.setUtenteId(user.getIdUser());
		association.setOrdineId(this.getIdOrder());
		if (this.users == null) {
			this.users = new ArrayList<>();
		}

		this.users.add(association);
		user.getOrders().add(association);

		return association;
	}

	public String ottieniDescState() {

		String state = "";

		try {

			char orderState = getStateOrder();
			if (orderState == 'a') {
				state = "Da Prendere In Carico";
			} else if (orderState == 'b') {
				state = "In Esecuzione";
			} else if (this.sizeOrder == 'a') { //food o drink

				if (orderState == 'c') {
					state = "Eseguito";
				}
			} else if (this.sizeOrder == 'b') {//food e drink
				
				if (orderState == 'd'){
					state = "Eseguito";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return state;
	}

	public char getStateOrder() {
		return stateOrder;
	}

	public void setStateOrder(char stateOrder) {
		this.stateOrder = stateOrder;
	}

	public char getSizeOrder() {
		return sizeOrder;
	}

	public void setSizeOrder(char sizeOrder) {
		this.sizeOrder = sizeOrder;
	}

}
