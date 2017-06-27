package org.apache.ofbiz.osafe.services;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;


import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ModelService;
import org.apache.ofbiz.service.ServiceUtil;
import org.apache.ofbiz.osafe.services.AddressVerificationResponse;
import org.apache.ofbiz.osafe.util.Util;

public class AddressServices {

    public static final String module = AddressServices.class.getName();

    public static Map<String, Object> extendedAddressValidation(DispatchContext dctx, Map<String, ?> context) {
        LocalDispatcher dispatcher = dctx.getDispatcher();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        Map<String, Object> responseMap = ServiceUtil.returnSuccess();
        List suggestionList = new LinkedList<>();

        String productStoreId = (String) context.get("productStoreId");
        String address1 = (String) context.get("address1");
        String address2 = (String) context.get("address2");
        String address3 = (String) context.get("address3");
        String city = (String) context.get("city");
        String state = (String) context.get("stateProvinceGeoId");
        String county = (String) context.get("countyGeoId");
        String postalCode = (String) context.get("postalCode");
        String postalCodeExt = (String) context.get("postalCodeExt");
        String country = (String) context.get("countryGeoId");
        
        responseMap.put("address1",address1);
        responseMap.put("address2",address2);
        responseMap.put("address3",address3);
        responseMap.put("city",city);
        responseMap.put("stateProvinceGeoId",state);
        responseMap.put("countyGeoId",county);
        responseMap.put("postalCode",postalCode);
        responseMap.put("postalCodeExt",postalCodeExt);
        responseMap.put("countryGeoId",country);
        responseMap.put("responseCode",AddressVerificationResponse.AS);
        responseMap.put("suggestionList",suggestionList);
        
        String addressValidationMethod = Util.getProductStoreParm(productStoreId, "ADDRESS_VERIFICATION_METHOD");

    	if (UtilValidate.isEmpty(addressValidationMethod))
    	{
    		return responseMap;
    	}

    	if(addressValidationMethod.equalsIgnoreCase("MELISSA_DATA"))
    	{
            Map melissaAddressParams = UtilMisc.toMap("productStoreId",productStoreId);
            melissaAddressParams.put("address1",address1);
            melissaAddressParams.put("address2",address2);
            melissaAddressParams.put("address3",address3);
            melissaAddressParams.put("city",city);
            melissaAddressParams.put("stateProvinceGeoId",state);
            melissaAddressParams.put("countyGeoId",county);
            melissaAddressParams.put("postalCode",postalCode);
            melissaAddressParams.put("postalCodeExt",postalCodeExt);
            melissaAddressParams.put("countryGeoId",country);
            melissaAddressParams.put("userLogin", userLogin);
            
            try
            {
                Map result = dispatcher.runSync("addressValidationMelissaData", melissaAddressParams);
                if (!ModelService.RESPOND_ERROR.equals(result.get(ModelService.RESPONSE_MESSAGE)))
                {
                    responseMap.put("address1",result.get("address1"));
                    responseMap.put("address2",result.get("address2"));
                    responseMap.put("address3",result.get("address3"));
                    responseMap.put("city",result.get("city"));
                    responseMap.put("stateProvinceGeoId",result.get("stateProvinceGeoId"));
                    responseMap.put("countyGeoId",result.get("countyGeoId"));
                    responseMap.put("postalCode",result.get("postalCode"));
                    responseMap.put("postalCodeExt",result.get("postalCodeExt"));
                    responseMap.put("countryGeoId",result.get("countryGeoId"));
                    responseMap.put("responseCode",result.get("responseCode"));
                    suggestionList = (List) result.get("suggestionList");
                    responseMap.put("suggestionList",suggestionList);
                }
            }
            catch (Exception e)
            {
            	String errMsg = "Could not validate Address using [" + addressValidationMethod + "]";
                Debug.logError(e, errMsg, module);
            }
    	}
        
        return responseMap;
    }
}
