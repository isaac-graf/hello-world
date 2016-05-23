package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.Plant;
import com.sbna.vendorportal.pojo.MasterDataHelper;

public interface PlantDao {

    List<Plant> getPlants();

    List<Plant> getPlantsByLang(String lang);

    List<MasterDataHelper> getAllPlants(String locale);

    Plant get(Long id);

    Plant get(String plantCode);

    void save(Plant plant);

    void delete(Long id);

    void bulkPersist(List<Plant> entities);

    void handleUniqueViolation(List<Plant> plants);
}
