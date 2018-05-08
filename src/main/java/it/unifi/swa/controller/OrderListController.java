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
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
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


            setOrderList(orderDao.getOrderByOperator((Operator)userSession));
            isOperatore = true;

        } else {


            setOrderList(orderDao.getOrderByClient(userSession));
            isClient = true;
        }
    }

    public String toDetailOrder(Ordine order) {

        return "detailOrder?id=" + order.getIdOrder() + "&faces-redirect=true";

    }

    @Transactional
    public void changeOrderState(int idOrder) {

        Ordine order = orderDao.findById(idOrder);

        if (order.getStateOrder() == 'a') {

            try {
                if (userSessionBean.getType() == 'b') {
                	//System.out.println(userSessionBean.getUser().getIdUser());

                    Operator barman = operatorDao.findById(userSessionBean.getUser().getIdUser());
                    order.setBarman(barman);

                    order.setStateOrder('b');
                } else if (userSessionBean.getType() == 'c') {
                    Operator cook = operatorDao.findById(userSessionBean.getUser().getIdUser());
                    order.setCook(cook);
                    order.setStateOrder('c');
                }

                orderDao.update(order);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {

            try {

                if (order.getTipoOrdine() == 'd') {
                    order.setStateOrder('g');
                } else if (order.getTipoOrdine() == 'f') {
                    order.setStateOrder('g');
                } else {

                    if (userSessionBean.getType() == 'c') {
                        if (order.getBarman() != null && order.getCook() == null) {
                            Operator cook = operatorDao.findById(userSessionBean.getUser().getIdUser());
                            order.setCook(cook);
                            if(order.getStateOrder() != 'f'){
                                order.setStateOrder('d');
                            }
                        } else if (order.getBarman() != null && order.getCook() != null) {
                            if (order.getStateOrder() == 'f') {
                                order.setStateOrder('g');
                            } else {
                                order.setStateOrder('e');

                            }
                        } else {
                            order.setStateOrder('e');
                        }
                    }

                    if (userSessionBean.getType() == 'b') {
                        if (order.getCook() != null && order.getBarman() == null) {
                            Operator barman = operatorDao.findById(userSessionBean.getUser().getIdUser());
                            order.setBarman(barman);
                            order.setStateOrder('d');
                        } else if (order.getCook() != null && order.getBarman() != null) {
                            if (order.getStateOrder() == 'e') {
                                order.setStateOrder('g');
                            } else {
                                order.setStateOrder('f');

                            }
                        } else {
                            order.setStateOrder('f');
                        }
                    }

                }

                orderDao.update(order);

            
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        setOrderList(orderDao.getOrderByOperator((Operator)userSessionBean.getUser()));
        
    }

    public void refresh() {

        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);

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
