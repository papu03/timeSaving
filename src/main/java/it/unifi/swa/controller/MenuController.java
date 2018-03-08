package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.dao.ProductDAO;
import it.unifi.swa.domain.Menu;
import it.unifi.swa.domain.OPAssociation;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import java.util.Vector;

@Named
@ConversationScoped
public class MenuController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Conversation conversation;

    @Inject
    private SelectPubBean selectPubBean;

    @Inject
    private UserSessionBean userSessionBean;

    @Inject
    private MenuDAO menuDao;

    @Inject
    private ProductDAO productDao;

    private List<Product> productList;
    private Map<Product, Integer> basket;
    private Product selectedProduct;
    private Menu myMenu;
    private Ordine order;
    private List<OPAssociation> opaList;

    private boolean isOperatore;
    private boolean isClient;

    @PostConstruct
    public void init() {

        System.out.println("Init Menu Controller");
        System.out.println("name pub " + selectPubBean.getPub().getNome());
        //this.initConversation();
        basket = new HashMap<Product, Integer>();
        List<Product> list = new ArrayList<Product>();
        try {
            list = menuDao.getListOfProduct(selectPubBean.getPub());
            productList = list;
            myMenu = productList.get(0).getMenu();
        } catch (NullPointerException ex) {
            productList = null;
            System.out.println("There is no Products in this pub menu");
        }

        Map<Product, Integer> listWithQnt = new HashMap<Product, Integer>();

        for (Product element : productList) {
            listWithQnt.put(element, 0);
        }

        if (!listWithQnt.isEmpty()) {
            basket = listWithQnt;
        }

        if (userSessionBean.getType() != 'u') {
            isOperatore = true;
        } else {
            isClient = true;
        }

        Ordine ord = new Ordine();
        ord.setLocal(selectPubBean.getPub());
        ord.setStateOrder('a');
        ord.setClient(userSessionBean.getUser());
        this.order = ord;
        // setOrder(ord);

        // vopa = new Vector<OPAssociation>();
        // setVopa(vopa);
        opaList = new ArrayList<OPAssociation>();

    }

    @PreDestroy
    public void end() {
        System.out.println("End Menu Controller");
    }

    public List<Product> getProductList() {

        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addToOrder(Product p) {

        boolean presente = false;

        for (OPAssociation op : opaList) {
            if (p == op.getProduct()) {
                int qty = op.getQuantity();
                op.setQuantity(++qty);
                basket.put(p, op.getQuantity());
                System.out.println("Modificato opa " + op.getProduct().getProdName() + " qnt " + op.getQuantity());
                presente = true;
            }
        }

        if (!presente) {
            OPAssociation opa = order.addProduct(p, 1);
            opaList.add(opa);
            basket.put(p, opa.getQuantity());
            System.out.println("Creato opa " + opa.getProduct().getProdName() + " qnt " + opa.getQuantity());
        }

        System.out.println("Aggiungo " + p.getProdName() + " all\'ordine");

    }

    public void removeFromOrder(Product p) {

        for (OPAssociation op : opaList) {
            if (p == op.getProduct()) {
                int qty = op.getQuantity();
                if (qty == 0) {
                    return;
                }
                op.setQuantity(--qty);
                basket.put(p, op.getQuantity());
                System.out.println("Modificato opa " + op.getProduct().getProdName() + " qnt " + op.getQuantity());
                System.out.println("Rimosso " + p.getProdName() + " all\'ordine");

            }
        }

    }

    public String toSelectPub() {

        this.endConversation();
        return "selectPub?&faces-redirect=true";
    }

    public String toSummaryPage() {
        return "summary?&faces-redirect=true";
    }

    public String showProductDetails() {

        return "product?idProd=" + selectedProduct.getIdProduct() + "&faces-redirect=true";

    }

    public String editProduct(HtmlInputText name, HtmlInputText price, HtmlInputText tmpExe, HtmlInputText image) {
        selectedProduct.setProdName(name.getValue().toString());
        selectedProduct.setImage(image.getValue().toString());

        selectedProduct.setPrice(Double.parseDouble(price.getValue().toString()));
        selectedProduct.setTmpExe(Integer.parseInt(tmpExe.getValue().toString()));

        productDao.updateProduct(selectedProduct);

        return "menu?faces-redirect=true";
    }

    public String removeProduct() {

        if (productList.size() > 1) {
            productDao.removeProduct(selectedProduct);
        } else {
            System.out.println("Impossibile rimuovere tutti gli oggetti");
        }
        return "menu?faces-redirect=true";
    }

    public String addProduct(HtmlInputText name, HtmlInputText price, HtmlInputText tmpExe, HtmlInputText image) {
        Product newProduct = new Product();

        try {
            // if (!price.getValue().toString().equals("") &&
            // !tmpExe.getValue().toString().equals("")) {
            newProduct.setMenu(myMenu);
            newProduct.setProdName(name.getValue().toString());
            newProduct.setImage(image.getValue().toString());

            newProduct.setPrice(Double.parseDouble(price.getValue().toString()));
            newProduct.setTmpExe(Integer.parseInt(tmpExe.getValue().toString()));

            productDao.addProduct(newProduct);
            // } else {
            // System.out.println("Inserisci prezzo e tempo");
            // }
        } catch (Exception e) {
            System.out.println("Inserisci prezzo e tempo");
        }

        return "menu?faces-redirect=true";

    }

    public String goToOrders() {

        return "orderList?&faces-redirect=true";

    }

    public String goToHomePage() {

        svuotaBasket();
        
        return "selectPub?&faces-redirect=true";

    }

    public String logOut() {

        userSessionBean = null;
        svuotaBasket();
        return "login?&faces-redirect=true";

    }
    
    private void svuotaBasket(){
        
        Map<Product, Integer> listWithQnt = new HashMap<Product, Integer>();
        for (Product element : productList) {
            listWithQnt.put(element, 0);
        }

        if (!listWithQnt.isEmpty()) {
            basket = listWithQnt;
        }
        
        opaList = new ArrayList<OPAssociation>();
        
    }

    public Map<Product, Integer> getBasket() {
        return basket;
    }

    public void setBasket(Map<Product, Integer> basket) {
        this.basket = basket;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public boolean isIsOperatore() {
        return isOperatore;
    }

    public void setOperatore(boolean isOperatore) {
        this.isOperatore = isOperatore;
    }

    public boolean isIsClient() {
        return isClient;
    }

    public void setClient(boolean isClient) {
        this.isClient = isClient;
    }

    public Ordine getOrder() {
        return order;
    }

    public void setOrder(Ordine order) {
        this.order = order;
    }

    public List<OPAssociation> getOpaList() {
        return opaList;
    }

    public void setOpaList(List<OPAssociation> opaList) {
        this.opaList = opaList;
    }

    public void initConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "back";
    }

}
