package com.sbna.vendorportal.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:project.properties"})
public class ProjectConfig {
	
    //In month for filter
    public static final int DATE_RANGE_MIN = -6;

    // Properties names
    public static final String CONFIG_BASE_HREF_PROPERTY = "config.basehref";
    public static final String CONFIG_CONTEXT_PROPERTY = "config.context";
    public static final String CONFIG_DEBUG_PROPERTY = "config.debug";
    public static final String CONFIG_ENVIRONMENT_PROPERTY = "config.environment";

    public static final String CONFIG_FILTER_SELECT_MULTI_PROPERTY = "config.filter.select.multi";

    public static final String CONFIG_ENDPOINT_BASE_HREF_PROPERTY = "config.endpoint.basehref";
    public static final String CONFIG_ENDPOINT_CONTEXT_PROPERTY = "config.endpoint.context";

    //Properties Token stuff
    public static final String CONFIG_X_AUTH_TOKEN = "config.xauthtoken";//if want to send in header
    public static final String CONFIG_AUTH_TOKEN = "config.authtoken";//if want to send in body
    public static final String CONFIG_MAGIX_KEY = "config.magixkey";//adding this magix key as salt
    public static final String CONFIG_TOKEN_TIMEOUT = "config.tokentimeout";//token timeout value in minutes
    public static final String CONFIG_REQUEST_TIMEOUT = "config.requesttimeout";//request timeout value in seconds
    public static final String CONFIG_MAX_LOGIN_ATTEMPTS = "config.maxNumTries";// Maximum
    // number
    // of
    // login
    // attemts
    // View param names

    public static final String CONFIG_ASN_SAP_POST_DATA = "config.asn.sap.post.data";
    public static final String CONFIG_DOC_DO_UPLOAD = "config.doc.doUpload";
    public static final String CONFIG_DOC_UPLOAD_DIR_PATH = "config.doc.upload.dir.path";

    public static final String BASE_HREF_PARAM = "basehref";
    public static final String ENVIRONMENT_PARAM = "environment";
    public static final String CONTEXT_PARAM = "context";
    public static final String DEBUG_PARAM = "debug";
    public static final String FILTER_SELECT_MULTI_PARAM = "selectMultiFilter";
    public static final String LOGGED_IN_USER_PARAM = "loggedInUser";
    public static final String LOGGED_IN_USER_DISPLAYNAME_PARAM = "userDisplayName";
    public static final String ROLES_LIST_PARAM = "rolesList";
    public static final String META_DATA_PARAM = "metaData";
    public static final String ADMIN_EMAIL_PARAM = "adminEmailAddresses";

    public static final String DATE_FORMATE_REQUEST_PARAM = "MMddyyyy";

    //status
    public static final String STATUS_REQUESTED = "Requested";
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_ACCEPTED = "Accepted";
    public static final String STATUS_REJECTED = "Rejected";
    public static final String STATUS_IN_PROGRESS = "In-Progress";
    public static final String STATUS_SCAR_ISSUED = "Scar-Issued";

    // Page URLs
    public static final String INDEX_URI_TEMPLATE = "/";  // GET
    public static final String APP_URI_TEMPLATE = "/app";  // GET
    public static final String INBOX_URI_TEMPLATE = "/app/inbox";  // GET
    public static final String COMPOSE_EMAIL_URI_TEMPLATE = "/app/compose-email";  // GET    
    public static final String SEND_EMAIL_URI_TEMPLATE = "/app/send-email";  // POST
    public static final String ADMIN_URI_TEMPLATE = "/admin";  // GET
    public static final String ADMIN_USERS_URI_TEMPLATE = "/admin/users";  // GET
    public static final String ADMIN_USERS_SAVE_URI_TEMPLATE = "/admin/users-save";  // POST
    public static final String ADMIN_USER_UPDATE_URI_TEMPLATE = "/admin/user/update"; // POST

    
    public static final String DOC_TYPE_REPO = "repo";
    public static final String DOC_TYPE_TRAINING = "training";
    public static final String DOC_BASE_URI = "/app/document";  // GET
    public static final String DOC_VIEW_URI = "/app/doc-repo";
    
    public static final String DOC_ADMIN_BASE_URI = "/admin/doc";
    public static final String DOC_DISP_URI = DOC_BASE_URI+"/{docType}";  // GET    
	public static final String DOC_UPLOAD_URI = DOC_ADMIN_BASE_URI+"/{docType}/upload";  
	public static final String DOC_DOWNLOAD_FILE_URI = DOC_BASE_URI+"/{docType}/download";
	public static final String DOC_DELETE_FILE_URI = DOC_BASE_URI+"/{docType}/delete";
	
