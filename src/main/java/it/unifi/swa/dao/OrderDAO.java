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
	
	@Transactional
	public Ordine insertOrder(Pub pub){
		
		Ordine ord= new Ordine();
		
		ord.setLocal(pub);
		
		this.save(ord);
		
		return ord;

	}
	
//	public List<Operator> getBarman(){
//		List<Operator> barman = null;
//
//		try {
//			barman = entityManager.createQuery("from Operator o where o.oType = :oType", Operator.class)
//					.setParameter("oType", 1).getResultList();
//
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return barman;
//
//	}
//	
//	
//	@Transactional
//	public void saveOrder(Ordine order) {
//
//		this.save(order);
//	}

}
