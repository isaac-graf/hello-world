package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.ModuleDao;
import com.sbna.vendorportal.dto.Language;
import com.sbna.vendorportal.dto.Module;
import com.sbna.vendorportal.dto.ModuleDesc;
import com.sbna.vendorportal.pojo.MasterDataHelper;
import com.sbna.vendorportal.pojo.MenuItem;

@Repository(value = "moduleDao")
@Transactional(propagation = Propagation.MANDATORY)
public class ModuleDaoImpl extends AbstractHibernateDao<Module> implements ModuleDao {

    public List<MasterDataHelper> getAllStatus(String locale, String moduleUrlRole) {
        Query query = getEntityManager().createQuery("SELECT new com.sbna.vendorportal.pojo.MasterDataHelper"
                + "(s.statusDescEng, sd.statusDesc) "
                + "FROM Module m JOIN m.statuses s "
                + "JOIN s.statusDescs sd "
                + "JOIN sd.lang l "
                + "WHERE l.langKey = :locale "
                + "AND m.moduleUrlRole = :moduleUrlRole")
                .setParameter("locale", locale)
                .setParameter("moduleUrlRole", moduleUrlRole);
        return query.getResultList();
    }

    @Override
    public List<Module> getAllModules() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MenuItem> getMenuItems(String locale, List<String> roleList) {
        Query query = getEntityManager().createQuery("SELECT DISTINCT new com.sbna.vendorportal.pojo.MenuItem"
                + "(mod_desc.moduleDesc, mod.menuIcon, mod.moduleUrl, mod.menuOrder) "
                + "FROM Module mod JOIN  mod.roles rol "
                + "JOIN mod.moduleDescs mod_desc "
                + "JOIN mod_desc.lang l "
                + "WHERE l.langKey = :locale "
                + "AND rol.roleDescEng IN :roleList "
                + "order by mod.menuOrder");
        query.setParameter("locale", locale);
        query.setParameter("roleList", roleList);
        return query.getResultList();
    }
    
    @Override
	public List<Module> getModuleByLang(String lang) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Module> query = builder.createQuery(Module.class);    
        
        Root<Module> module = query.from(Module.class);
        Join<Module, ModuleDesc> moduleDescs = module.join("moduleDescs");
        Join<ModuleDesc, Language> languages = moduleDescs.join("lang");
        List<Predicate> conditions = new ArrayList<Predicate>();
        conditions.add(builder.equal(languages.get("langKey"), lang));

        TypedQuery<Module> typedQuery = getEntityManager().createQuery(
                query.select(module).where(conditions.toArray(new Predicate[]{}))
                .orderBy(builder.asc(module.get("moduleDesc"))).distinct(true));
        return typedQuery.getResultList();
	}

}