    public static final String DOC_DISP_URI_TEMPLATE = DOC_BASE_URI+"/%s";  // GET    
	public static final String DOC_UPLOAD_URI_TEMPLATE = DOC_ADMIN_BASE_URI+"/%s/upload";  
	public static final String DOC_DOWNLOAD_FILE_URI_TEMPLATE = DOC_BASE_URI+"/%s/download";
	public static final String DOC_DELETE_FILE_URI_TEMPLATE = DOC_BASE_URI+"/%s/delete";	
    
    public static final String AUDIT_URI_TEMPLATE = "/audit";  // GET
    public static final String EXPORT_URI_TEMPLATE = "/export";  // GET
    public static final String EXPORT_EXCEL_REPORT_URI_TEMPLATE = "/exportExcelReport";  // GET
    public static final String AUDIT_TRAIL_URI_TEMPLATE = "/app/audittrail";
    public static final String AUDIT_TRAIL_DATA_TEMPLATE = "/app/audit-trail-Report";
    public static final String AUDIT_OBSRV_URI_TEMPLATE = "/app/audit/obsrv";
    public static final String AUDIT_OBSRVE_URI_TEMPLATE = "/app/audit/observ";
    public static final String AUDIT_OBSRV_URI_TEMPLATE_DB_RESULT = "/data/audit/db";
    public static final String AUDIT_OBSRV_GETLIST_URI_DB_RESULT = "/data/audit/getlist";
    public static final String AUDIT_OBSRV_EDIT_URI_DB_RESULT = "/data/audit/edit";
    public static final String AUDIT_OBSRV_UPDATE_URI = "/data/audit/update";

    public static final String AUDIT_PLAN_URI_TEMPLATE = "/app/audit/plan";

    //Audit Trail Report
    public static final String AUDIT_TRAIL_URI_RESULT = "/data/auditTrail";
    public static final String AUDIT_TRAIL_EXPORT_EXCEL = "/app/exportExcelReport";

    // Login Controller
    public static final String LOGIN_URI_TEMPLATE = "/login"; // GET
    public static final String LOGIN_FAILED_URI_TEMPLATE = "/login-failed"; // GET

    // Forgotten password
//    public static final String FORGOTTEN_PASSWORD_URI_TEMPLATE = "/forgotten-password";  // GET
//    public static final String FORGOT_URI_TEMPLATE = "/forgot"; //GET
//    public static final String RESET_PASSWORD_URI_TEMPLATE = "/reset-password/{hash}";  // GET
    // Quote Controller
    public static final String QUOTE_URI_TEMPLATE = "/app/quote"; // GET
    public static final String QUOTE_DEATIL_VIEW_TEMPLATE = "/app/quote-detail"; // GET, POST
    public static final String QUOTE_DEATIL_URI_TEMPLATE = "/app/quote/{rfqNo}/{vendorCode}"; // GET, POST

    public static final String QUOTE_DATA_URI_TEMPLATE = "/data/quote"; // GET, POST
    public static final String QUOTE_ID_DATA_URI_TEMPLATE = "/data/quote/{id}"; // GET, POST
    //AuditReportController
    public static final String REPORT_AUDITOBSR_URI_TEMPLATE = "/app/auditobsrreport";
    public static final String REPORT_AUDITOBSR_URI_VENDOR = "/app/auditobsrreportvendor";
    public static final String REPORT_AUDITOBSR_URI_BUYER = "/app/auditobsrreportbuyer";
    public static final String REPORT_AUDITOBSR_SEARCH_VENDOR = "/app/vauditreportsearch";
    public static final String REPORT_AUDITOBSR_SEARCH_BUYER = "/app/bauditreportsearch";

    public static final String EXPORT_AUDITOBSR_VENDOR = "/app/exporxslv";
    public static final String EXPORT_AUDITOBSR_BUYER = "/app/exporxslb";
    //end audit
    //PCR Report 
    public static final String REPORT_PCR_VIEW = "/app/pcrreport";
    public static final String REPORT_PCR_TEMPLATE_VENDOR = "/app/pcr-report-vendor";
    public static final String REPORT_PCR_TEMPLATE_BUYER = "/app/pcr-report-buyer";
    public static final String EXPORT_PCR_TEMPLATE_VENDOR = "/app/exportpcrxslv";
    public static final String EXPORT_PCR_TEMPLATE_BUYER = "/app/exportpcrxslb";

