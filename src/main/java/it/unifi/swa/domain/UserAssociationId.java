package it.unifi.swa.domain;

import java.io.Serializable;

public class UserAssociationId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int utenteId;
	private int ordineId;
	
	public int hashCode() {
	    return (int)(utenteId + ordineId);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof UserAssociationId) {
	    	UserAssociationId otherId = (UserAssociationId) object;
	      return (otherId.utenteId == this.utenteId) && (otherId.ordineId == this.ordineId);
	    }
	    return false;
	  }
	
	
	public int getUtenteId() {
		return utenteId;
	}
	public void setUtenteId(int utenteId) {
		this.utenteId = utenteId;
	}
	public int getOrdineId() {
		return ordineId;
	}
	public void setOrdineId(int ordineId) {
		this.ordineId = ordineId;
	}


}
