package it.unifi.swa.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client extends User {

	
	private long bankData;

	public long getBankData() {
		return bankData;
	}

	public void setBankData(long bankData) {
		this.bankData = bankData;
	}
	


}