    public static final String REPORT_PCR_ATTACHMENT = "/app/pcr-report-attach/{poId}";

    //end
    // User controller
    public static final String USERS_URI_TEMPLATE = "/data/users";  // GET, POST
    public static final String USERS_ID_URI_TEMPLATE = "/data/users/{id}";  // GET, PUT, DELETE

    // Document
    public static final String DOCUMENT_DOWNLOAD_URI_TEMPLATE = "/document/download/{id}";
    public static final String DOCUMENT_UPLOAD_URI_TEMPLATE = "/document/upload";

    // order uri
    public static final String ORDER_URI_TEMPLATE = "/app/order";
    public static final String ORDER_ACK_URI_TEMPLATE = "/app/order/ack";
    public static final String ORDER_URI_UPDATE = "/data/order/update";
    public static final String ORDER_DOWNLOAD_FILE_URI = "/data/order/download/file";
    public static final String ORDER_DOWNLOAD_FILE_REF_URI = "/data/order/download/file/{refModule}/{refNo}";
    public static final String ORDER_URI_TEMPLATE_SAP_RESULT = "/data/order/sap";
    public static final String ORDER_URI_TEMPLATE_DB_RESULT = "/data/order/db";
    public static final String ADAPTER_ORDER_ROLE_URI_TEMPLATE = "/api/order/{role}"; // GET,
    public static final String ADAPTER_ORDER_URI_TEMPLATE = "/api/order"; // GET,

    //ref docs
    public static final String PORECON_GET_DOWNLOAD_FILE_URI = "/app/porecon/download/file";
    public static final String PORECON_GET_DOWNLOAD_FILE_LIST_URI = "/app/porecon/download/file/{invoiceNo}";
    public static final String PORECON_UPLOAD_FILE_URI = "/app/porecon/upload/file";

    public static final String ASN_GET_DOWNLOAD_FILE_URI = "/app/asn/download/file";
    public static final String ASN_GET_DOWNLOAD_FILE_LIST_URI = "/app/asn/download/file/{poNo}/{asnNo}";

    // payment history uri
    public static final String PAYMENT_HISTORY_URI_TEMPLATE = "/app/payment-history";
    public static final String PAYMENT_HISTORY_EXPORT_URI_TEMPLATE = "/app/payment-history/export";
    public static final String PAYMENT_HISTORY_URI_TEMPLATE_SAP_RESULT = "/data/payment-history/sap";
    public static final String ADAPTER_PAYMENT_HISTORY_ROLE_URI_TEMPLATE = "/api/payment-history/{role}"; // GET,
    public static final String ADAPTER_PAYMENT_HISTORY_URI_TEMPLATE = "/api/payment-history"; // GET,

    // po reconcillation uri
    public static final String DEL_PERF_URI_TEMPLATE = "/app/delperf";
    public static final String QUALITY_URI_TEMPLATE = "/app/quality";
    public static final String PORECON_URI_TEMPLATE = "/app/porecon";
    public static final String PORECON_EXPORT_URI_TEMPLATE = "/app/porecon/export";
    public static final String DEL_PERF_EXPORT_URI_TEMPLATE = "/app/delperf/export";
    public static final String QUALITY_EXPORT_URI_TEMPLATE = "/app/quality/export";
    public static final String PORECON_URI_TEMPLATE_SAP_RESULT = "/data/porecon/sap";
    public static final String ADAPTER_PORECON_ROLE_URI_TEMPLATE = "/api/porecon/{role}"; // GET,
    public static final String ADAPTER_PORECON_URI_TEMPLATE = "/api/porecon"; // GET,
    public static final String PORECON_DELETE_FILE_URI = "/data/porecon/delete/file";
    public static final String DEL_PERF_UPDATE_URI = "/data/delperf/update";

    public static final String ORDER_URI_PDF = "/pdf";

    // Error
    public static final String ERROR_URI_TEMPLATE = "/error"; // GET

    // No access
    public static final String NO_ACCESS_URI_TEMPLATE = "/no-access"; // GET

    // Adapter API
    public static final String ADAPTER_ID_URI_TEMPLATE = "/api/{status}"; // GET,
    public static final String ADAPTER_ORDER_URI_PDF = "/api/order/pdf"; // GET,
    public static final String ADAPTER_ORDER_URI_PDF_ROLE = "/api/order/pdf/ROLE_VENDOR"; // GET,

