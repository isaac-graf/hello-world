package com.sbna.vendorportal.dto;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.sbna.vendorportal.aspect.AuditTransient;
import com.sbna.vendorportal.config.SecurityConfig;
import com.sbna.vendorportal.config.TableConfig;

@Entity
@Table(name = TableConfig.TABLE_USER)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_usr", allocationSize = 1)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class User extends BaseModel {

    @AuditTransient
    @Column(name = "usr_login_fail_cnt")
    private int usrLoginFailCnt;

    @AuditTransient
    @Column(name = "usr_status_changed")
    private int usrStatusChanged;

    @AuditTransient
    private String email;

    @AuditTransient
    @Column(name = "usr_name")
    private String usrName;

    @AuditTransient
    @Column(name = "usr_pwd")
    private String usrPwd;

    @AuditTransient
    @Column(name = "usr_token")
    private String usrToken;

    @AuditTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prchs_grp_id")
    @JsonIgnore
    private PurchaseGroup prchsGrp;

    @ManyToOne
    @JoinColumn(name = "usr_status_id")
    @JsonIgnore
    private Status status;

    @AuditTransient
    @OneToOne(optional = false, mappedBy = "usr")
    @JsonManagedReference
    private Profile profile;

    // bi-directional many-to-many association to Role
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "usr_role", joinColumns = {
        @JoinColumn(name = "usr_id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id")})
    @JsonManagedReference
    private Set<Role> roles;

    @JsonIgnore
    public boolean inRole(String roleName) {
        for (Role role : roles) {
            if (role.getRoleDescEng().equalsIgnoreCase(roleName)) {
                return true;
            }

        }
        return false;
    }

    public int getUsrLoginFailCnt() {
        return this.usrLoginFailCnt;
    }

    public void setUsrLoginFailCnt(int usrLoginFailCnt) {
        this.usrLoginFailCnt = usrLoginFailCnt;
    }

    public int getUsrStatusChanged() {
        return usrStatusChanged;
    }

    public void setUsrStatusChanged(int usrStatusChanged) {
        this.usrStatusChanged = usrStatusChanged;
    }

    public String getUsrName() {
        return this.usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    @JsonIgnore
    public String getUsrPwd() {
        return this.usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String getUsrToken() {
        return this.usrToken;
    }

    public void setUsrToken(String usrToken) {
        this.usrToken = usrToken;
    }

    public PurchaseGroup getPrchsGrp() {
        return this.prchsGrp;
    }

    public void setPrchsGrp(PurchaseGroup prchsGrp) {
        this.prchsGrp = prchsGrp;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Get non admin role ROLE_BUYER/ROLE_VENDOR
     *
     * @return
     */
    @JsonIgnore
    public String getNonAdminRoleName() {
        for (Role role : roles) {
            if ((SecurityConfig.ROLE_BUYER).equalsIgnoreCase(role.getRoleDescEng())
                    || (SecurityConfig.ROLE_VENDOR).equalsIgnoreCase(role.getRoleDescEng())) {
                return role.getRoleDescEng().toUpperCase();
            }
        }
        return null;
    }

    /**
     * Get non admin role id ROLE_BUYER/ROLE_VENDOR
     *
     * @return
     */
    @JsonIgnore
    public Long getNonAdminRoleId() {
        for (Role role : roles) {
            if ((SecurityConfig.ROLE_BUYER).equalsIgnoreCase(role.getRoleDescEng())
                    || (SecurityConfig.ROLE_VENDOR).equalsIgnoreCase(role.getRoleDescEng())) {
                return role.getId();
            }
        }
        return null;
    }

    /**
     * Get all non admin role id list
     *
     * @return
     */
    @JsonIgnore
    public List<Long> getAllNonAdminRoleIds() {
        List<Long> roleIds = null;
        for (Role role : roles) {
            if (!(SecurityConfig.ROLE_ADMIN).equalsIgnoreCase(role.getRoleDescEng())) {
                roleIds.add(role.getId());
            }
        }
        return roleIds;
    }

    @JsonIgnore
    public String getDisplayName() {
        Profile profile = this.getProfile();
        String userDisplayName = "";
        if (profile != null) {
            if ((SecurityConfig.ROLE_VENDOR).equalsIgnoreCase(this.getNonAdminRoleName())) {
                userDisplayName = profile.getFullName();
            } else {
                userDisplayName = profile.getFirstName() + " "
                        + profile.getLastName();
            }
        }
        return userDisplayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [UsrStatusChanged=" + usrStatusChanged + ", usrLoginFailCnt=" + usrLoginFailCnt + ", email=" + email
                + ", usrName=" + usrName + ", usrToken=" + usrToken
                + ", toString()=" + super.toString() + "]";
    }

}
