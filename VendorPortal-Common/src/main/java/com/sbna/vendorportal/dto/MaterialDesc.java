package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the material_desc database table.
 * 
 */
@Entity
@Table(name = "material_desc")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_materialdesc", allocationSize = 1)
//@NamedQuery(name = "MaterialDesc.findAll", query = "SELECT m FROM MaterialDesc m")
public class MaterialDesc extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "material_desc")
	private String materialDesc;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	private Language lang;

	// bi-directional many-to-one association to Material
	@ManyToOne(fetch = FetchType.LAZY)
	private Material material;

	public MaterialDesc() {
	}

	public String getMaterialDesc() {
		return this.materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public Language getLanguage() {
		return this.lang;
	}

	public void setLanguage(Language lang) {
		this.lang = lang;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}