    // POST
    public static final String TEST_ADAPTER_CONN_URI_TEMPLATE = "/data/conn/{id}";
    public static final String QUOTE_BASE_API_URI_TEMPLATE = "/api/quote";  // GET, POST
    public static final String QUOTE_SEARCH_URI_TEMPLATE = "/api/quote/{role}";  // GET, POST
    public static final String QUOTE_DETAIL_URI_TEMPLATE = "/api/quote/{rfqNo}/{vendorCode}/{role}";  // GET, POST

    public static final String UI_LOGIN_USERNAME_PARAM = "ui.login.username.param";

    //forgot password
    public static final String FORGOT_PASSWORD_URI_TEMPLATE = "/forgot-pwd";
    public static final String RESET_PASSWORD_URI = "/reset-password";  // GET
    public static final String CHANGE_PASSWORD_URI = "/app/change-pwd";  // GET
    public static final String HASH_TOKEN = "hashToken";
    public static final String RESET_PASSWORD_URI_TEMPLATE = RESET_PASSWORD_URI + "/{" + HASH_TOKEN + "}";  // GET

    public static final String ERR_USERNAME_NOT_FOUND = "Username '%s' not found";

    public static final String PROP_KEY_CONFIRM_PWD_NOT_MATCH_ERR = "confirm.pwd.not.match.err";
    public static final String PROP_KEY_PWD_EMPTY_ERR = "pwd.empty.err";
    public static final String PROP_KEY_INVALID_RESET_LINK = "invalid.reset.link";
    public static final String PROP_KEY_OLD_PWD_MATCH_ERR = "old.pwd.match.err";
    public static final String PAGE_TITLE = "pageTitle";
    public static final String PROP_KEY_TITLE_INBOX = "page.title.inbox";
    public static final String PROP_KEY_TITLE_PO = "page.title.po";
    public static final String PROP_KEY_ACCT_INACTIVE = "acct.inactive";
    public static final String PROP_KEY_OLD_PWD_INCORRECT = "old.pwd.incorrect";
    public static final String PROP_KEY_TITLE_CHG_PWD = "page.label.chgpwd.title";

    public static final String AUDIT_MSG_FETCH_FAILED = "Failed to get message for Audit";
    public static final String AUDIT_MSG_USER_LOGIN = "User Logged In";

    public static final String UI_LOGIN_FAILURE_PAGE = "ui.login.failure.page";
    public static final String UI_DEFAULT_LANDING_PAGE = "ui.default.landing.page";
    public static final String UI_ADMIN_LANDING_PAGE = "ui.admin.landing.page";

    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_INACTIVE = "inactive";

    // Price Change Request
    public static final String REF_DOCS_URI_TEMPLATE = "/app/doc/{id}"; //GET

    // Price Change Request
    public static final String PCR_URI_TEMPLATE = "/app/pcr";
    public static final String PCR_BUYER_VIEW_TEMPLATE = "/app/pcr/buyer";
    public static final String PCR_VENDOR_VIEW_TEMPLATE = "/app/pcr/vendor";
    public static final String PCR_BUYER_API_URI_TEMPLATE = "/data/pcr/buyer/";  // GET
    public static final String PCR_VENDOR_API_URI_TEMPLATE = "/data/pcr/vendor/";  // GET

    // ASN Display
    public static final String ASN_VENDOR_URI_TEMPLATE = "/app/asn-vendor"; // GET
    public static final String ASN_BUYER_URI_TEMPLATE = "/app/asn-buyer"; // GET,

    public static final String ASN_DISPLAY_URI_TEMPLATE = "/app/asn"; // GET
    // POST
    public static final String ASN_UPDATE_URI_TEMPLATE = "/data/asn/update"; // POST

    public static final String ASN_DATA_URI_TEMPLATE = "/data/asn"; // GET, POST
    public static final String ASN_CREATE_URI_TEMPLATE = "/app/create-asn";
    public static final String ASN_SAVE_URI_TEMPLATE = "/app/save-asn";

    public static final String ASN_API_SAVE_URI_TEMPLATE = "/api/save-asn"; // POST	
    public static final String ASN_API_SAVE_ROLE_URI_TEMPLATE = ASN_API_SAVE_URI_TEMPLATE + "/{role}"; // POST

    public static final String ASN_API_URI_TEMPLATE = "/api/asn/{role}"; // GET,
    // POST
    public static final String ASN_ID_API_URI_TEMPLATE = "/api/asn/{role}/{asnNo}"; // GET,
    // POST

//  public static final String PCRSUPPLIER_URI_TEMPLATE = "/app/pcrsupplier";
    //Pcr Supplier 
    public static final String PCRSUPPLIER_URI_TEMPLATE = "/app/pcrsupplier"; // GETs
    public static final String PCRSUPPLIER_DATA_URI_TEMPLATE = "/data/pcr";// GET, POST
    public static final String PCRSUPPLIER_API_URI_TEMPLATE = "/app/pcrsupplier";  // GET, POST

