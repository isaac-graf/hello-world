package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.RefDocs;

public interface RefDocsDao {

    RefDocs get(Long id);

    List<RefDocs> getRefDocs(Long id, Long refId, String refModule);

    void save(RefDocs refDocs);
    void delete(Long id);

    List<RefDocs> getRefDocs(List<Long> refIds, String refModule);
    
    void bulkPersist(List<RefDocs> refDocs);

	List<RefDocs> getRefDocs(Long id, Long refId, String refModule,
			String grpId);
}
