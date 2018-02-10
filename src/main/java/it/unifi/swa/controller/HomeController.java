package it.unifi.swa.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class HomeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String goToRegister(){
		return "register";
	}
	
	public String goToLogin(){
		return "login";
	}

}
