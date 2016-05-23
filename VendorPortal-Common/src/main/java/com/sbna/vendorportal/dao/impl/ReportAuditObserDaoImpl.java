package com.sbna.vendorportal.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbna.vendorportal.dao.AbstractHibernateDao;
import com.sbna.vendorportal.dao.ReportAuditObserDao;
import com.sbna.vendorportal.dto.AuditPlan;
import com.sbna.vendorportal.dto.PurchaseOrg;
import com.sbna.vendorportal.pojo.AuditObsrReportPojo;
import com.sbna.vendorportal.pojo.MasterDataHelper;

@Repository("reportAuditObserDao")
@Transactional(propagation=Propagation.MANDATORY)
public class ReportAuditObserDaoImpl
  extends AbstractHibernateDao<AuditPlan>
  implements ReportAuditObserDao
{
  public List<AuditObsrReportPojo> getReportAuditObsr4Vendor(String year, String month, String userId)
  {
    StringBuilder query = new StringBuilder("");
    query.append("SELECT SUM(CASE AUDIT_OBSR_STATUS_ID WHEN 1000003 THEN 1 ELSE 0 END) AS \"OPEN\",");
    query.append("SUM(CASE AUDIT_OBSR_STATUS_ID WHEN 1000004 THEN 1 ELSE 0 END) AS \"CLOSE\",");
    query.append("CONVERT(CHAR(4), obsr_date, 100) + CONVERT(CHAR(4), obsr_date, 120) AS \"DATE\" ");
    query.append("FROM AUDIT_OBSR AO , audit_plan AP ");
    query.append("WHERE AO.AUDIT_PLAN_ID=AP.ID ");
    query.append("AND AP.VENDOR_ID=? ");
    if ("00".equals(month)) {
      query.append(" AND\tCONVERT(CHAR(4), obsr_date, 120)=? ");
    } else {
      query.append(" AND CONVERT(VARCHAR(7), obsr_date, 120)=? ");
    }
    query.append("GROUP BY  OBSR_DATE");
    Query q = getEntityManager().createNativeQuery(query.toString());
    q.setParameter(1, userId);
    if ("00".equals(month)) {
      q.setParameter(2, year.trim());
    } else {
      q.setParameter(2, year.trim() + "-" + month.trim());
    }
    return formateReport(q.getResultList());
  }
  
  public List getAuditObsr4Buyer(String year, String month, String userId, String fromPorg, String toPorg, String fromVendor, String toVendor)
  {
    StringBuilder query = new StringBuilder("");
    query.append("SELECT SUM(CASE AUDIT_OBSR_STATUS_ID WHEN 1000003 THEN 1 ELSE 0 END) AS \"OPEN\",");
    query.append("SUM(CASE AUDIT_OBSR_STATUS_ID WHEN 1000004 THEN 1 ELSE 0 END) AS \"CLOSE\",");
    query.append(" CONVERT(CHAR(4), obsr_date, 100) + CONVERT(CHAR(4), obsr_date, 120) AS \"DATE\" ");
    query.append("FROM AUDIT_OBSR AO , audit_plan AP ");
    query.append("WHERE AO.AUDIT_PLAN_ID=AP.ID ");
    if ((!"-1".equals(fromVendor)) && (!"-1".equals(toVendor))) {
      query.append("AND AP.VENDOR_ID in (SELECT U.ID FROM USR U WHERE U.USR_NAME BETWEEN '" + fromVendor + "' AND '" + toVendor + "') ");
    } else if (("-1".equals(toVendor)) && (!"-1".equals(fromVendor))) {
      query.append(" AND AP.VENDOR_ID in (SELECT U.ID FROM USR U WHERE U.USR_NAME >= '" + fromVendor + "') ");
    } else if (("-1".equals(fromVendor)) && (!"-1".equals(toVendor))) {
      query.append(" AND AP.VENDOR_ID in (SELECT U.ID FROM USR U WHERE U.USR_NAME <= '" + toVendor + "') ");
    }
    if ((!"-1".equals(fromPorg)) && (!"-1".equals(toPorg))) {
      query.append("AND AP.prchs_org in (Select po.id from prchs_org po where po.prchs_org_code between '" + fromPorg + "' AND '" + toPorg + "') ");
    } else if (("-1".equals(toPorg)) && (!"-1".equals(fromPorg))) {
      query.append(" AND AP.prchs_org in (Select po.id from prchs_org po where po.prchs_org_code >= '" + fromPorg + "') ");
    } else if (("-1".equals(fromPorg)) && (!"-1".equals(toPorg))) {
      query.append(" AND AP.prchs_org in (Select po.id from prchs_org po where po.prchs_org_code <= '" + toPorg + "') ");
    }
    if ("00".equals(month)) {
      query.append(" AND\tCONVERT(CHAR(4), obsr_date, 120)='" + year.trim() + "' ");
    } else {
      query.append(" AND CONVERT(VARCHAR(7), obsr_date, 120)='" + year.trim() + "-" + month.trim() + "' ");
    }
    query.append(" GROUP BY  OBSR_DATE");
    System.out.println("Query : " + query.toString());
    Query q = getEntityManager().createNativeQuery(query.toString());

    return formateReport(q.getResultList());
  }
  
  public List<MasterDataHelper> getAllUsernameFullname()
  {
    Query query = getEntityManager()
      .createQuery(
      "SELECT new com.sbna.vendorportal.pojo.MasterDataHelper(u.usrName, p.fullName) FROM User u JOIN u.profile p JOIN u.roles r WHERE r.roleDescEng = 'ROLE_VENDOR'");
    

    return query.getResultList();
  }
  
  public List<PurchaseOrg> getAllPrchOrg()
  {
    Query query = getEntityManager().createQuery(
      "SELECT P FROM PurchaseOrg P");
    return query.getResultList();
  }
  
  public List<AuditObsrReportPojo> formateReport(List resultList)
  {
    List<AuditObsrReportPojo> formatedList = new ArrayList();
    Iterator<Object[]> itr = resultList.iterator();
    while (itr.hasNext())
    {
      Object[] res = (Object[])itr.next();
      AuditObsrReportPojo aor = new AuditObsrReportPojo();
      aor.setOpen(((Integer)res[0]).intValue());
      aor.setClose(((Integer)res[1]).intValue());
      aor.setDate((String)res[2]);
      formatedList.add(aor);
    }
    return formatedList;
  }
}
