package com.sbna.vendorportal.dao;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.VendorDetails;

public interface VendorDetailsDao {

    List<VendorDetails> getVendorDetails(Map<String, String> requestParams);

    VendorDetails getVendorDetailFromDB(final long vendorCode);

    List<VendorDetails> getPendingRequests(final String lang);

    VendorDetails get(final Long id);

    void save(VendorDetails vendorDetails);

}
