package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.Menu;
import java.util.List;

@Dependent
public class MenuDAO extends BaseDao<Menu> {

    protected MenuDAO() {
        super(Menu.class);
    }

    public List<Product> getListOfProduct(Pub pub) {

        List<Product> result = null;
        
        try {
            result = entityManager.createQuery("from Product p where p.menu = :menu", Product.class)
                    .setParameter("menu", pub.getMenu())
                    .getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;

    }

}
