package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlInputText;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.BasketBean;
import it.unifi.swa.bean.ProductBean;
import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.dao.ProductDAO;
import it.unifi.swa.domain.Menu;
import it.unifi.swa.domain.Product;

@Named
@ViewScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SelectPubBean selectPubBean;

	@Inject
	private BasketBean basketBean;

	@Inject
	private ProductBean productBean;

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

	private boolean isOperatore;
	private boolean isClient;

	@PostConstruct
	public void init() {
		System.out.println("Init Menu Controller");
		System.out.println("name pub " + selectPubBean.getPub().getNome());

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
			listWithQnt.put(element, -1);
		}

		if (!listWithQnt.isEmpty()) {
			basket = listWithQnt;
		}

		if (userSessionBean.getType() != 0) {
			isOperatore = true;
		} else {
			isClient = true;
		}
	}

	public List<Product> getProductList() {

		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void addItem(Product p) {

		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {

			if (entry.getKey().equals(p)) {
				int increment = entry.getValue();
				entry.setValue(++increment);
				System.out.println("Quantità " + entry.getValue());

			}
		}
		System.out.println("Item " + p.getProdName() + " aggiunto");

	}

	public void removeItem(Product p) {

		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {

			if (entry.getKey().equals(p)) {
				int increment = entry.getValue();
				if (increment == 0) {
					return;
				}
				entry.setValue(--increment);
				System.out.println("Quantità " + entry.getValue());

			}
		}
		System.out.println("Item " + p.getProdName() + " rimosso");

	}

	public String toSelectPub() {
		return "selectPub?&faces-redirect=true";
	}

	public String toSummaryPage() {
		basketBean.setBasket(basket);
		return "summary?&faces-redirect=true";
	}

	public String showProductDetails() {

		productBean.initConversation();
		productBean.setProduct(selectedProduct);
		return "product?faces-redirect=true";
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
}