package com.sbna.vendorportal.config;

public class QueryConstants {

	public static final String ASN_DISPLAY_VENDOR_QUERY = "select new com.sbna.vendorportal.pojo.AsnVendorForm( "
			+ "po.poNo, po.poDate, plt.plantCode, pltDesc.plantDesc, "
			+ "mat.materialCode, poi.matExtraDesc, matDesc.materialDesc, asn.currentDispatchQuantity, "
			+ "poi.uom, asn.deliveryDate, asn.id, asn.asnGroupId, asn.carrierName1, asn.trackNo1, asn.carrierName2, asn.trackNo2 ) "
			+ "from PurchaseOrderItemsAsn asn "
			+ "join asn.prchsOrderItem poi "
			+ "join poi.prchsOrder po "
			+ "join po.user u "
			+ "join poi.plant plt "
			+ "join plt.plantDescs pltDesc "
			+ "join pltDesc.lang pltLg "			
			+ "left join poi.material mat "
			+ "left join mat.materialDescs matDesc "
			+ "left join matDesc.lang matLg "
			+ "where ( matLg.langKey = :langKey or poi.material IS NULL ) "
			+ "and pltLg.langKey = :langKey and "			
			+ "u.id = :userId";
	
	public static final String ASN_DISPLAY_BUYER_QUERY = "select new com.sbna.vendorportal.pojo.AsnBuyerForm( "
			+ "prof.fullName, plt.plantCode, pltDesc.plantDesc, mat.materialCode, poi.matExtraDesc, matDesc.materialDesc, asn.currentDispatchQuantity, poi.uom, asn.deliveryDate, "
			+ "po.poNo, asn.carrierName1, asn.trackNo1, asn.carrierName2, asn.trackNo2, asn.id) "
			+ "from PurchaseOrderItemsAsn asn "
			+ "join asn.prchsOrderItem poi "
			+ "join poi.prchsOrder po "
			+ "join po.user vendr "
			+ "join vendr.status v_stat "
			+ "join vendr.profile prof "
//			+ "join poi.prchsGrp pg "
//			+ "join pg.usrs buyr "
			+ "join poi.plant plt "
			+ "join plt.plantDescs pltDesc "
			+ "join pltDesc.lang pltLg "			
			+ "left join poi.material mat "
			+ "left join mat.materialDescs matDesc "
			+ "left join matDesc.lang lg "
			+ "where (lg.langKey = :langKey  or poi.material IS NULL ) "
			+ "and pltLg.langKey = :langKey and "
			+ "v_stat.statusDescEng = 'Active'";
//			+ "and buyr.id = :userId";
	
	
	public static final String ADMIN_CONTROL_USER_QUERY = "select new com.sbna.vendorportal.pojo.UserForm( "
			+ "u, p.firstName, p.lastName, p.middleName, p.fullName, s.statusDescEng ) "
			+ "from User u "
			+ "left join u.profile p "
			+ "left join u.status s "
			+ "where u.id != :curUserId "
                        + "ORDER BY s.id";
	
	public static final String ASN_FETCH_GROUP_QUERY = "select asn from com.sbna.vendorportal.dto.PurchaseOrderItemsAsn asn "
			+ "where asn.asnGroupId = :asnGroupId";
	
	public static final String POI_WITH_BALANCE_QTY_BY_PO_ID = "select new "
			+ "com.sbna.vendorportal.pojo.PurchaseOrderItemHelper"
			+ "(poi.id, po.poNo, poi.itemNo, mat.materialCode, poi.matExtraDesc, "
			+ "matDesc.materialDesc, poi.quantity, poi.balanceQuantity, "
			+ "poi.uom) "
			+ "from PurchaseOrderItem poi "
			+ "join poi.prchsOrder po "
			+ "left join poi.material mat "
			+ "left join mat.materialDescs matDesc "
			+ "left join matDesc.lang lg "
			+ "where po.id = :poId and "
			+ "( lg.langKey = :langKey or poi.material IS NULL ) "
			+ "and poi.balanceQuantity > 0";
        
	public static final String POI_BY_PO_NO = "select new "
			+ "com.sbna.vendorportal.pojo.PurchaseOrderItemHelper"
			+ "(poi.id, po.poNo, poi.itemNo, mat.materialCode, poi.matExtraDesc, "
			+ "matDesc.materialDesc, poi.quantity, poi.balanceQuantity, "
			+ "poi.uom) "
			+ "from PurchaseOrderItem poi "
			+ "join poi.prchsOrder po "
			+ "left join poi.material mat "
			+ "left join mat.materialDescs matDesc "
			+ "left join matDesc.lang lg "
			+ "where po.poNo = :poNo and "
			+ "( lg.langKey = :langKey or poi.material IS NULL ) ";	
}		
