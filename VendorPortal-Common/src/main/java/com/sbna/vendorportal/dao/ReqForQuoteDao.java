package com.sbna.vendorportal.dao;

import com.sbna.vendorportal.dto.ReqForQuote;
import java.util.List;

public interface ReqForQuoteDao {

    void save(ReqForQuote reqForQuote);

    ReqForQuote get(Long id);

    List getRfqIdsByVendor(Long vendorId);

    List getRfqIds();

    List getCollecNos();

    List<ReqForQuote> getByRfqNoVendorCode(String rfqNo, String vendorCode);

    void bulkPersist(List<ReqForQuote> rfqs);

    List<ReqForQuote> getRFQQuotes();

    void handleUniqueViolation(List<ReqForQuote> entities);
}
