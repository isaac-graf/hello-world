package com.sbna.vendorportal.util.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import com.sbna.vendorportal.config.HttpConfig;
import com.sbna.vendorportal.config.ProjectConfig;
import com.sbna.vendorportal.dto.Status;
import com.sbna.vendorportal.dto.User;
import com.sbna.vendorportal.util.Utils;

@Component(value = "utils")
public class UtilsImpl implements Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsImpl.class);

    @Inject
    private Environment env;

    private final String TOKEN_TIMEOUT = "2";
    private final String REQUEST_TIMEOUT = "30";

    public String getAbsoluteUrl(String uri) {
        // remove the leading slash
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        return env.getRequiredProperty(ProjectConfig.CONFIG_BASE_HREF_PROPERTY)
                + uri;
    }

    public String getProperty(String key) {
        return env.getProperty(key);
    }

    public String getRequiredProperty(String key) {
        return env.getProperty(key);
    }

    /**
     * * Token Utils
     *
     * @param user
     * @return
     */
    public String createToken(User user) {
        String tokenTimeout = getProperty(ProjectConfig.CONFIG_TOKEN_TIMEOUT);
        long expires = System.currentTimeMillis() + 1000L * 60 * Integer.parseInt(tokenTimeout != null ? tokenTimeout.trim()
                : TOKEN_TIMEOUT);
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(user.getId());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        tokenBuilder.append(":");
        tokenBuilder.append(computeSignature(user, expires));
        return tokenBuilder.toString();
    }

    @Override
    public String createPasswordResetToken(User user) {
        String tokenTimeout = getProperty(ProjectConfig.CONFIG_TOKEN_TIMEOUT);
        LOGGER.debug("tokenTimeout :{}", tokenTimeout);
        long expires = System.currentTimeMillis()
                + 1000L
                * 60
                * Integer.parseInt(tokenTimeout != null ? tokenTimeout.trim()
                                : TOKEN_TIMEOUT);
        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append(user.getEmail());
        tokenBuilder.append(":");
        tokenBuilder.append(expires);
        return new String(Base64.encodeBase64(tokenBuilder.toString()
                .getBytes()));
    }

    public String computeSignature(User user, long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(user.getId());
        signatureBuilder.append(":");
        signatureBuilder.append(expires);
        signatureBuilder.append(":");
        signatureBuilder.append(user.getUsrPwd());
        signatureBuilder.append(":");
        signatureBuilder.append(getProperty(ProjectConfig.CONFIG_MAGIX_KEY));
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(signatureBuilder.toString()
                .getBytes())));
    }

    public String getUserIdFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }
        String[] parts = authToken.split(":");
        String userId = parts[0];
        return userId;
    }

    public boolean validateToken(String authToken, User user) {
        String[] parts = authToken.split(":");
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];
        if (expires < System.currentTimeMillis()) {
            return false;
        }
        return signature.equals(computeSignature(user, expires));
    }

    /**
     * * HTTP Utils
     *
     * @param urlParams
     * @return
     */
    public String buildQueryStringURL(Map<String, String> urlParams) {
        urlParams.put("locale", getLocale());
        StringBuilder urlQuery = new StringBuilder();
        String separator = "?";
        for (Map.Entry<String, String> entry : urlParams.entrySet()) {
            if (entry.getValue() != null) {
                urlQuery.append(separator);
                urlQuery.append(urlEncode(entry.getKey()));
                urlQuery.append('=');
                urlQuery.append(urlEncode(entry.getValue()));
                separator = "&";
            }
        }
        return urlQuery.toString();
    }

    public String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

    public String urlDecode(String value) {
        try {
            return URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

    /**
     *
     * @param dataURI
     * @param requestParams
     * @param currentUser
     * @return
     * @throws IOException
     */
    @Override
    public String doGet(String dataURI, Map<String, String> requestParams,
            User currentUser) throws IOException {
        LOGGER.info("******************* DO GET CALL ADAPTER SAP START *******************" + dataURI);
        String requestURI = buildRequestURI(dataURI, currentUser);
        requestURI += requestParams != null ? buildQueryStringURL(requestParams)
                : "";
		LOGGER.error("debug.. requestURI is " + requestURI);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestURI);
        HttpResponse response = null;
        setHeaders(httpGet, currentUser);
        String result = executeHttpCall(httpClient, httpGet, response);
        LOGGER.info("******************* DO GET CALL ADAPTER SAP END *******************" + dataURI);
        return urlEncode(result);
    }

    /**
     * 
     * @param dataURI
     * @param data
     * @param currentUser
     * @return
     * @throws IOException 
     */
    @Override
    public String doPost(String dataURI, String data, User currentUser)
            throws IOException {
        LOGGER.info("******************* DO POST CALL ADAPTER SAP START *******************" + dataURI);
        String requestURI = buildRequestURI(dataURI, currentUser);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestURI);
        HttpResponse response = null;
        setHeaders(httpPost, currentUser);
        StringEntity input = new StringEntity(data);
        httpPost.setEntity(input);
        String result = executeHttpCall(httpClient, httpPost, response);
        LOGGER.info("******************* DO POST CALL ADAPTER SAP END *******************" + dataURI);
        return urlEncode(result);
    }

    public void setHeaders(HttpRequestBase httpRequestBase, User currentUser) {
        httpRequestBase.setHeader(HttpHeaders.USER_AGENT,
                HttpConfig.DEFAULT_USER_AGENT);
        httpRequestBase
                .setHeader(HttpHeaders.CONTENT_TYPE, HttpConfig.APP_JSON);
        httpRequestBase.setHeader(
                getProperty(ProjectConfig.CONFIG_X_AUTH_TOKEN),
                createToken(currentUser));
        httpRequestBase.setConfig(requestConfigWithTimeout());
    }

    public String buildRequestURI(String dataURI, User currentUser) {
        String requestURI = getProperty(ProjectConfig.CONFIG_ENDPOINT_BASE_HREF_PROPERTY)
                + getProperty(ProjectConfig.CONFIG_ENDPOINT_CONTEXT_PROPERTY)
                + dataURI + "/" + currentUser.getNonAdminRoleName();
        return requestURI;
    }

    public String executeHttpCall(CloseableHttpClient httpClient,
            HttpRequestBase httpRequestBase, HttpResponse response)
            throws IOException {
        try {
            // Execute the method.
            response = httpClient.execute(httpRequestBase);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new IllegalStateException("API Call failed: "
                        + response.getStatusLine());
            }
            return parseResponse(response);
        } catch (ClientProtocolException e) {
            throw new ClientProtocolException();
        } catch (ConnectException e) {
			LOGGER.error("Error making connection " + e.getMessage());
            throw new ConnectException();
        } finally {
            // Release the connection.
            httpClient.close();
        }
    }

    public String parseResponse(HttpResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (response.getEntity().getContent())));
        StringBuilder buf = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            buf.append(output);
        }
        String content = buf.toString();
        return content;
    }

    public RequestConfig requestConfigWithTimeout() {
        String reqTimeout = getProperty(ProjectConfig.CONFIG_REQUEST_TIMEOUT);
        Integer timeout = Integer.parseInt(reqTimeout != null ? reqTimeout
                .trim() : REQUEST_TIMEOUT) * 1000;
        return RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(timeout).setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout).build();
    }

    @Override
    public String encodeLoginPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public boolean matchesEncodedPassword(String rawPassword,
            String encodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean isUserActive(Status status) {
        LOGGER.debug("User status :{}", status);
        if (status != null) {
            if (ProjectConfig.STATUS_ACTIVE.equalsIgnoreCase(status
                    .getStatusDescEng())) {
                return true;
            }
        }
        return false;
    }

    public String getLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        return locale.getLanguage();
    }

    @Override
    public Date parseDate(String dateStr) {
        return parseDate(dateStr, "MM-dd-yyyy");
    }

    @Override
    public Date parseDate(String dateStr, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public String changeDateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    @Override
    public Date stringToDateConvr(String timestamp, String format) {
        // SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // Date d = sdf.parse("Mon May 27 11:46:15 IST 2013");
        Date d = new Date();
        try {
            d = sdf.parse(timestamp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void saveFile(String dirPath, String filename, byte[] refDocObj, String[][] coverPageContent) throws Exception {
        PDDocument coverDoc = generateCoverPdfDoc(coverPageContent);
        InputStream contentStream = new ByteArrayInputStream(refDocObj);
        mergePdfDocs(coverDoc, contentStream, dirPath + File.separator + filename);
    }

    @Override
    public boolean isSameDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());

        return (todayCal.get(Calendar.ERA) == cal.get(Calendar.ERA)
                && todayCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
                && todayCal.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR));
    }

    @Override
    public boolean isSameYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());

        return (todayCal.get(Calendar.ERA) == cal.get(Calendar.ERA)
                && todayCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR));
    }

    @Override
    public String decodeBase64(String encodedStr) {
        byte[] decodeByteArr = Base64.decodeBase64(encodedStr);
        String decodedStr = new String(decodeByteArr);
        return decodedStr;
    }

    @Override
    public String encodeBase64(String decodedStr) {
        byte[] decodeByteArr = Base64.encodeBase64(decodedStr.getBytes());
        String encodedStr = new String(decodeByteArr);
        return encodedStr;
    }

    @Override
    public PDDocument generateCoverPdfDoc(String[][] tableContent) throws IOException {

        PDDocument doc;
        PDPageContentStream pageContentStream = null;
        try {
            doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            pageContentStream = new PDPageContentStream(doc, page);

            float y = 700;
            float margin = 100;
            final int cols = tableContent[0].length;
            final float rowHeight = 20f;
            final float tableWidth = page.findMediaBox().getWidth() - (2 * margin);
            final float colWidth = tableWidth / (float) cols;
            final float cellMargin = 5f;

            pageContentStream.setFont(PDType1Font.HELVETICA, 10);

            float xCoord = margin + cellMargin;
            float yCoord = y - 15;
            for (int i = 0; i < tableContent.length; i++) {
                for (int j = 0; j < tableContent[i].length; j++) {
                    String text = tableContent[i][j];
                    pageContentStream.beginText();

                    pageContentStream.moveTextPositionByAmount(xCoord, yCoord);

                    if (text != null) {
						pageContentStream.drawString(text);
					}
					pageContentStream.endText();
                    xCoord += colWidth;
                }
                yCoord -= rowHeight;
                xCoord = margin + cellMargin;
            }
        } finally {
            if (pageContentStream != null) {
                pageContentStream.close();
            }
        }

        return doc;
    }

    @Override
    public void mergePdfDocs(PDDocument coverDoc, InputStream contentStream, String fileName) throws Exception {
    	InputStream firstPageStream = null;
        try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			coverDoc.save(baos);
			PDFMergerUtility mergerUtility = new PDFMergerUtility();
			firstPageStream = new ByteArrayInputStream(baos.toByteArray());
			mergerUtility.addSource(firstPageStream);
			mergerUtility.addSource(contentStream);
			mergerUtility.setDestinationFileName(fileName);
			mergerUtility.mergeDocuments();
		} finally{
			try {
				if(coverDoc != null){
					coverDoc.close();
				}
				if(contentStream != null){
					contentStream.close();
				}
				if(firstPageStream != null){
					firstPageStream.close();
				}
			} catch (Exception e) {
				LOGGER.error("Error in closing streams while merging pdf docs", e);
			}
		}
    }
}
