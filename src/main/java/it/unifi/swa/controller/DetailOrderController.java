package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.bean.producer.HttpParam;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.dao.OrderProductDAO;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;

@Named
@RequestScoped
public class DetailOrderController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserSessionBean userSessionBean;

    @Inject
    private OrderProductDAO orderProductDao;

    @Inject
    @HttpParam("id")
    private String orderId;

    private Map<Product, Integer> qntProductMap;

    @PostConstruct
    public void init() {
        System.out.println("Init DetailOrder Controller");

        qntProductMap = new HashMap<Product, Integer>();
        if (orderId != null) {
            int idOrder = Integer.parseInt(orderId);
            qntProductMap = orderProductDao.getProdQntByIdOrder(idOrder, userSessionBean.getType());

        }

    }
    
    @PreDestroy
    public void end() {
        System.out.println("End DetailOrder Controller");
    }

    public String toOrderList() {

        return "orderList?&faces-redirect=true";

    }

    public Map<Product, Integer> getQntProductMap() {
        return qntProductMap;
    }

    public void setQntProductMap(Map<Product, Integer> qntProductMap) {
        this.qntProductMap = qntProductMap;
    }

}
