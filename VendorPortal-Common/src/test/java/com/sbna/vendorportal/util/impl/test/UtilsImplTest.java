package com.sbna.vendorportal.util.impl.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.sbna.vendorportal.dto.AuditTrail;
import com.sbna.vendorportal.dto.BaseModel;
import com.sbna.vendorportal.dto.Profile;
import com.sbna.vendorportal.dto.Role;
import com.sbna.vendorportal.dto.Status;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.util.Utils;
import com.sbna.vendorportal.util.impl.UtilsImpl;

public class UtilsImplTest {

	@Test
	public void test_createAuditTrails() throws IllegalArgumentException, IllegalAccessException{
		System.out.println("Testing createAuditTrails...");
		User oldUser = getOldUser();

		User newUser = getNewUser();

		Utils utils = new UtilsImpl();
//		List<AuditTrail> auditTrails = utils.createAuditTrails("Usr", null, oldUser, newUser, "updated User", "sapTransCode");
//		System.out.println("auditTrails :"+auditTrails);
	}

	private User getNewUser() {
		User oldUser = new User();
		oldUser.setId(2L);
		oldUser.setCreated(new Date());
		oldUser.setUpdated(new Date());
		oldUser.setEmail("new@test_domain.com");
		Status status = new Status();
		status.setId(2L);
		status.setStatusDescEng("inactive");
		oldUser.setStatus(status );
		Profile profile= new Profile();
		profile.setId(2L);
		profile.setFullName("new_fullName");
		oldUser.setProfile(profile);
		oldUser.setUsrLoginFailCnt(1);
		oldUser.setUsrName("newUser");
		Set<Role> roles = new HashSet<Role>();
		Role role1=new Role();
		role1.setId(3L);
		role1.setRoleDescEng("Vendor");
		roles.add(role1);
		Role role2=new Role();
		role2.setId(1L);
		role2.setRoleDescEng("Admin");
		roles.add(role2);
		oldUser.setRoles(roles );
		return oldUser;
	}

	private User getOldUser() {
		User oldUser = new User();
		oldUser.setId(1L);
		oldUser.setCreated(new Date());
		oldUser.setUpdated(new Date());
		oldUser.setEmail("old@test_domain.com");
		Status status = new Status();
		status.setId(1L);
		status.setStatusDescEng("active");
		oldUser.setStatus(status );
		Profile profile= new Profile();
		profile.setId(1L);
		profile.setFullName("fullName");
		oldUser.setProfile(profile);
		oldUser.setUsrLoginFailCnt(2);
		oldUser.setUsrName("oldUser");
		Set<Role> roles = new HashSet<Role>();
		Role role1=new Role();
		role1.setId(1L);
		role1.setRoleDescEng("Admin");
		roles.add(role1);
		
		Role role2=new Role();
		role2.setId(2L);
		role2.setRoleDescEng("Buyer");		
		roles.add(role2);
		oldUser.setRoles(roles );
		return oldUser;
	}
	
	
	@Test
	public void test_isAssignable(){
		User user = new User();
		if(BaseModel.class.isAssignableFrom(user.getClass())){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		
	}
}
