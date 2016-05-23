package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.Status;

public interface StatusDao {

    Status get(Long id);

    Status get(String name);

    Status getActiveStatus();
}
