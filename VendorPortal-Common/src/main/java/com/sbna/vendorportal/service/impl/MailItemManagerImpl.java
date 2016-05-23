package com.sbna.vendorportal.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.config.SecurityConfig;
import com.sbna.vendorportal.dao.MailItemDao;
import com.sbna.vendorportal.dao.UserDao;
import com.sbna.vendorportal.dto.MailItem;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.dto.exception.UserNotFoundException;
import com.sbna.vendorportal.pojo.MailItemHelper;
import com.sbna.vendorportal.service.MailItemManager;
import com.sbna.vendorportal.service.NotificationManager;

@Service("mailItemManager")
@Transactional(propagation = Propagation.REQUIRED)
public class MailItemManagerImpl implements MailItemManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MailItemManagerImpl.class);

	@Autowired
	UserDao userDao;

	@Autowired
	MailItemDao mailItemDao;
	
	@Autowired
	NotificationManager notificationManager;	

	@Override
	public MailItem get(Long id) {
		return mailItemDao.get(id);
	}

	@Override
	public void save(MailItem mailItem) {
		mailItemDao.save(mailItem);

	}

	@Override
	public List<MailItem> getMailItems(String toUsername)
			throws UserNotFoundException {
		User user = userDao.get(toUsername);
		LOGGER.debug("Matching User for toUsername[{}] :{}", toUsername, user);

		if (user != null) {
			return getMailItems(user);
		} else {
			throw new UserNotFoundException(String.format(
					ProjectConfig.ERR_USERNAME_NOT_FOUND, toUsername));
		}

	}

	@Override
	public List<MailItem> getMailItems(User toUser) {
		return mailItemDao.getMailItems(toUser);
	}

	@Override
	public List<MailItemHelper> getMailItems(String toUsername, int start,
			int limit) throws UserNotFoundException {
		User user = userDao.get(toUsername);
		LOGGER.debug("Matching User for toUsername[{}] :{}", toUsername, user);

		if (user != null) {
			return getMailItems(user, start, limit);
		} else {
			throw new UserNotFoundException(String.format(
					ProjectConfig.ERR_USERNAME_NOT_FOUND, toUsername));
		}

	}

	@Override
	public List<MailItemHelper> getMailItems(User toUser, int start, int limit) {
		return mailItemDao.getMailItems(toUser, start, limit);
	}

	@Override
	public Long getUnReadMailCount(User toUser) {
		return mailItemDao.getUnReadMailCount(toUser);
	}

	@Override
	public void save(List<MailItem> mailItems) {
		mailItemDao.bulkPersist(mailItems);
		
	}
	
	@Override
	public void send(String fromMail, String toMails, String ccMails, String bccMails, String subject, String mailBody) throws Exception{
		String[] toMailArr = StringUtils.isBlank(toMails) ? null : toMails.split(",");
		String[] ccMailArr = StringUtils.isBlank(ccMails) ? null : ccMails.split(",");
		String[] bccMailArr = StringUtils.isBlank(bccMails) ? null : bccMails.split(",");
		notificationManager.sendEmail(fromMail, toMailArr, ccMailArr, bccMailArr, subject, mailBody, true, false);
	}
}
