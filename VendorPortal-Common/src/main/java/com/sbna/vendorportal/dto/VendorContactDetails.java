package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.sbna.vendorportal.config.TableConfig;

@Entity
@Table(name = TableConfig.TABLE_VENDORCONTACTDETAILS)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_vendorcontactdetails", allocationSize = 1)
public class VendorContactDetails extends BaseModel {

    private static final long serialVersionUID = -8309150906560351973L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_details_id")
    @JsonIgnore
    private VendorDetails vendorDetailsId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "email", nullable = false)
    private String email;

    /*// bi-directional many-to-one association to Status
     @OneToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "attachment_id")
     private RefDocs refDocs;
     */
    public VendorDetails getVendorDetailsId() {
        return vendorDetailsId;
    }

    public void setVendorDetailsId(VendorDetails vendorDetailsId) {
        this.vendorDetailsId = vendorDetailsId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /*	public RefDocs getRefDocs() {
     return refDocs;
     }

     public void setRefDocs(RefDocs refDocs) {
     this.refDocs = refDocs;
     }
     */
}
