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

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alessandro
 */

@Named
@SessionScoped
public class MenuController implements Serializable {

	@Inject
	private SelectPubBean selectPubBean;

	@Inject
	private BasketBean basketBean;

	@Inject
	private MenuDAO menuDAO;

	private List<Product> productList;
    private Map<Product, Integer> basket = new HashMap<Product, Integer>();

	public MenuController(){
		basket.put(null, 0);
	}
	public List<Product> getProductList() {

		try {

			List<Product> lista = menuDAO.getListOfProduct(selectPubBean.getPub());
			if (!lista.isEmpty()) {
				productList = lista;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void addItemToBasket(Product p, int qnt){
		basket.put(p, qnt);
	    basketBean.setBasket(basket);
	 }
	
	public void toSelectPub() {
		selectPubBean.setPub(null);
	}

}
