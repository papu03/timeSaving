package it.unifi.swa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;

import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;
import it.unifi.swa.domain.Pub;
import it.unifi.swa.domain.User;

@Dependent
public class OrderDAO extends BaseDao<Ordine> {

    private static final long serialVersionUID = 1L;

    protected OrderDAO() {
        super(Ordine.class);
    }

    // @Transactional
    // public Ordine insertOrder(Pub pub,boolean isFood,boolean isDrink){
    //
    // Ordine ord= new Ordine();
    // ord.setLocal(pub);
    // ord.setStateOrder('a');
    //
    // if(isFood && isDrink){
    // ord.setSizeOrder('b');
    // }else{
    // ord.setSizeOrder('a');
    // }
    //
    // this.save(ord);
    //
    // return ord;
    //
    // }
    public void insertOrder(Ordine ord, List<Operator> operators) {

        for (Operator op : operators) {

            if (op.getoType() == 'c') {
                ord.setCook(op);
            }

            if (op.getoType() == 'b') {
                ord.setBarman(op);
            }
        }

//		if (isFood && isDrink) {
//			ord.setSizeOrder('b');
//		} else {
//			ord.setSizeOrder('a');
//		}
        this.save(ord);

    }

    public List<Ordine> getOrderByClient(User client) {

        List<Ordine> orderList = new ArrayList<Ordine>();

        orderList = entityManager.createQuery("from Ordine o where o.client.idUser= :clientId", Ordine.class)
                .setParameter("clientId", client.getIdUser()).getResultList();

        //System.out.println("L'id dell'ordine è: "+orderList.get(0).getIdOrder());
        return orderList;

    }

//    public List<Ordine> getOrderByCook(User cook) {
//
//        List<Ordine> orderList = new ArrayList<Ordine>();
//
//        orderList = entityManager.createQuery("from Ordine o where o.cook.idUser = :cookId", Ordine.class)
//                .setParameter("cookId", cook.getIdUser()).getResultList();
//
//        return orderList;
//
//    }
//
//    public List<Ordine> getOrderByBarman(User barman) {
//
//        List<Ordine> orderList = new ArrayList<Ordine>();
//
//        orderList = entityManager.createQuery("from Ordine o where o.barman.idUser = :barmanId", Ordine.class)
//                .setParameter("barmanId", barman.getIdUser()).getResultList();
//
//        return orderList;
//
//    }

    public List<Ordine> getOrderByOperator(Operator operator, char tpOperator) {

        String condizione = "";
        List<Integer> idOrderList = new ArrayList<Integer>();
        List<Ordine> orderList = new ArrayList<Ordine>();

        try {

            if (tpOperator == 'b') {
                condizione = "(o.barman is null or o.barman.idUser = :userId) and o.tipoOrdine in ('d', 'm') and o.local= :local";
            } else if (tpOperator == 'c') {
                condizione = "(o.cook is null or o.cook.idUser = :userId) and o.tipoOrdine in ('f', 'm') and o.local= :local";
            } 
//            else{
//            	condizione = "o.client.idUser= :userId";
//            }

//            String query = "select o.idOrder from Ordine o where " + condizione;
//
//            idOrderList = entityManager.createQuery(query)
//                    .setParameter("userId", user.getIdUser()).getResultList();
//
//            for (int idOrder : idOrderList) {
//                orderList.add(this.findById(idOrder));
//            }
            String query = "from Ordine o where " + condizione;

            orderList = entityManager.createQuery(query, Ordine.class)
            		.setParameter("local", operator.getLocal())
                    .setParameter("userId", operator.getIdUser()).getResultList();

          

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return orderList;
    }

    public void setTipoOrdine(Ordine ord, boolean isFood, boolean isDrink) {

        if (isFood && isDrink) {
            ord.setTipoOrdine('m'); 
        } else if (isFood) {
            ord.setTipoOrdine('f');
        } else {
            ord.setTipoOrdine('d');
        }
    }

}
