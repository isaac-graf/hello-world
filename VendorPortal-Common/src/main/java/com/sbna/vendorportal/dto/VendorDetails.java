package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sbna.vendorportal.config.TableConfig;

@Entity
@Table(name = TableConfig.TABLE_VENDORDETAILS)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_vendordetails", allocationSize = 1)
public class VendorDetails extends BaseModel {

    private static final long serialVersionUID = -8309150906560351973L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_profile_id")
    @JsonIgnore
    private VendorProfile vendorProfileId;

    // bi-directional many-to-one association to Status
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_details_status_id")
    @JsonIgnore
    private Status status;

    @Column(name = "additional_information", nullable = false)
    private String additionalInformation;

    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street2", nullable = false)
    private String street2;

    @Column(name = "street3", nullable = false)
    private String street3;

    @Column(name = "houseNo", nullable = false)
    private String houseNo;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "land", nullable = false)
    private String land;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "payment_term", nullable = false)
    private String paymentTerm;

    @Column(name = "payment_term_text", nullable = false)
    private String paymentTermText;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "telephone_extn", nullable = false)
    private String telephoneExtn;

    @Column(name = "mobile_num", nullable = false)
    private String mobileNum;

    @Column(name = "telephone_fax", nullable = false)
    private String telephoneFax;

    @Column(name = "fax_extn", nullable = false)
    private String faxExtn;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "account_number", nullable = false)
    private String acctNum;

    @Column(name = "message_type", nullable = false)
    private String messageType;

    @Column(name = "bank_key", nullable = false)
    private String bankKey;

    @Column(name = "iban_num", nullable = false)
    private String ibanNum;

    @Column(name = "bank_branch", nullable = false)
    private String bankBranch;

    @Column(name = "bank_street", nullable = false)
    private String bankStreet;

    @Column(name = "bank_city", nullable = false)
    private String bankCity;

    @Column(name = "bank_region", nullable = false)
    private String bankRegion;

    @OneToMany(mappedBy = "vendorDetailsId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<VendorContactDetails> vendorContactDetails;

    public VendorProfile getVendorProfileId() {
        return vendorProfileId;
    }

    public void setVendorProfileId(VendorProfile vendorProfileId) {
        this.vendorProfileId = vendorProfileId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreet3() {
        return street3;
    }

    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephoneExtn() {
        return telephoneExtn;
    }

    public void setTelephoneExtn(String telephoneExtn) {
        this.telephoneExtn = telephoneExtn;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getTelephoneFax() {
        return telephoneFax;
    }

    public void setTelephoneFax(String telephoneFax) {
        this.telephoneFax = telephoneFax;
    }

    public String getFaxExtn() {
        return faxExtn;
    }

    public void setFaxExtn(String faxExtn) {
        this.faxExtn = faxExtn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getBankKey() {
        return bankKey;
    }

    public void setBankKey(String bankKey) {
        this.bankKey = bankKey;
    }

    public String getIbanNum() {
        return ibanNum;
    }

    public void setIbanNum(String ibanNum) {
        this.ibanNum = ibanNum;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankStreet() {
        return bankStreet;
    }

    public void setBankStreet(String bankStreet) {
        this.bankStreet = bankStreet;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankRegion() {
        return bankRegion;
    }

    public void setBankRegion(String bankRegion) {
        this.bankRegion = bankRegion;
    }

    public List<VendorContactDetails> getVendorContactDetails() {
        return vendorContactDetails;
    }

    public void setVendorContactDetails(List<VendorContactDetails> vendorContactDetails) {
        this.vendorContactDetails = vendorContactDetails;
    }

    public String getPaymentTermText() {
        return paymentTermText;
    }

    public void setPaymentTermText(String paymentTermText) {
        this.paymentTermText = paymentTermText;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
