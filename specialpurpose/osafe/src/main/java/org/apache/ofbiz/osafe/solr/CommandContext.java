package org.apache.ofbiz.osafe.solr;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;




public class CommandContext {
    private HttpServletRequest request;
    private String requestName;
    private String searchText;
    private String productCategoryId;
    private String topMostProductCategoryId;
    private List<String> filterGroups;
    private String sortParameterName;
    private String sortParameterValue;
    private String numberOfRowsShown;
    private boolean onCategoryList;

    private Map<String, String> filterGroupsDescriptions = new HashMap<String, String>();
    private Map<String, String> filterGroupsIds = new HashMap<String, String>();
    private Map<String, String> filterGroupsFacetSorts = new HashMap<String, String>();

    public CommandContext() {
        super();
        filterGroups = new LinkedList<>();
    }

    public boolean isOnCategoryList() {
        return onCategoryList;
    }

    public void setOnCategoryList(boolean onCategoryList) {
        this.onCategoryList = onCategoryList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getTopMostProductCategoryId() {
        return topMostProductCategoryId;
    }

    public void setTopMostProductCategoryId(String topMostProductCategoryId) {
        this.topMostProductCategoryId = topMostProductCategoryId;
    }

    public List<String> getFilterGroups() {
        return filterGroups;
    }

    public void setFilterGroups(List<String> filterGroups) {
        this.filterGroups = filterGroups;
    }

    public void addFilterGroup(String filterGroup) {
        this.filterGroups.add(filterGroup);
    }

    public Map<String, String> getFilterGroupsDescriptions() {
        return filterGroupsDescriptions;
    }

    public void setFilterGroupsDescriptions(Map<String, String> filterGroupsDescriptions) {
        this.filterGroupsDescriptions = filterGroupsDescriptions;
    }

    public Map<String, String> getFilterGroupsIds() {
        return filterGroupsIds;
    }

    public void setFilterGroupsIds(Map<String, String> filterGroupsIds) {
        this.filterGroupsIds = filterGroupsIds;
    }

    public Map<String, String> getFilterGroupsFacetSorts() {
        return filterGroupsFacetSorts;
    }

    public void setFilterGroupsFacetSorts(Map<String, String> filterGroupsFacetSorts) {
        this.filterGroupsFacetSorts = filterGroupsFacetSorts;
    }

    public String getSortParameterName() {
        return sortParameterName;
    }

    public void setSortParameterName(String sortParameterName) {
        this.sortParameterName = sortParameterName;
    }

    public String getSortParameterValue() {
        return sortParameterValue;
    }

    public void setSortParameterValue(String sortParameterValue) {
        this.sortParameterValue = sortParameterValue;
    }

    public String getNumberOfRowsShown() {
        return numberOfRowsShown;
    }

    public void setNumberOfRowsShown(String numberOfRowsShown) {
        this.numberOfRowsShown = numberOfRowsShown;
    }

}
