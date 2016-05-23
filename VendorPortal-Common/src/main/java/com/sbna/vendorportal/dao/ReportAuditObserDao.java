package com.sbna.vendorportal.dao;

import java.util.List;

import com.sbna.vendorportal.dto.PurchaseOrg;
import com.sbna.vendorportal.pojo.AuditObsrReportPojo;
import com.sbna.vendorportal.pojo.MasterDataHelper;

public abstract interface ReportAuditObserDao
{
  public abstract List<AuditObsrReportPojo> getReportAuditObsr4Vendor(String paramString1, String paramString2, String paramString3);
  
  public abstract List<AuditObsrReportPojo> getAuditObsr4Buyer(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);
  
  public abstract List<MasterDataHelper> getAllUsernameFullname();
  
  public abstract List<PurchaseOrg> getAllPrchOrg();
}
