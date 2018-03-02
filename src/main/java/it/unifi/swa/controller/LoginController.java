package it.unifi.swa.controller;

import it.unifi.swa.bean.SelectPubBean;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import it.unifi.swa.bean.UserSessionBean;
import it.unifi.swa.dao.ClientDAO;
import it.unifi.swa.dao.OperatorDAO;

import it.unifi.swa.domain.User;


@Model
public class LoginController {

    private User userData;

    @Inject
    private UserSessionBean userSession;

    @Inject
    private ClientDAO clientDao;
    @Inject
    private OperatorDAO operatorDao;
//    @Inject
//    private PubDAO pubDao;

    private String username;
    private String password;

    public String login() {

        userData = new User();
        userSession.setUser(null);
        userSession.setType('n');

        userData.setUsername(username);
        userData.setPassword(password);

        User loggedUser = null;
        
        try{
            loggedUser = clientDao.findByLoginInfo(userData);
        }catch(Exception ex){
            ex.printStackTrace();
        }
                

        if (loggedUser == null) {

            try{
                loggedUser = operatorDao.findByLoginInfo(userData);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            if (loggedUser == null) {
                throw new RuntimeException("Login Failed");
            }
            
            userSession.setType(operatorDao.findByLoginInfo(userData).getoType());

        } else {
            userSession.setType('u');
        }

        userSession.setUser(loggedUser);
        System.out.println(loggedUser.getUsername() + " loggato");
        
       // if(userSession.getType() == 0){
            return "selectPub?&faces-redirect=true";
//        }else{
//            return "orderList?&faces-redirect=true";
//        }
    }
    
    public String logOut() {

        userSession.setUser(null);
        userSession.setType('n');

        return "login?&faces-redirect=true";

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
