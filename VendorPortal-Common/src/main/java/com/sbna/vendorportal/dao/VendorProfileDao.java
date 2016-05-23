package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.dto.VendorProfile;

public interface VendorProfileDao {

    void setProfile(final User user, final String lang, String createdBy, String updatedBy);

    VendorProfile fetchVendorProfile(final long id);

    List<VendorProfile> getWaitingRequets();

    void save(VendorProfile vendorProfile);
}
