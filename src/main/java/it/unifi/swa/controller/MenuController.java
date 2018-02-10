package it.unifi.swa.controller;

import it.unifi.swa.bean.MenuBean;
import it.unifi.swa.domain.Pub;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alessandro
 */

@Named
@SessionScoped
public class MenuController implements Serializable {
    
    @Inject
    private MenuBean menuBean;
    
    private Pub selectedPub;
    
}
