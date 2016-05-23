package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.pojo.MasterDataHelper;
import com.sbna.vendorportal.pojo.UserForm;
import com.sbna.vendorportal.pojo.UserDetailDataHelper;

public interface UserDao {

    List<User> getUsers();

    User get(Long id);

    User get(String email);

    void save(User user);

    void delete(Long id);

    List<User> getUsers(int start, int limit);

    List<User> getVendors();

    List<User> getUsersByRoleName(String role);

    void bulkPersist(List<User> entities);

    List<MasterDataHelper> getAllUsernameFullname();

    void handleUniqueViolation(List<User> users);

    List<User> getVendorEmailByVendorId(Long vendorId);
    
    List<User> getActiveVendorEmailByVendorId(Long vendorId);

    List<String> getEmailByPurchaseGroupId(Long prchsGrpId);

    List<String> getAllEmailsByRole(String role);

    List<User> getBuyersByPrchdGroup(Long prchsGrpId);

    User getByUserName(String vendorCode);

    List<String> getEmailList();

    List<UserForm> getUserForms(Long curUserId);

    List<UserDetailDataHelper> getUserDetailsByRolename(String role);

	List<String> getActiveUserEmails();
}
