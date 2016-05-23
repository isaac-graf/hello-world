package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.PlantDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.Plant;
import com.sbna.vendorportal.dto.PlantDesc;
import com.sbna.vendorportal.dto.Plant_;
import com.sbna.vendorportal.pojo.MasterDataHelper;
import javax.persistence.Query;

@Repository(value = "plantDao")
@Transactional(propagation = Propagation.MANDATORY)
public class PlantDaoImpl extends AbstractHibernateDao<Plant> implements PlantDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlantDaoImpl.class);

    @Override
    public List<Plant> getPlants() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Plant> criteria = builder.createQuery(Plant.class);
        Root<Plant> plantRoot = criteria.from(Plant.class);
        criteria.select(plantRoot);
        return getEntityManager().createQuery(criteria).getResultList();
    }

    public Plant get(String plantCode) {

        // TODO deleted stuff
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Plant> criteria = builder.createQuery(Plant.class);
        Root<Plant> plantRoot = criteria.from(Plant.class);
        criteria.where(builder.equal(plantRoot.get(Plant_.plantCode), plantCode));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Plant> getPlantsByLang(String lang) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Plant> query = builder.createQuery(Plant.class);

        Root<Plant> plants = query.from(Plant.class);
        Join<Plant, PlantDesc> plantDescs = plants.join("plantDescs");
        Join<PlantDesc, Language> languages = plantDescs.join("lang");
        List<Predicate> conditions = new ArrayList<Predicate>();
        conditions.add(builder.equal(languages.get("langKey"), lang));

        TypedQuery<Plant> typedQuery = getEntityManager().createQuery(
                query.select(plants).where(conditions.toArray(new Predicate[]{}))
                .orderBy(builder.asc(plants.get("plantCode"))).distinct(true));
        return typedQuery.getResultList();
    }

    /**
     * @author Phaneendra
     * @param locale
     * @return
     */
    public List<MasterDataHelper> getAllPlants(String locale) {
        Query query = getEntityManager().createQuery("SELECT new com.sbna.vendorportal.pojo.MasterDataHelper"
                + "(p.plantCode, p.plantCode + ' - ' + pd.plantDesc) "
                + "FROM Plant p JOIN p.plantDescs pd "
                + "JOIN pd.lang l "
                + "WHERE l.langKey = :locale ")
                .setParameter("locale", locale);
        return query.getResultList();
    }

    @Override
    public Plant get(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Plant plant) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void handleUniqueViolation(List<Plant> plants) {
        List<Plant> dbplants = new ArrayList<Plant>();
        dbplants = getPlants();
        for (Plant plant : plants) {
            for (Plant bdplant : dbplants) {
                if (plant.getPlantCode().equals(bdplant.getPlantCode())) {
                    plant.setId(bdplant.getId());
                    plant.setPlantDescs(bdplant.getPlantDescs());
                }

            }

        }
    }

}
