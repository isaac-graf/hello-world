package com.sbna.vendorportal.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbna.vendorportal.dto.Material;
import com.sbna.vendorportal.pojo.MaterialForm;

public interface MaterialDao {

	List<Material> getMaterials();

	List<Material> getMaterialsByLang(String lang);
	
	List<Material> getMaterialCode(String materialCode);

	Material get(Long id);

	Material get(String materialCode);

	void save(Material material);

	void delete(Long id);
	
	void bulkPersist(List<Material> entities);

	void handleUniqueViolation(List<Material> materials);
	
	List<MaterialForm> getMaterialFormByLang(String lang);
}
