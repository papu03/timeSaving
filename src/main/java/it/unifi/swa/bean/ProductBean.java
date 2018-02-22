package it.unifi.swa.bean;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.domain.Product;

@ConversationScoped
@Named
public class ProductBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Conversation conversation;
	
	private Product product;
	
	public void initConversation() {
		if (conversation.isTransient())
			conversation.begin();
	}

	public String endConversation() {
		if (!conversation.isTransient())
			conversation.end();
		return "back";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
