package com.oracle.communications.brm.cc.custom.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { })
@XmlRootElement(name = "profileMCM")
public class CustomProfileMCM implements Serializable {

    @Schema(description = "MCM profile.")
    protected MCMProfileInfo mcmProfileInfo;
    
    public void setProfileInfo(MCMProfileInfo value) {
        this.mcmProfileInfo = value;
    }
    
    public MCMProfileInfo getProfileInfo() {
        if (this.mcmProfileInfo == null) {
            this.mcmProfileInfo = new MCMProfileInfo();
        }
        return this.mcmProfileInfo;
    }
}
