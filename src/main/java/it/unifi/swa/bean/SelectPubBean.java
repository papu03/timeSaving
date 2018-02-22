package it.unifi.swa.bean;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.domain.Pub;

@SessionScoped
@Named
public class SelectPubBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pub pub;

	public Pub getPub() {
		return pub;
	}

	public void setPub(Pub pub) {
		this.pub = pub;
	}

}
