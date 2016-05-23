package com.sbna.vendorportal.dao;

import java.util.List;
import java.util.Map;

import com.sbna.vendorportal.dto.Pcr;
import com.sbna.vendorportal.pojo.MasterKeyValueHelper;

public abstract interface ReportPCRDao
{
  public abstract List<MasterKeyValueHelper> getPCRNumber(String paramString);
  public abstract List<MasterKeyValueHelper> getPCRNumber4Buyer(String paramString);
  public List<MasterKeyValueHelper> getAllStatus(String locale, String moduleUrlRole);
  public List<Pcr> getPCR(Map<String,String> requestParams);
}
