/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbna.vendorportal.config;

/**
 *
 * @author Pbatthala
 */
public class AdapterProjectConfig {

    public static final int DATE_RANGE_MIN = -6; //In month for filter
    

    public static final int PO_PDF_RANGE = 5; //In month for filter

    public static final String IV_FLAG_HEADER = "H";
    public static final String IV_FLAG_ITEM = "I";
    public static final String IV_FLAG_BOTH = "C";

    public static final String SIGN_INCLUDE = "I";
    public static final String SIGN_EXCLUDE = "E";

    public static final String OPTION_BETWEEN = "BT";//Between    
    public static final String OPTION_EQUAL = "EQ";//Equal    
//    public static final String OPTION_GREATER = "GT";//Greater    
    public static final String OPTION_LESSTHANEQUAL = "LE";//Greater than equal 
    public static final String OPTION_GREATEREQUAL = "GE";//Greater than equal 

    public static final String BAPI_STATUS_SUCCESS = "S";//BAPI SUCCESS 
    public static final String BAPI_STATUS_ERROR = "E";//BAPI ERROR
    
    public static final String STATUS_OPEN = "O";//STATUS OPEN
    public static final String STATUS_ALL = "A";//STATUS ALL
    
    public static final String PCR_API_ROLE_URI_TEMPLATE = "/api/pcr/buyer/{role}";  // GET, POST
    
    public static final String PCR_API_VP_URI_TEMPLATE = "/api/pcr/buyer";  // GET, POST
    
    
    public static final String API_VENDOR_INFO_URI_TEMPLATE = "/api/vendor-info"; //GET, POST
    public static final String VENDOR_INFO_API_URI_TEMPLATE = "/api/vendor-info/{role}";  // GET, POST
    public static final String VENDOR_INFO_APPROVEORDENY_API_URI_TEMPLATE = "/api/vendor-info-approve-deny";  // GET, POST
    
    public static final String FIRST_TIME_USER = "FirstTimeUser";  // GET, POST

}
