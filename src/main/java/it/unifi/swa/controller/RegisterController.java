package it.unifi.swa.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.domain.Client;

@Named
@SessionScoped
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

    @Inject
    private ClientDAO clientDao;


    public String register() {
        
        if(!password.equals(password_check)){
            return "register?pwd_diff=y&faces-redirect=true";
        }
        
        client = new Client();

        client.setName(name);
        client.setSurname(surname);
        client.setAddress(address);
        client.setBankData(bankData);
        client.setUsername(username);
        client.setPassword(password);

        // System.out.println("il nome è "+client.getName()+" il cognome
        // "+cognome );
        clientDao.saveClient(client);
        return "success?&faces-redirect=true";
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

}
