package it.unifi.swa.dao;

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
public class OrderDAO extends BaseDao<Ordine>{

	private static final long serialVersionUID = 1L;

	protected OrderDAO() {
		super(Ordine.class);
	}
	
	public void insertOrder(User client,Pub pub,boolean isFood,boolean isDrink){
		Ordine ord= new Ordine();
		List<Operator> cook = null;
		List<Operator> barman = null;

		ord.getUsers().add(client);
		ord.setLocal(pub);

		if(isFood){
			try {
				cook = entityManager.createQuery("from Operator o where o.oType = :oType", Operator.class)
						.setParameter("oType", 2).getResultList();
				ord.getUsers().add(cook.get(0));

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(isDrink){
			try {
				barman = entityManager.createQuery("from Operator o where o.oType = :oType", Operator.class)
						.setParameter("oType", 1).getResultList();

				ord.getUsers().add(barman.get(0));

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		this.saveOrder(ord);
		//System.out.println(barman.get(0));
	}
	
	@Transactional
	public void saveOrder(Ordine order) {

		this.save(order);
	}

}
