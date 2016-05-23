package com.sbna.vendorportal.service;

import java.util.Map;

import javax.mail.MessagingException;

import com.sbna.vendorportal.dto.User;

public interface NotificationManager {

    void sendPasswordResetEmail(User user, String resetLink) throws MessagingException;

    // to allow for testing, this method is in the interface
    String renderTemplate(Map<String, Object> map, String template);

    // to allow for testing, this method is in the interface
    void sendEmail(String from, String[] to, String[] cc, String[] bcc, String subject, String body) throws MessagingException;

    void submitQuote(String subject, String message, String from, String[] to);

    void sendEmailsToBuyersFrmVendor(String fromEmail, String[] toEmailArr, String mailSubject);

    void sendEmailsToVendorFrmBuyer(String fromEmail, String[] toEmail, String mailSubject, String mailBody);

    void captureMailLog(String from, String[] to, String[] bcc, String subject,
            String body);

    void sendRFQorPOEmail(User user, String resetLink, boolean isRFQ, String itemnum, String Date, boolean isReminder) throws MessagingException;

    void sendEmail(String from, String[] to, String[] cc, String[] bcc,
            String subject, String body, boolean captureLog,
            boolean addSignature);

}
