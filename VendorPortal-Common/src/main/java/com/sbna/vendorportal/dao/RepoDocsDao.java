package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.RepoDocs;
import com.sbna.vendorportal.pojo.RepoDocForm;

public interface RepoDocsDao {
	List<RepoDocForm> getAllDocs(String docType);
	
	void save(RepoDocs repoDocs);

	List<RepoDocs> getList(Long[] repoDocIdArr, String docType);
	
	RepoDocs get(Long id);

	void remove(RepoDocs repoDocs);
}
