package com.sbna.vendorportal.service;

import java.util.List;

import com.sbna.vendorportal.dto.MailItem;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.dto.exception.UserNotFoundException;
import com.sbna.vendorportal.pojo.MailItemHelper;

public interface MailItemManager {

	MailItem get(Long id);

	void save(MailItem mailItem);

	List<MailItem> getMailItems(String toUsername) throws UserNotFoundException;

	List<MailItem> getMailItems(User toUser);

	List<MailItemHelper> getMailItems(String toUsername, int start, int limit) throws UserNotFoundException;

	List<MailItemHelper> getMailItems(User toUser, int start, int limit);

	Long getUnReadMailCount(User toUser);

	void save(List<MailItem> mailItems);

	void send(String fromMail, String toMails, String ccMails, String bccMails,
			String subject, String mailBody) throws Exception;
}
