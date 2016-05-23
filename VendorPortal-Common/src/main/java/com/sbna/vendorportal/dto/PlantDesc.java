package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the plant_desc database table.
 * 
 */
@Entity
@Table(name = "plant_desc")
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_plantdesc", allocationSize = 1)
//@NamedQuery(name = "PlantDesc.findAll", query = "SELECT p FROM PlantDesc p")
public class PlantDesc extends BaseModel {
	private static final long serialVersionUID = 1L;

	@Column(name = "plant_desc")
	private String plantDesc;

	// bi-directional many-to-one association to Language
	@ManyToOne(fetch = FetchType.LAZY)
	private Language lang;

	// bi-directional many-to-one association to Plant
	@ManyToOne(fetch = FetchType.LAZY)
	private Plant plant;

	public PlantDesc() {
	}

	public String getPlantDesc() {
		return this.plantDesc;
	}

	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}

	public Language getLanguage() {
		return this.lang;
	}

	public void setLanguage(Language lang) {
		this.lang = lang;
	}

	public Plant getPlant() {
		return this.plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

}