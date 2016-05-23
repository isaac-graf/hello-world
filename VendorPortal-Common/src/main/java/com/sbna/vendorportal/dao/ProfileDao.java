package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.Profile;

public interface ProfileDao {

	List<Profile> getProfiles();

	Profile get(Long id);

	Profile get(String username);

	void save(Profile profile);

	void delete(Long id);

	List<Profile> getProfiles(int start, int limit);

	void bulkPersist(List<Profile> entities);

	void handleUniqueViolation(List<Profile> profiles);

}
