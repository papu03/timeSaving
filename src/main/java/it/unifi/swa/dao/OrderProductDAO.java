package it.unifi.swa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;

import it.unifi.swa.domain.OPAssociation;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.Product;

@Dependent
public class OrderProductDAO extends BaseDao<OPAssociation> {

	private static final long serialVersionUID = 1L;

	protected OrderProductDAO() {
		super(OPAssociation.class);
	}

//	public void insertProdAssociation(Ordine ord, Map<Product, Integer> basket) {
//
//		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
//			OPAssociation association = ord.addProduct(entry.getKey(), entry.getValue());
//			this.save(association);
//		}
//
//	}
	
	@Transactional
	public void insertProdAssociation(Ordine ord, List<OPAssociation> opaList) {

		for (OPAssociation opa : opaList) {
			opa.setIdOrder(ord.getIdOrder());
    		//System.out.println("id order: "+opa.getIdOrder()+" id product "+opa.getIdProduct());
			ord.getProducts().add(opa);
			this.save(opa);
		}

	}

//	public Map<Product, Integer> getProdQntByOrder(Ordine order, int type) {
//
//		List<OPAssociation> prodAssociations = null;
//		Map<Product, Integer> qntProductMap = new HashMap<Product, Integer>();
//
//		System.out.println("il tipo è "+type);
//		try {
//			prodAssociations = entityManager
//					.createQuery("from OPAssociation opa where opa.idOrder= :idOrdine", OPAssociation.class)
//					.setParameter("idOrdine", order.getIdOrder()).getResultList();
//			if (type == 'b') {// barista
//
//				for (OPAssociation opAssoc : prodAssociations) {
//
//					if (opAssoc.getProduct().getTpProduct() == 'd') {
//						qntProductMap.put(opAssoc.getProduct(), opAssoc.getQuantity());
//					}
//				}
//			} else if (type == 'c') {// cuoco
//
//				for (OPAssociation opAssoc : prodAssociations) {
//
//					if (opAssoc.getProduct().getTpProduct() == 'f') {
//						qntProductMap.put(opAssoc.getProduct(), opAssoc.getQuantity());
//					}
//				}
//
//			} else { // cliente
//
//				for (OPAssociation opAssoc : prodAssociations) {
//
//					qntProductMap.put(opAssoc.getProduct(), opAssoc.getQuantity());
//
//				}
//
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return qntProductMap;
//
//	}
	public Map<Product, Integer> getProdQntByIdOrder(int idOrder, int type) {

		List<OPAssociation> prodAssociations = null;
		Map<Product, Integer> qntProductMap = new HashMap<Product, Integer>();
		
		System.out.println("il tipo è "+type);
		try {
			prodAssociations = entityManager
					.createQuery("from OPAssociation opa where opa.idOrder= :idOrdine", OPAssociation.class)
					.setParameter("idOrdine", idOrder).getResultList();
			if (type == 'b') {// barista

				for (OPAssociation opAssoc : prodAssociations) {

					if (opAssoc.getProduct().getTpProduct() == 'd') {
						qntProductMap.put(opAssoc.getProduct(), opAssoc.getQuantity());
					}
				}
			} else if (type == 'c') {// cuoco

				for (OPAssociation opAssoc : prodAssociations) {

					if (opAssoc.getProduct().getTpProduct() == 'f') {
						qntProductMap.put(opAssoc.getProduct(), opAssoc.getQuantity());
					}
				}

			} else { // cliente

				for (OPAssociation opAssoc : prodAssociations) {

					qntProductMap.put(opAssoc.getProduct(), opAssoc.getQuantity());

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return qntProductMap;

	}
}
