package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.Pcr;
import com.sbna.vendorportal.dto.Status;

public interface PcrDao {

	List<Pcr> getPcrByGroupAndLang(long purchaseGroupId, String lang);

	Pcr update(Pcr chargeChanges);

	List<Pcr> getPcrByPcrNo(long id);

	void save(Pcr pcr);
 
	Pcr get(Long id);

	Status getStatusId(final String statusDesc, final String language);
}
