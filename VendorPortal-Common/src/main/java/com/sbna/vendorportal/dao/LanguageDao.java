package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.Language;


public interface LanguageDao {
	Language get(Long id);

	Language get(String name);
}


