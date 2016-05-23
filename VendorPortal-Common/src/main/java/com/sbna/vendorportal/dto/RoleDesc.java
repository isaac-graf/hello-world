package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the role_desc database table.
 * 
 */
@Entity
@Table(name = TableConfig.TABLE_ROLE_DESC)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_rfqdesc", allocationSize = 1)
public class RoleDesc extends BaseModel {

	@Column(name = "role_desc")
	private String roleDesc;

	@ManyToOne(fetch = FetchType.LAZY)
	private Language lang;

	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Language getLang() {
		return this.lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}