package it.unifi.swa.dao;

import java.util.ArrayList;
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
	public List<UserAssociation> insertUserAssociation(Ordine ord, User client, boolean isFood, boolean isDrink) {

		List<Operator> cook = null;
		List<Operator> barman = null;
		List<UserAssociation> userAssociations=new ArrayList<UserAssociation>();

		try {
			UserAssociation aClient = ord.addUser(client);

			this.save(aClient);
			userAssociations.add(aClient);

			if (isFood) {

				cook =  entityManager.createQuery("from Operator o where o.oType = :oType AND o.local= :local", Operator.class)
						.setParameter("oType", 'c').
						setParameter("local", ord.getLocal()).
						getResultList();
				
				UserAssociation aCook = ord.addUser(cook.get(0)); //prendiamo il primo per semplicità
				this.save(aCook);
				userAssociations.add(aCook);
			}

			if (isDrink) {

				barman = entityManager.createQuery("from Operator o where o.oType = :oType AND o.local= :local", Operator.class)
						.setParameter("oType", 'b').
						setParameter("local", ord.getLocal()).
						getResultList();

				UserAssociation aBarman = ord.addUser(barman.get(0));//prendiamo il primo per semplicità
				this.save(aBarman);
				userAssociations.add(aBarman);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return userAssociations;
	}
	
	public List<UserAssociation> getUserAssocByUser(User user){
		
		List<UserAssociation> userAssociations=new ArrayList<UserAssociation>();
		
		userAssociations= entityManager.createQuery("from UserAssociation ua where ua.utenteId = :utenteid", UserAssociation.class)
					.setParameter("utenteid", user.getIdUser()).getResultList();

		//System.out.println(userAssociations.get(0).getOrdineId());
			
		return userAssociations;
		
	}
	
	public List<UserAssociation> getUserAssocByOrder(Ordine order){
		
		List<UserAssociation> userAssociations=new ArrayList<UserAssociation>();
		
		userAssociations= entityManager.createQuery("from UserAssociation ua where ua.ordineId = :ordineid", UserAssociation.class)
					.setParameter("ordineid", order.getIdOrder()).getResultList();
			
		return userAssociations;
		
	}
}
