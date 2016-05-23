package com.sbna.vendorportal.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.sbna.vendorportal.dto.AuditTrail;
import com.sbna.vendorportal.dto.BaseModel;
import com.sbna.vendorportal.dto.Status;
import com.sbna.vendorportal.dto.User;

public interface Utils {

    String getAbsoluteUrl(String uri);

    String getProperty(String key);

    String getRequiredProperty(String key);

    String getLocale();

    /**
     * *
     * Token Utils
     *
     */
    String createToken(User user);

    String computeSignature(User user, long expires);

    String getUserIdFromToken(String authToken);

    boolean validateToken(String authToken, User user);

    /**
     * *
     * HTTP Utils
     *
     * @param urlParams
     * @return 
     */
    String buildQueryStringURL(Map<String, String> urlParams);

    String urlEncode(String value);

    String urlDecode(String value);

    String doGet(String dataURI, Map<String, String> requestParams, User currentUser)
            throws IOException;

    String doPost(String dataURI, String data, User currentUser)
            throws IOException;

    String encodeLoginPassword(String password);

    String createPasswordResetToken(User user);

    boolean matchesEncodedPassword(String rawPassword, String encodedPassword);

    boolean isUserActive(Status status);

//    List<AuditTrail> createAuditTrails(String auditTable,
//            BaseModel oldAuditable, BaseModel newAuditable, String auditDesc,
//            String sapTransCode, String rowCreatedBy, String rowUpdatedBy) throws IllegalArgumentException,
//            IllegalAccessException;

    Date parseDate(String dateStr);

    String changeDateFormat(Date date, String format);

    Date stringToDateConvr(String timestamp, String format);

    void saveFile(String dirPath, String filename, byte[] refDocObj, String[][] coverPageContent) throws Exception;

    Date parseDate(String dateStr, String format);

    boolean isSameDate(Date date);

    boolean isSameYear(Date date);
    
    String decodeBase64(String encodedStr);
    
    String encodeBase64(String decodedStr);

	PDDocument generateCoverPdfDoc(String[][] content) throws IOException;

	void mergePdfDocs(PDDocument coverDoc, InputStream contentStream, String fileName)
			throws Exception;
}
