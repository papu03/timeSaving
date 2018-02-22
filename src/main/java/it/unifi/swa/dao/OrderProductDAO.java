package it.unifi.swa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.OPAssociation;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;

@Dependent
public class OrderProductDAO extends BaseDao<OPAssociation>{

	private static final long serialVersionUID = 1L;

	protected OrderProductDAO() {
		super(OPAssociation.class);
	}
	
	public void insertProdAssociation(Ordine ord, Map<Product, Integer> basket){
		
		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
			OPAssociation association=ord.addProduct(entry.getKey(), entry.getValue());
			this.save(association);
		}
		
	}
	
	public Map<Product, Integer> getProdQntByOrder(Ordine order){
		
		List<OPAssociation> prodAssociations=null;
		Map<Product, Integer> qntProductMap=new HashMap<Product, Integer>();

		try {
			prodAssociations= entityManager.createQuery("from OPAssociation opa where opa.idOrder= :idOrdine", OPAssociation.class)
					.setParameter("idOrdine", order.getIdOrder()).getResultList();

			for (OPAssociation opAssoc : prodAssociations) {

				//System.out.println(opAssoc.getProduct().getProdName());
				qntProductMap.put(opAssoc.getProduct(), opAssoc.getQuantity());
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return qntProductMap;
		
	}
}
