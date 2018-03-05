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
	
//	@Transactional
//	public Ordine insertOrder(Pub pub,boolean isFood,boolean isDrink){
//		
//		Ordine ord= new Ordine();
//		ord.setLocal(pub);
//		ord.setStateOrder('a');
//		
//		if(isFood && isDrink){
//			ord.setSizeOrder('b');
//		}else{
//			ord.setSizeOrder('a');
//		}
//
//		this.save(ord);
//		
//		return ord;
//
//	}
	
	@Transactional
	public void insertOrder(Ordine ord,boolean isFood,boolean isDrink){
		
		
		if(isFood && isDrink){
			ord.setSizeOrder('b');
		}else{
			ord.setSizeOrder('a');
		}

		this.save(ord);
		

	}


}
