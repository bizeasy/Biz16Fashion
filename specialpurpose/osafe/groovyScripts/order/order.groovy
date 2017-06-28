package common;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.util.EntityQuery;

import java.util.LinkedList;
import java.util.HashMap;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.util.EntityUtil;
import org.apache.ofbiz.osafe.util.Util;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.StringUtil;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.base.util.string.FlexibleStringExpander;

orderHeader = request.getAttribute("orderHeader");
status = orderHeader.getRelatedOne("StatusItem",true);
orderItemShipGroups =  orderHeader.getRelatedCache("OrderItemShipGroup", UtilMisc.toList("shipGroupSeqId"));
orderItemShipGroupSize = orderItemShipGroups.size();
rowClass = request.getAttribute("rowClass");
lineIndex = request.getAttribute("lineIndex");
trackingURL = "";
trackingNumber = "";
if (UtilValidate.isNotEmpty(orderItemShipGroups) && orderItemShipGroupSize == 1)
 {
    for (GenericValue shipGroup : orderItemShipGroups)
    {
        trackingNumber = shipGroup.trackingNumber;
        findCarrierShipmentMethodMap = UtilMisc.toMap("shipmentMethodTypeId", shipGroup.shipmentMethodTypeId, "partyId", shipGroup.carrierPartyId,"roleTypeId" ,"CARRIER");
        //carrierShipmentMethod = delegator.findByPrimaryKeyCache("CarrierShipmentMethod", findCarrierShipmentMethodMap);
        carrierShipmentMethod =  EntityQuery.use(delegator).from("CarrierShipmentMethod").where(findCarrierShipmentMethodMap).cache().queryOne();
        carrierDescription = "";
        if (UtilValidate.isNotEmpty(carrierShipmentMethod))
        {
            shipmentMethodType = carrierShipmentMethod.getRelatedOne("ShipmentMethodType",true);
            if (UtilValidate.isNotEmpty(shipmentMethodType))
            {
              carrierDescription = shipmentMethodType.description;
            }
        	
        }
        carrierPartyGroupName = "";
        if (UtilValidate.isNotEmpty(shipGroup.carrierPartyId) && shipGroup.carrierPartyId != "_NA_")
        {
            carrierParty = carrierShipmentMethod.getRelatedOne("Party",true);
            carrierPartyGroup = carrierParty.getRelatedOne("PartyGroup",true);
            carrierPartyGroupName = carrierPartyGroup.groupName;
            trackingURLPartyContents = delegator.findByAndCache("PartyContent",UtilMisc.toMap("partyId",shipGroup.carrierPartyId,"partyContentTypeId","TRACKING_URL"));
            if (UtilValidate.isNotEmpty(trackingURLPartyContents))
            {
                trackingURLPartyContent = EntityUtil.getFirst(trackingURLPartyContents);
                if (UtilValidate.isNotEmpty(trackingURLPartyContent))
                {
                    content = trackingURLPartyContent.getRelatedOne("Content",true);
                    if (UtilValidate.isNotEmpty(content))
                    {
                        dataResource = content.getRelatedOne("DataResource",true);
                        if (UtilValidate.isNotEmpty(dataResource))
                        {
                            electronicText = dataResource.getRelatedOne("ElectronicText",true);
                            trackingURL = electronicText.textData;
                            if (UtilValidate.isNotEmpty(trackingURL))
                            {
                                trackingURL = FlexibleStringExpander.expandString(trackingURL, UtilMisc.toMap("TRACKING_NUMBER",trackingNumber));
                            }
                        }
                    }
                }
            }
        }
    }
 }




context.orderHeader = orderHeader;
context.status = status;
context.orderItemShipGroups = orderItemShipGroups;
context.orderItemShipGroupSize = orderItemShipGroupSize;
context.trackingURL = trackingURL;
context.trackingNumber = trackingNumber;
context.lineIndex = lineIndex;
context.rowClass = rowClass;



