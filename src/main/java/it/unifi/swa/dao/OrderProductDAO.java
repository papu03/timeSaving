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

	
	@Transactional
	public void insertProdAssociation(Ordine ord, List<OPAssociation> opaList) {

		for (OPAssociation opa : opaList) {
			opa.setIdOrder(ord.getIdOrder());
			ord.getProducts().add(opa);
			this.save(opa);
		}

	}
	
	public void removeFromProduct(Product prod){
		List<OPAssociation> prodAssociations = null;
		
		try {
			prodAssociations = entityManager
					.createQuery("from OPAssociation opa where opa.product= :product", OPAssociation.class)
					.setParameter("product", prod).getResultList();
			
			for (OPAssociation opa : prodAssociations) {
				//System.out.println(opa.getProduct().getProdName());
				this.delete(opa);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public Map<Product, Integer> getProdQntByIdOrder(int idOrder, char type) {

		List<OPAssociation> prodAssociations = null;
		Map<Product, Integer> qntProductMap = new HashMap<Product, Integer>();
		
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
