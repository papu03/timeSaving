package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.OPAssociation;

@Dependent
public class OrderProductDAO extends BaseDao<OPAssociation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected OrderProductDAO() {
		super(OPAssociation.class);
	}
}
