/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unifi.swa.bean;

import it.unifi.swa.domain.Pub;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Alessandro
 */
@SessionScoped
@Named
public class SelectPubBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Pub> pubList;

    /**
     * @return the pubList
     */
    public List<Pub> getPubList() {
        return pubList;
    }

    /**
     * @param pubList the pubList to set
     */
    public void setPubList(List<Pub> pubList) {
        this.pubList = pubList;
    }


}
