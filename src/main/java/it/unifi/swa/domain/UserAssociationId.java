package it.unifi.swa.domain;

import java.io.Serializable;

public class UserAssociationId implements Serializable {
	
	private int idUser;
	private int idOrdine;
	
	public int hashCode() {
	    return (int)(idUser + idOrdine);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof UserAssociationId) {
	    	UserAssociationId otherId = (UserAssociationId) object;
	      return (otherId.idUser == this.idUser) && (otherId.idOrdine == this.idOrdine);
	    }
	    return false;
	  }
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}


}