    public static final String CONFIG_APP_EMAIL_ADDRESSES_PROPERTY = "smtp.username";

    public static final String APP_MAIL_URI_TEMPLATE = "/app/mail-items";  // GET
    public static final String APP_MAIL_ID_URI_TEMPLATE = "/app/mail-item/{id}";  // GET
    public static final String APP_MAIL_ID_READ_URI_TEMPLATE = "/app/mark-mail-as-read/{id}";  // GET
    public static final String APP_MULTI_MAIL_READ_URI_TEMPLATE = "/app/mark-multiple-mail-as-read";  // POST
    public static final String APP_MULTI_MAIL_UNREAD_URI_TEMPLATE = "/app/mark-multiple-mail-as-unread";  // POST    

    public static final String PCRSUPPLIER_SEARCH_URI_TEMPLATE = "/api/pcr";
    public static final String PCRSUPPLIER_SEARCH_URI_TEMPLATE_ROLE = "/api/pcr/{role}";

    public static final String APP_MAIL_UNREAD_COUNT_URI_TEMPLATE = "/app/mail-unread-count";  // GET	

    //vendor profile report
    public static final String VENDOR_PROFILE_REPORT_URI_TEMPLATE = "/app/vendorprofile";
    public static final String VENDOR_PROFILE_REPORT_DATA_TEMPLATE = "/app/vendorprofile-Report-Vendor";

    //vendor information change
    public static final String VENDOR_URI_TEMPLATE = "/app/vendor-info-change"; //GET, POST
    public static final String APPROVER_URI_TEMPLATE = "/app/vendor-info-change/{id}"; //GET, POST
    public static final String APPROVER_ACTION_URI_TEMPLATE = "/app/vendor-info-change-action";
    public static final String APP_VENDOR_INFO_URI_TEMPLATE = "/app/vendor-info-vendor";//POST

    public static final String APP_APPROVER_INFO_URI_TEMPLATE = "/app/vendor-info-approver-view";//POST
    public static final String APP_APPROVER_DETAILED_INFO_URI_TEMPLATE = "/app/vendor-info-approver-view-detailed";//POST

    public static final String PCR_REF_MODULE = "PCR";

    public static final String REF_MODULE_PO_VENDOR = "PO_VENDOR";
    public static final String REF_MODULE_PO_RECONCILE = "PO_RECONCILE";
    public static final String REF_MODULE_VENDOR_INFO_CHANGE = "VENDOR_INFO_CHANGE";
    public static final String VIEW_DATE_FORMAT = "MM-dd-yyyy";
    public static final String AUTO_GENERATED_MAIL = "<br/><br/><span style='font-size : 12px'>This is an auto generated email.</span>";
    public static final String DO_NOT_REPLY_MSG = "<br/><span style='font-size : 12px'>IMPORTANT: Please do not reply to this message or mail address.</span>";
    public static final String EMAIL_SIGNATURE = "<br/>Thank you,<br/>Durez and SBNA Purchasing";

    //Language
    public static final String LANGUAGE_EN = "en";
    public static final String REF_MODULE_ASN_OVERALL_INVOICE = "ASN_OVERALL_INVOICE";
    public static final String REF_MODULE_ASN_INVOICE = "ASN_INVOICE";
    public static final String REF_MODULE_ASN_COA = "ASN_COA";

    public static final String ASN_DOWNLOAD_FILE_URI = "/data/asn/download/file";
    public static final String ASN_DOWNLOAD_FILE_REF_URI = "/data/asn/download/file/{refModule}/{refNo}";
    public static final String PO_STATUS_ACK = "Acknowledge";
    public static final String PO_STATUS_NOT_ACK = "Not-Acknowledged";

    public static final String ASN_CREATE_SUCCESS = "success";
    public static final String ASN_CREATE_FAILURE = "failure";

    public static final String AUDIT_TRAIL_DELIMITER = "-";

    public static final Map<String, String> PACKING_TYPE = new HashMap<String, String>() {
        {
            put("ZPF1", "Unit");
            put("ZPV1", "Fixed");
        }
    };
    public static final Map<String, String> FREIGHT_TYPE = new HashMap<String, String>() {
        {
            put("ZFC1", "Unit");
            put("ZFB1", "Fixed");
        }
    };

}
