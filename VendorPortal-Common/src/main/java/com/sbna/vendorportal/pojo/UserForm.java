package com.sbna.vendorportal.pojo;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.config.SecurityConfig;
import com.sbna.vendorportal.dto.Role;
import com.sbna.vendorportal.dto.User;

public class UserForm {

    private Long userId;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;
    private String displayName;
    private String status;
    private boolean active;
    private boolean admin;
    private boolean auditor;
    private boolean buyer;
    private boolean vendor;
    private boolean approver;

    public UserForm() {
    }

    public UserForm(User user, String firstName,
            String lastName, String middleName, String fullName,
            String status) {
        super();
        this.userId = user.getId();
        this.userName = user.getUsrName();
        this.email = user.getEmail();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.fullName = fullName;
        this.status = status;
        updateRoles(user.getRoles());
        setDisplayName();
        setActive();
    }

    private void updateRoles(Set<Role> roles) {
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                if (SecurityConfig.ROLE_ADMIN.equalsIgnoreCase(role.getRoleDescEng())) {
                    this.admin = true;
                } else if (SecurityConfig.ROLE_AUDITOR.equalsIgnoreCase(role.getRoleDescEng())) {
                    this.auditor = true;
                } else if (SecurityConfig.ROLE_BUYER.equalsIgnoreCase(role.getRoleDescEng())) {
                    this.buyer = true;
                } else if (SecurityConfig.ROLE_VENDOR.equalsIgnoreCase(role.getRoleDescEng())) {
                    this.vendor = true;
                } else if (SecurityConfig.ROLE_APPROVER.equalsIgnoreCase(role.getRoleDescEng())) {
                    this.approver = true;
                }
            }
        }

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAuditor() {
        return auditor;
    }

    public void setAuditor(boolean auditor) {
        this.auditor = auditor;
    }

    public boolean isBuyer() {
        return buyer;
    }

    public void setBuyer(boolean buyer) {
        this.buyer = buyer;
    }

    public boolean isVendor() {
        return vendor;
    }

    public void setVendor(boolean vendor) {
        this.vendor = vendor;
    }

    public boolean isApprover() {
        return approver;
    }

    public void setApprover(boolean approver) {
        this.approver = approver;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName() {
        if (StringUtils.isBlank(this.fullName)) {
            this.displayName = this.firstName + " " + this.lastName;
        } else {
            this.displayName = this.fullName;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive() {
        if (this.status != null) {
            if (ProjectConfig.STATUS_ACTIVE.equalsIgnoreCase(this.status)) {
                this.active = true;
            }
        }
    }

    @Override
    public String toString() {
        return "UserForm [userId=" + userId + ", userName=" + userName
                + ", email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", middleName=" + middleName
                + ", fullName=" + fullName + ", displayName=" + displayName
                + ", status=" + status + ", active=" + active + ", admin="
                + admin + ", auditor=" + auditor + ", buyer=" + buyer
                + ", vendor=" + vendor + ", approver=" + approver + "]";
    }

}
