package it.unifi.swa.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.transaction.Transactional;

import it.unifi.swa.domain.Operator;
import it.unifi.swa.domain.Ordine;
import it.unifi.swa.domain.User;
import it.unifi.swa.domain.UserAssociation;

@Dependent
public class UserAssoDAO extends BaseDao<UserAssociation> {

	protected UserAssoDAO() {
		super(UserAssociation.class);
	}

	@Transactional
	public void insertUserAssociation(Ordine ord, User client, boolean isFood, boolean isDrink) {

		List<Operator> cook = null;
		List<Operator> barman = null;
		try {
			UserAssociation aClient = ord.addUser(client);

			this.save(aClient);

			if (isFood) {

				cook = entityManager.createQuery("from Operator o where o.oType = :oType", Operator.class)
						.setParameter("oType", 'c').getResultList();
				UserAssociation aCook = ord.addUser(cook.get(0));
				this.save(aCook);
			}

			if (isDrink) {

				barman = entityManager.createQuery("from Operator o where o.oType = :oType", Operator.class)
						.setParameter("oType", 'b').getResultList();

				UserAssociation aBarman = ord.addUser(barman.get(0));
				this.save(aBarman);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public List<UserAssociation>  getUserAssocByUser(User user){
		
		List<UserAssociation> userAssociations=null;
		try {
			userAssociations= entityManager.createQuery("from UserAssociation ua where ua.utenteId = :utenteid", UserAssociation.class)
					.setParameter("utenteid", user.getIdUser()).getResultList();

			System.out.println(userAssociations.get(0).getOrdineId());
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return userAssociations;
		
	}
}
