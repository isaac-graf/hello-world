package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.Role;

public interface RoleDao {
	Role get(Long id);

	Role get(String name);
}
