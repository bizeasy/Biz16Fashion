package common;

import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.base.util.UtilHttp;
import org.apache.ofbiz.base.util.StringUtil;
import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.lang.StringUtils;
import org.apache.ofbiz.osafe.util.Util;
import java.util.Map;
import java.net.URLDecoder;
import org.apache.ofbiz.osafe.solr.SolrConstants;

String searchText = URLDecoder.decode(org.apache.ofbiz.osafe.util.Util.stripHTML(parameters.searchText), SolrConstants.DEFAULT_ENCODING);
String searchTextSpellCheck = URLDecoder.decode(org.apache.ofbiz.osafe.util.Util.stripHTML(parameters.searchTextSpellCheck), SolrConstants.DEFAULT_ENCODING);
if (UtilValidate.isNotEmpty(searchText))
{
	SearchResultsPageTitle = UtilProperties.getMessage("OSafeUiLabels", "SearchResultsNotFoundTitle", UtilMisc.toMap("searchText", searchText), locale)
	context.pageTitle = SearchResultsPageTitle;
}

String shoppingListSearchText = org.apache.ofbiz.osafe.util.Util.stripHTML((String)(request.getAttribute("shoppingListSearchText")));
if (UtilValidate.isNotEmpty(shoppingListSearchText))
{
	SearchResultsPageTitle = UtilProperties.getMessage("OSafeUiLabels", "SearchResultsNotFoundTitle", UtilMisc.toMap("searchText", shoppingListSearchText), locale)
	context.pageTitle = SearchResultsPageTitle;
}



