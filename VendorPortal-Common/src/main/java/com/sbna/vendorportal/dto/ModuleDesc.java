package com.sbna.vendorportal.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the module_desc database table.
 * 
 */
@Entity
@Table(name="module_desc")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_module", allocationSize = 1)
public class ModuleDesc extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name="module_desc")
	private String moduleDesc;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lang_id")
	private Language lang;

	//bi-directional many-to-one association to Module
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="module_id")
	private Module module;

	public ModuleDesc() {
	}

	public String getModuleDesc() {
		return this.moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public Language getLang() {
		return this.lang;
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}