package it.unifi.swa.controller;

import it.unifi.swa.bean.BasketBean;
import it.unifi.swa.bean.SelectPubBean;
import it.unifi.swa.dao.MenuDAO;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SelectPubBean selectPubBean;

	@Inject
	private BasketBean basketBean;

	@Inject
	private MenuDAO menuDAO;

	private List<Product> productList;
	private Map<Product, Integer> basket;

	@PostConstruct
	public void init() {
		System.out.println("Init Menu Controller");

		basket = new HashMap<Product, Integer>();

		try {

			List<Product> list = menuDAO.getListOfProduct(selectPubBean.getPub());
			if (!list.isEmpty()) {
				productList = list;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			// List<Product> list =
			// menuDAO.getListOfProduct(selectPubBean.getPub());
			//Map<String, Integer> listWithQnt = new HashMap<String, Integer>();
			Map<Product, Integer> listWithQnt = new HashMap<Product, Integer>();

			for (Product element : productList) {
				listWithQnt.put(element, 0);
			}

			if (!listWithQnt.isEmpty()) {
				basket = listWithQnt;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Product> getProductList() {

		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

//	public void addItemToBasket(Product p, int qnt) {
//		basket.put(p, qnt);
//		basketBean.setBasket(basket);
//	}

	

	public void addItem(Product p) {


		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {

			if (entry.getKey().equals(p)) {
				int increment = entry.getValue() + 1;
				entry.setValue(increment);
				System.out.println("Quantità " + entry.getValue());

			}
		}
		System.out.println("Item " + p.getProdName() + " aggiunto");

	}
	
	public String toSelectPub() {
		selectPubBean.setPub(null);
		return "selectPub?&faces-redirect=true";
 	}
	
//	public String toSummaryPage() {
//		Map<Product, Integer> basketNotNull = new HashMap<Product, Integer>();
//		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
//			if (entry.getValue()>0) {
//				basketNotNull.put(entry.getKey(), entry.getValue());
//			}
//		
//		}
//		basketBean.setBasket(basketNotNull);
//		return "summary";
// 	}
	
	public String toSummaryPage() {
		basketBean.setBasket(basket);
		return "summary?&faces-redirect=true";
 	}

	public Map<Product, Integer> getBasket() {
		return basket;
	}

	public void setBasket(Map<Product, Integer> basket) {
		this.basket = basket;
	}
}
