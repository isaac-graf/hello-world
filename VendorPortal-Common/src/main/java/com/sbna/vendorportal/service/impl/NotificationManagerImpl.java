package com.sbna.vendorportal.service.impl;

import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.dao.UserDao;
import com.sbna.vendorportal.dto.MailItem;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.service.MailItemManager;
import com.sbna.vendorportal.service.NotificationManager;
import com.sbna.vendorportal.util.Utils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "notificationManager")
@Transactional(propagation = Propagation.REQUIRED)
public class NotificationManagerImpl implements NotificationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationManagerImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Utils utils;

    private VelocityEngine velocityEngine;

    @Autowired
    private Environment env;

    @Autowired
    private UserDao userDao;

    @Autowired
    MailItemManager mailItemManager;

    public NotificationManagerImpl() {
        try {
            velocityEngine = new VelocityEngine();
            // This sets Velocity to use our own logging implementation so we can use SLF4J
            velocityEngine.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, LOGGER);
            velocityEngine.setProperty("resource.loader", "class");
            velocityEngine.setProperty("class.resource.loader.description", "Classpath Loader");
            velocityEngine.setProperty("class.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // Init
            velocityEngine.init();
        } catch (Exception e) {
            LOGGER.error("Could not initialise velocity engine!", e);
        }
    }

    @Override
    public void sendPasswordResetEmail(User user, String resetLink) throws MessagingException {
        final String emailTitle = "Reset Password - Vendor Portal";

        Map<String, Object> map = getBaseMap();
        map.put("toUser", user);
        map.put("toUserName", user.getDisplayName());
        map.put("resetLink", utils.getAbsoluteUrl(resetLink));
        String emailBody = renderTemplate(map, "reset-password.vm");
        String fromMail = env.getRequiredProperty(ProjectConfig.CONFIG_APP_EMAIL_ADDRESSES_PROPERTY);
        LOGGER.debug("Application Email Address :{}", fromMail);

        sendEmail(
                fromMail,
                new String[]{user.getEmail()}, null, new String[]{},
                emailTitle, emailBody, false);

    }

    @Override
    public void sendRFQorPOEmail(User user, String resetLink, boolean isRFQ, String itemnum, String date, boolean isReminder) throws MessagingException {
        try {
            String emailTitle;
            if (isRFQ) {
                emailTitle = "RFQ Created for " + itemnum + " & " + date;
            } else {
                emailTitle = "PO Created for " + itemnum + " & " + date;
                if (isReminder) {
                    emailTitle = "Gentle Reminder:" + emailTitle;
                }
            }

            Map<String, Object> map = getBaseMap();
            map.put("toUser", user);
            if (null != user.getProfile()
                    && null != user.getProfile().getFullName()) {
                map.put("toUserName", user.getProfile().getFullName());
            } else {
                map.put("toUserName", " ");
                LOGGER.error(
                        "Failed to retrieve full name from profile for vendor ",
                        user.getUsrName());
            }
            map.put("resetLink", utils.getAbsoluteUrl(resetLink));

            String emailBody;
            if (isRFQ) {
                emailBody = renderTemplate(map, "rfq-email"
                        + ".vm");
            } else {
                emailBody = renderTemplate(map, "po-email"
                        + ".vm");
            }

            String fromMail = env.getRequiredProperty(ProjectConfig.CONFIG_APP_EMAIL_ADDRESSES_PROPERTY);
            LOGGER.debug("Application Email Address :{}", fromMail);

            sendEmail(
                    fromMail,
                    new String[]{user.getEmail()}, null, new String[]{},
                    emailTitle, emailBody, false);
        } catch (MessagingException e) {
            LOGGER.error("Error when processing notification " + e.toString());
            throw new MessagingException();
        } catch (Exception e) {
            LOGGER.error("Error when processing Message " + e.toString());
        }

    }

    @Override
    public void sendEmailsToBuyersFrmVendor(String fromEmail, String[] toEmailArr, String mailSubject) {
        captureMailLog(fromEmail, toEmailArr, null, mailSubject, "");
    }

    @Override
    public void sendEmailsToVendorFrmBuyer(String fromEmail, String[] toEmail, String mailSubject, String mailBody) {

        try {
            sendEmail(fromEmail, toEmail, null, null, mailSubject, mailBody);
        } catch (MessagingException e) {
            LOGGER.info(e.getMessage());
        }

    }

    public void sendEmail(String from, String[] to, String[] cc, String[] bcc, String subject, String body) throws MessagingException {

        sendEmail(from, to, cc, bcc, subject, body, true);
    }

    @Override
    public void captureMailLog(String from, String[] to, String[] bcc,
            String subject, String body) {
        try {

            //            User fromUser = profileManager.getUserByEmail(from);
            if (to != null && to.length > 0) {
                for (String toMail : to) {
                    User toUser = userDao.get(toMail);
                    if (toUser != null) {
                        MailItem mailItem = createMailItem(subject, body, from, toUser);
                        mailItemManager.save(mailItem);
                    } else {
                        LOGGER.warn("No matching User for Email[{}] in Data Base", toMail);
                    }
                }
            }

            if (bcc != null && bcc.length > 0) {
                for (String bccMail : bcc) {
                    User bccUser = userDao.get(bccMail);
                    if (bccUser != null) {
                        MailItem mailItem = createMailItem(subject, body, from,
                                bccUser);
                        mailItemManager.save(mailItem);
                    } else {
                        LOGGER.warn("No matching User for Email[{}] in Data Base", bccMail);

                    }

                }
            }

        } catch (Exception e) {
            LOGGER.error("Error in saving mail item to database. " + e.getMessage(), e);
        }
    }

    private MailItem createMailItem(String subject, String body, String mailFrom,
            User toUser) {
        MailItem mailItem = new MailItem();
        mailItem.setMailRead(false);
        mailItem.setMessage(body);
        mailItem.setRowCreatedBy("Application");
        mailItem.setRowUpdatedBy("Application");
        mailItem.setSubject(subject);
        mailItem.setMailFrom(mailFrom);
        mailItem.setUsr2(toUser);
        return mailItem;
    }

    // Automatically add the header and footer to the email
    public void sendEmail(String from, String[] to, String[] cc, String[] bcc, String subject, String body, boolean captureLog) throws MessagingException {

        sendEmail(from, to, cc, bcc, subject, body, captureLog, true);

    }

    @Override
    public void sendEmail(String from, String[] to, String[] cc,
            String[] bcc, String subject, String body, boolean captureLog, boolean addSignature) {
        try {
            if (captureLog) {
                captureMailLog(from, to, bcc, subject, body);
            }

            Map<String, Object> context = getBaseMap();

            StringBuilder completeBody = new StringBuilder();
            if (addSignature) {
                //        completeBody.append(renderTemplate(context, "include/header.vm"));
                body += ProjectConfig.EMAIL_SIGNATURE;
                body += ProjectConfig.AUTO_GENERATED_MAIL;
                body += ProjectConfig.DO_NOT_REPLY_MSG;
            }
            completeBody.append(body);
            //        completeBody.append(renderTemplate(context, "include/footer.vm"));

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = null;

            messageHelper = new MimeMessageHelper(message, true);
            if (to != null && to.length > 0) {
                messageHelper.setTo(to);
            }
            if (cc != null) {
                Address[] ccAddresses = new Address[cc.length];
                for (int i = 0; i < cc.length; i++) {
                    ccAddresses[i] = new InternetAddress(cc[i]);
                }
                message.addRecipients(Message.RecipientType.CC, ccAddresses);
            }
            if (bcc != null) {
                Address[] bccAddresses = new Address[bcc.length];
                for (int i = 0; i < bcc.length; i++) {
                    bccAddresses[i] = new InternetAddress(bcc[i]);
                }
                message.addRecipients(Message.RecipientType.BCC, bccAddresses);
            }
            if (from != null) {
                messageHelper.setFrom(from);
            }
            messageHelper.setSubject(subject);
            messageHelper.setText(completeBody.toString(), true);

            LOGGER.info("Sending email to {}, {}, {}", Arrays.toString(to), Arrays.toString(cc), Arrays.toString(bcc));
            javaMailSender.send(messageHelper.getMimeMessage());
            LOGGER.info("Mail sent successfully");
        } catch (Exception e) {
            LOGGER.error("----------------ERROR SENDING THE MAIL----------------" + e.getMessage());
        }
    }

    public String renderTemplate(Map<String, Object> map, String template) {
        // build our context map
        VelocityContext velocityContext = new VelocityContext();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            velocityContext.put(entry.getKey(), entry.getValue());
        }

        // Try the renderTemplate, log any problems
        final Writer writer = new StringWriter();
        try {
            velocityEngine.mergeTemplate(template, velocityContext, writer);
        } catch (Exception e) {
            LOGGER.error("Could not renderTemplate template {}", "email", e);
        }

        return writer.toString();
    }

    private Map<String, Object> getBaseMap() {
        SimpleDateFormat copywriteDate = new SimpleDateFormat("yyyy");

        Map<String, Object> map = new HashMap<>();
        map.put("utils", utils);
        map.put("basehref", env.getRequiredProperty(ProjectConfig.CONFIG_BASE_HREF_PROPERTY));
        map.put("copyrightDate", copywriteDate.format(new Date()));

        return map;
    }

    /**
     *
     * @param subject
     * @param message
     * @param from
     * @param to
     */
    @Override
    public void submitQuote(String subject, String message, String from, String[] to) {
//        try {
        //Vendor Ack mail - not necessary as per email templates shared

        //Buyer purchase group quote submit mail
        captureMailLog(from, to, null, subject, message);
        //sendEmail(from, to, null, null, subject, message);
//        } catch (MessagingException e) {
//            LOGGER.error("Could not send email", e);
//        }
    }
}
