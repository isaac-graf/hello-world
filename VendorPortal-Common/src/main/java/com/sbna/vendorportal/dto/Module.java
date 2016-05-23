package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.sbna.vendorportal.config.TableConfig;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = TableConfig.TABLE_MODULE)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_module", allocationSize = 1)
public class Module extends BaseModel {

    @Column(name = "module_url", nullable = false)
    private String moduleUrl;

    @Column(name = "module_desc")
    private String moduleDesc;

    @Column(name = "module_url_role", nullable = true)
    private String moduleUrlRole;

    @Column(name = "menu_order", nullable = true)
    private int menuOrder;

    @Column(name = "menu_icon_url", nullable = true)
    private String menuIcon;

    // @ManyToOne(fetch=FetchType.LAZY)
    // @JoinColumn(name = "parent_url_id")
    // private Module parent;
    //
    // @OneToMany(fetch=FetchType.LAZY, mappedBy = "parent")
    // private List<Module> children;
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    private List<ModuleDesc> moduleDescs;

    // bi-directional many-to-many association to Role
    @ManyToMany
    @JoinTable(name = "module_role", joinColumns = {
        @JoinColumn(name = "module_id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_id")})
    @JsonManagedReference
    private List<Role> roles;

    // bi-directional many-to-many association to Status
    @ManyToMany
    @JoinTable(name = "module_status", joinColumns = {
        @JoinColumn(name = "module_id")}, inverseJoinColumns = {
        @JoinColumn(name = "status_id")})
    @JsonManagedReference
    private List<Status> statuses;

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Status> getStatuses() {
        return this.statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public String getModuleUrlRole() {
        return moduleUrlRole;
    }

    public void setModuleUrlRole(String moduleUrlRole) {
        this.moduleUrlRole = moduleUrlRole;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public List<ModuleDesc> getModuleDescs() {
        return moduleDescs;
    }

    public void setModuleDescs(List<ModuleDesc> moduleDescs) {
        this.moduleDescs = moduleDescs;
    }

}
