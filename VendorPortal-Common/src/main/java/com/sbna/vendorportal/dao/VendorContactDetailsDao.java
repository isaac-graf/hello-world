package com.sbna.vendorportal.dao;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.VendorContactDetails;

public interface VendorContactDetailsDao {

    List<VendorContactDetails> getVendorContactDetails(Map<String, String> requestParams);

    List<VendorContactDetails> getVendorContactInfofromDB(final long vendorCode);

//	void saveBulkDetails(List<VendorContactDetails> vendorContactDetails);
}
