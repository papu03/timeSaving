package it.unifi.swa.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.OperatorDAO;
import it.unifi.swa.dao.OrderDAO;
import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.User;
import javax.transaction.Transactional;

@Named
@ViewScoped
public class OrderListController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserSessionBean userSessionBean;

    @Inject
    private OrderDAO orderDao;

    @Inject
    private OperatorDAO operatorDao;

    private List<Ordine> orderList;

    private boolean isOperatore;
    private boolean isClient;
    private int changeStateCount;

    @PostConstruct
    public void init() {
        System.out.println("Init Order List Controller");

        orderList = new ArrayList<Ordine>();
        User userSession = userSessionBean.getUser();
        isOperatore = false;
        isClient = false;
        changeStateCount = 0;

        if (userSessionBean.getType() == 'c' || userSessionBean.getType() == 'b') {

            /*for (Ordine ord : orderDao.getOrderByOperator(userSession, userSessionBean.getType())) {
                orderList.add(ord);
            }*/
            setOrderList(orderDao.getOrderByOperator(userSession, userSessionBean.getType()));
            isOperatore = true;

        } else {

            for (Ordine ord : orderDao.getOrderByClient(userSession)) {
                orderList.add(ord);
            }

            isClient = true;
        }
    }

    public String toDetailOrder(Ordine order) {

        return "detailOrder?id=" + order.getIdOrder() + "&faces-redirect=true";

    }

    @Transactional
    public void changeOrderState(Ordine order) {

        //if (changeStateCount != 3) {
        if (order.getStateOrder() == 'a') {

            try {
                if (userSessionBean.getType() == 'b') {
                    Operator barman = operatorDao.findById(userSessionBean.getUser().getIdUser());
                    order.setBarman(barman);
                    order.setStateOrder('b');
                } else if (userSessionBean.getType() == 'c') {
                    Operator cook = operatorDao.findById(userSessionBean.getUser().getIdUser());
                    order.setCook(cook);
                    order.setStateOrder('c');
                }

                orderDao.update(order);
                //changeStateCount++;

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {

            try {

                if (order.getStateOrder() != 'd' && order.getStateOrder() != 'e') {

                    if (order.getBarman() != null && order.getCook() == null && userSessionBean.getType() == 'c') {
                        Operator cook = operatorDao.findById(userSessionBean.getUser().getIdUser());
                        order.setCook(cook);
                        order.setStateOrder('d');
                    } else if (order.getCook() != null && order.getBarman() == null && userSessionBean.getType() == 'b') {
                        Operator barman = operatorDao.findById(userSessionBean.getUser().getIdUser());
                        order.setBarman(barman);
                        order.setStateOrder('d');
                    } else {
                        order.setStateOrder('e');
                    }
                } else {
                    order.setStateOrder('e');
                }


                /*if (order.getBarman() != null && order.getCook() != null) {

                        if (order.getStateOrder() == 'b') {

                            if (userSessionBean.getType() == 'c') { // cuoco
                                order.setStateOrder('c');
                            } else if (userSessionBean.getType() == 'b') { // barista
                                order.setStateOrder('d');
                            }

                        } else {


                        }

                    } else {

                        if (order.getStateOrder() == 'a') {
                            order.setStateOrder('b');
                        } else {
                            order.setStateOrder('e');
                        }
                    }*/
                orderDao.update(order);
                //changeStateCount++;

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        //}
    }

    public List<Ordine> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Ordine> orderList) {
        this.orderList = orderList;
    }

    public boolean isIsOperatore() {
        return isOperatore;
    }

    public void setIsOperatore(boolean isOperatore) {
        this.isOperatore = isOperatore;
    }

    public boolean isIsClient() {
        return isClient;
    }

    public void setClient(boolean isClient) {
        this.isClient = isClient;
    }

    public int getChangeStateCount() {
        return changeStateCount;
    }

    public void setChangeStateCount(int changeStateCount) {
        this.changeStateCount = changeStateCount;
    }

}
