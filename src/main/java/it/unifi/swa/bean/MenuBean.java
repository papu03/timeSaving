package it.unifi.swa.bean;

import it.unifi.swa.domain.Product;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class MenuBean implements Serializable {
    
    private List<Product> productList;
    
    
    
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
}
