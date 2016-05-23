package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.Module;
import com.sbna.vendorportal.pojo.MasterDataHelper;
import com.sbna.vendorportal.pojo.MenuItem;

import java.util.List;

public interface ModuleDao {

    List<MasterDataHelper> getAllStatus(String locale, String moduleUrlRole);
    
    List<Module> getAllModules();

	List<MenuItem> getMenuItems(String locale, List<String> roleList);
	
	List<Module> getModuleByLang(String locale);
}
