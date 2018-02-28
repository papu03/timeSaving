package it.unifi.swa.bean;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.domain.Ordine;

@ConversationScoped
@Named
public class OrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Conversation conversation;

	private Ordine order;

	public void initConversation() {
		if (conversation.isTransient())
			conversation.begin();
	}

	public String endConversation() {
		if (!conversation.isTransient())
			conversation.end();
		return "back";
	}

	public Ordine getOrder() {
		return order;
	}

	public void setOrder(Ordine order) {
		this.order = order;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}
