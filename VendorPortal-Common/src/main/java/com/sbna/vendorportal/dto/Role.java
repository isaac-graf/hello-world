package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.sbna.vendorportal.config.TableConfig;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = TableConfig.TABLE_ROLE)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_role", allocationSize = 1)
public class Role extends BaseModel {

	@Column(name = "role_desc_eng")
	private String roleDescEng;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
	private List<User> usrs;

	// bi-directional many-to-many association to Module
	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
	private List<Module> modules;

	public String getRoleDescEng() {
		return this.roleDescEng;
	}

	public void setRoleDescEng(String roleDescEng) {
		this.roleDescEng = roleDescEng;
	}

	public List<User> getUsrs() {
		return this.usrs;
	}

	public void setUsrs(List<User> usrs) {
		this.usrs = usrs;
	}

	public List<Module> getModules() {
		return this.modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "Role [roleDescEng=" + roleDescEng + ", toString()="
				+ super.toString() + "]";
	}
	
}
