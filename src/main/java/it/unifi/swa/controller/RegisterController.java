package it.unifi.swa.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.domain.Client;

@Model
public class RegisterController implements Serializable {

    
    private static final long serialVersionUID = 1L;

    private Client client;
    private String name;
    private String surname;
    private String address;
    private int bankData;
    private String password;
    private String password_check;
    private String username;
    private String email;
    private boolean checkPassFail;
    private boolean emailFail;
    private boolean userNameFail;
    private boolean passFail;


    @Inject
    private ClientDAO clientDao;

    @PostConstruct
	public void init() {
		System.out.println("Register Controller Init");
		checkPassFail=false;
		emailFail=false;
		userNameFail=false;
		passFail=false;
    }

    @PreDestroy
	public void end() {
		System.out.println("End Register Controller");
		 
	}
    
    public String register() {
        
        if(!password.equals(password_check)){
            //return "register?pwd_diff=y&faces-redirect=true";
        	checkPassFail=true;
        	//return "register?pwd_diff=y&faces-redirect=true";
        }
        if(username.isEmpty()){
        	userNameFail=true;
        }
        if(password.isEmpty()){
        	passFail=true;
        }
        if(email != null && email.isEmpty()){
        	emailFail=true;
        }
        
        if(!checkPassFail && !userNameFail && !passFail && !emailFail){
        	 client = new Client();

             client.setName(name);
             client.setSurname(surname);
             client.setAddress(address);
             client.setBankData(bankData);
             client.setUsername(username);
             client.setPassword(password);
             client.setEmail(email);
             clientDao.saveClient(client);
             return "success?&faces-redirect=true";
        }else{
        	 return "";
        }
        
       

        // System.out.println("il nome è "+client.getName()+" il cognome
        // "+cognome );
//        if(!username.equals(null) && !password.equals(null) && !email.equals(null)){
//       	 clientDao.saveClient(client);
//            return "success?&faces-redirect=true";
//       }else{
//       	return "register?&faces-redirect=true";
//       }
      
       
    }

    public String back(){
        return "login?&faces-redirect=true";
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBankData() {
        return bankData;
    }

    public void setBankData(int bankData) {
        this.bankData = bankData;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    public String getPassword_check() {
        return password_check;
    }

    public void setPassword_check(String password_check) {
        this.password_check = password_check;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isCheckPassFail() {
		return checkPassFail;
	}

	public void setCheckPassFail(boolean checkPassFail) {
		this.checkPassFail = checkPassFail;
	}

	public boolean isEmailFail() {
		return emailFail;
	}

	public void setEmailFail(boolean emailFail) {
		this.emailFail = emailFail;
	}


	public boolean isPassFail() {
		return passFail;
	}

	public void setPassFail(boolean passFail) {
		this.passFail = passFail;
	}

	public boolean isUserNameFail() {
		return userNameFail;
	}

	public void setUserNameFail(boolean userNameFail) {
		this.userNameFail = userNameFail;
	}

}
