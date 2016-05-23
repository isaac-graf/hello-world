package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.MailItem;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.pojo.MailItemHelper;

public interface MailItemDao {

	MailItem get(Long id);

	void save(MailItem mailItem);

	List<MailItem> getMailItems(User toUser);

	List<MailItemHelper> getMailItems(User toUser, int start, int limit);

	Long getUnReadMailCount(User toUser);

	void bulkPersist(List<MailItem> mailItems);

}
