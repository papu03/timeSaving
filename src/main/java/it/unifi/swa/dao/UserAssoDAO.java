package it.unifi.swa.dao;

import javax.enterprise.context.Dependent;

import it.unifi.swa.domain.UserAssociation;

@Dependent
public class UserAssoDAO extends BaseDao<UserAssociation> {

	protected UserAssoDAO() {
		super(UserAssociation.class);
	}

}
