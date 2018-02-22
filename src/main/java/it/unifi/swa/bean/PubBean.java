package it.unifi.swa.bean;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.domain.Pub;

@ConversationScoped
@Named
public class PubBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conversation conversation;
	
	private Pub selectedPub;

	public Pub getSelectedPub() {
		return selectedPub;
	}

	public void setSelectedPub(Pub selectedPub) {
		this.selectedPub = selectedPub;
	}
	
	public void initConversation() {
		if (conversation.isTransient())
			conversation.begin();
	}

	public String endConversation() {
		if (!conversation.isTransient())
			conversation.end();
		return "back";
	}

}
