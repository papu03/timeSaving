package it.unifi.swa.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import it.unifi.swa.domain.User;


@SessionScoped
@Named
public class UserSessionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private int type; //0 cliente 1 barista 2 cuoco

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return user != null;
    }
    
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
