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

	public void insertOrder(Ordine ord, List<Operator> operators, boolean isFood, boolean isDrink) {

		for (Operator op : operators) {

			if (op.getoType() == 'c') {
				ord.setCook(op);
			}

			if (op.getoType() == 'b') {
				ord.setBarman(op);
			}
		}

		if (isFood && isDrink) {
			ord.setSizeOrder('b');
		} else {
			ord.setSizeOrder('a');
		}

		this.save(ord);

	}

	public List<Ordine> getOrderByClient(User client) {

		List<Ordine> orderList = new ArrayList<Ordine>();

		orderList = entityManager.createQuery("from Ordine o where o.client = :client", Ordine.class)
				.setParameter("client", client).getResultList();

		System.out.println("L'id dell'ordine è: "+orderList.get(0).getIdOrder());

		return orderList;

	}

	public List<Ordine> getOrderByCook(User cook) {

		List<Ordine> orderList = new ArrayList<Ordine>();

		orderList = entityManager.createQuery("from Ordine o where o.cook = :cook", Ordine.class)
				.setParameter("cook", cook).getResultList();

		return orderList;

	}

	public List<Ordine> getOrderByBarman(User barman) {

		List<Ordine> orderList = new ArrayList<Ordine>();

		orderList = entityManager.createQuery("from Ordine o where o.barman = :barman", Ordine.class)
				.setParameter("barman", barman).getResultList();

		return orderList;

	}

}
