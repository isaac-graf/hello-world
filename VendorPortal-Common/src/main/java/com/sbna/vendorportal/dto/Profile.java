package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.sbna.vendorportal.config.TableConfig;

/**
 * The persistent class for the profile database table.
 *
 */
@Entity
@Table(name = TableConfig.TABLE_PROFILE)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_profile", allocationSize = 1)
public class Profile extends BaseModel {

    @Column(name = "contact_no")
    private String contactNo;
   

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @OneToOne(optional = false)
    @JoinColumn(name = "usr_id", unique = true, nullable = false, updatable = false)
    @JsonBackReference
    private User usr;

    public String getContactNo() {
        return this.contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    @Override
    public String toString() {
        return "Profile [contactNo=" + contactNo 
                + ", firstName=" + firstName + ", fullName=" + fullName
                + ", lastName=" + lastName + ", middleName=" + middleName
                + ", toString()=" + super.toString() + "]";
    }

}
