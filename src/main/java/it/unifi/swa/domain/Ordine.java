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
    
    private char tipoOrdine;

    private Pub local;
    private User client;
    private User cook;
    private User barman;
    private List<OPAssociation> products;
    // private List<UserAssociation> users;

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

    @ManyToOne
    @JoinColumn(name = "idClient_FK")
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @ManyToOne
    @JoinColumn(name = "idCook_FK")
    public User getCook() {
        return cook;
    }

    public void setCook(User cook) {
        this.cook = cook;
    }

    @ManyToOne
    @JoinColumn(name = "idBarman_FK")
    public User getBarman() {
        return barman;
    }

    public void setBarman(User barman) {
        this.barman = barman;
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
        // association.setIdOrder(this.getIdOrder());
        association.setQuantity(quantity);
        if (this.products == null) {
            this.products = new ArrayList<>();
        }

        // this.products.add(association);
        product.getOrders().add(association);

        return association;
    }

    // @OneToMany(mappedBy="ordine")
    // @OneToMany(targetEntity = UserAssociation.class, mappedBy = "ordine",
    // cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // public List<UserAssociation> getUsers() {
    // return users;
    // }
    //
    // public void setUsers(List<UserAssociation> users) {
    // this.users = users;
    // }
    //
    // public UserAssociation addUser(User user) {
    // UserAssociation association = new UserAssociation();
    // association.setUtente(user);
    // association.setOrdine(this);
    // association.setUtenteId(user.getIdUser());
    // association.setOrdineId(this.getIdOrder());
    // if (this.users == null) {
    // this.users = new ArrayList<>();
    // }
    //
    // this.users.add(association);
    // user.getOrders().add(association);
    //
    // return association;
    // }
    public String ottieniDescState() {

        String state = "";

        try {

            char orderState = getStateOrder();
            if (orderState == 'a') {
                state = "Ordine pagato in attesa";
            } else if (orderState == 'b') {
                state = "Ordine Bar in Esecuzione";
            } else if (orderState == 'c') {

                state = "Ordine Cucina in Esecuzione";
            } else if (orderState == 'd') {
                state = "Ordine Bar e Cucina in esecuzione";
            } else if (orderState == 'e') {
                state = "Ordine Cucina concluso, Ordine Bar in esecuzione";
            } else if (orderState == 'f') {
                state = "Ordine Bar concluso, Ordine Cucina in esecuzione";
            } else {
                state = "Ordine concluso";
            }

            // } else if (this.sizeOrder == 'a') { //food o drink
            //
            // if (orderState == 'c') {
            // state = "Eseguito";
            // }
            // } else if (this.sizeOrder == 'b') {//food e drink
            //
            // if (orderState == 'd'){
            // state = "Eseguito";
            // }
            // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return state;
    }

    public int ottieniTempoAttesa(Ordine order) {

        int eta = 0;

        try {

            for (OPAssociation opa : products) {
                if (opa.getIdOrder() == order.getIdOrder()) {
                    eta += (opa.getProduct().getTmpExe() * opa.getQuantity());
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return eta;

    }

    public char getStateOrder() {
        return stateOrder;
    }

    public void setStateOrder(char stateOrder) {
        this.stateOrder = stateOrder;
    }
    
    /**
     * @return the tipoOrdine
     */
    public char getTipoOrdine() {
        return tipoOrdine;
    }

    /**
     * @param tipoOrdine the tipoOrdine to set
     */
    public void setTipoOrdine(char tipoOrdine) {
        this.tipoOrdine = tipoOrdine;
    }

}
