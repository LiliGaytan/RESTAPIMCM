package com.oracle.communications.brm.cc.custom.model;

import com.oracle.communications.brm.cc.model.Adapter1;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "creationDate", "accountId", "effectiveDate", "name", "objectCacheType", "serviceId", "accounting"})
@XmlRootElement(name = "profileMCM")
public class MCMProfileInfo implements Serializable {
    
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"day", "emailStatus", "invoiceFlags", "openItemPositionId",
                                  "payMethod", "payProvCode", "PoOrderNo", "taxData", "username"})
    @XmlRootElement(name = "accounting")
    public static class MCMProfileAccounting implements Serializable {

        @Schema(description = "Day.")
        protected Integer day;
        @Schema(description = "Email Status.")
        protected String emailStatus;
        @Schema(description = "Invoice Flags.")
        protected Integer invoiceFlags;
        @Schema(description = "Open Item Position Id.")
        protected String openItemPositionId;
        @Schema(description = "Pay Method.")
        protected String payMethod;
        @Schema(description = "Pay Prov Code.")
        protected String payProvCode;
        @Schema(description = "Po Order No.")
        protected String poOrderNo;
        @Schema(description = "Tax Data.")
        protected String taxData;
        @Schema(description = "Username.")
        protected String username;

        public Integer getDay() {
            return this.day;
        }

        public void setDay(Integer value) {
            this.day = value;
        }

        public String getEmailStatus() {
            return this.emailStatus;
        }

        public void setEmailStatus(String value) {
            this.emailStatus = value;
        }

        public Integer getInvoiceFlags() {
            return this.invoiceFlags;
        }

        public void setInvoiceFlags(Integer value) {
            this.invoiceFlags = value;
        }

        public String getOpenItemPositionId() {
            return this.openItemPositionId;
        }

        public void setOpenItemPositionId(String value) {
            this.openItemPositionId = value;
        }
        
        public String getPayMethod() {
            return this.payMethod;
        }

        public void setPayMethod(String value) {
            this.payMethod = value;
        }

        public String getPayProvCode() {
            return this.payProvCode;
        }

        public void setPayProvCode(String value) {
            this.payProvCode = value;
        }

        public String getPoOrderNo() {
            return this.poOrderNo;
        }

        public void setPoOrderNo(String value) {
            this.poOrderNo = value;
        }
        
        public String getTaxData() {
            return this.taxData;
        }

        public void setTaxData(String value) {
            this.taxData = value;
        }
        
        public String getUsername() {
            return this.username;
        }

        public void setUsername(String value) {
            this.username = value;
        }
    }
    
    @Schema(description = "Profile ID.")
    protected String id;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(value = Adapter1.class)
    @XmlSchemaType(name = "date")
    protected Calendar creationDate;
    @Schema(description = "Account ID.")
    protected String accountId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(value = Adapter1.class)
    @XmlSchemaType(name = "date")
    protected Calendar effectiveDate;
    @Schema(description = "Profile name.")
    protected String name;
    @Schema(description = "Object Cache Type.")
    protected Integer objectCacheType;
    @Schema(description = "Service ID.")
    protected String serviceId;
    @Schema(description = "Accounting object.")
    protected MCMProfileInfo.MCMProfileAccounting mcmProfileAccounting;

    public String getId() {
        return this.id;
    }
    
    public void setId(final String value) {
        this.id = value;
    }
    
    public Calendar getCreationDate() {
        return this.creationDate;
    }
    
    public void setCreationDate(final Calendar value) {
        this.creationDate = value;
    }
    
    public String getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(final String value) {
        this.accountId = value;
    }
    
    public Calendar getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setEffectiveDate(final Calendar value) {
        this.effectiveDate = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String value) {
        this.name = value;
    }
    
    public Integer getObjectCacheType() {
        return this.objectCacheType;
    }
    
    public void setObjectCacheType(final Integer value) {
        this.objectCacheType = value;
    }
    
    public String getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(final String value) {
        this.serviceId = value;
    }
    
    public MCMProfileInfo.MCMProfileAccounting getMCMProfileAccounting() {

        if (this.mcmProfileAccounting == null) {
            this.mcmProfileAccounting = new MCMProfileInfo.MCMProfileAccounting();
        }

        return this.mcmProfileAccounting;
    }
}
