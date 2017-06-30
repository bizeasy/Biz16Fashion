//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.28 at 07:09:01 PM IST 
//


package org.apache.ofbiz.osafe.feeds.osafefeeds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FacetValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FacetValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ProductFeatureGroupId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductFeatureId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FromDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ThruDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SequenceNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PlpSwatch" type="{}PlpSwatchType" minOccurs="0"/>
 *         &lt;element name="PdpSwatch" type="{}PdpSwatchType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacetValueType", propOrder = {

})
public class FacetValueType {

    @XmlElement(name = "ProductFeatureGroupId", required = true, defaultValue = "")
    protected String productFeatureGroupId;
    @XmlElement(name = "ProductFeatureId", defaultValue = "")
    protected String productFeatureId;
    @XmlElement(name = "Description", defaultValue = "")
    protected String description;
    @XmlElement(name = "FromDate", defaultValue = "")
    protected String fromDate;
    @XmlElement(name = "ThruDate", defaultValue = "")
    protected String thruDate;
    @XmlElement(name = "SequenceNum", defaultValue = "")
    protected String sequenceNum;
    @XmlElement(name = "PlpSwatch")
    protected PlpSwatchType plpSwatch;
    @XmlElement(name = "PdpSwatch")
    protected PdpSwatchType pdpSwatch;

    /**
     * Gets the value of the productFeatureGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductFeatureGroupId() {
        return productFeatureGroupId;
    }

    /**
     * Sets the value of the productFeatureGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductFeatureGroupId(String value) {
        this.productFeatureGroupId = value;
    }

    /**
     * Gets the value of the productFeatureId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductFeatureId() {
        return productFeatureId;
    }

    /**
     * Sets the value of the productFeatureId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductFeatureId(String value) {
        this.productFeatureId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromDate(String value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the thruDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThruDate() {
        return thruDate;
    }

    /**
     * Sets the value of the thruDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThruDate(String value) {
        this.thruDate = value;
    }

    /**
     * Gets the value of the sequenceNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceNum() {
        return sequenceNum;
    }

    /**
     * Sets the value of the sequenceNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceNum(String value) {
        this.sequenceNum = value;
    }

    /**
     * Gets the value of the plpSwatch property.
     * 
     * @return
     *     possible object is
     *     {@link PlpSwatchType }
     *     
     */
    public PlpSwatchType getPlpSwatch() {
        return plpSwatch;
    }

    /**
     * Sets the value of the plpSwatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlpSwatchType }
     *     
     */
    public void setPlpSwatch(PlpSwatchType value) {
        this.plpSwatch = value;
    }

    /**
     * Gets the value of the pdpSwatch property.
     * 
     * @return
     *     possible object is
     *     {@link PdpSwatchType }
     *     
     */
    public PdpSwatchType getPdpSwatch() {
        return pdpSwatch;
    }

    /**
     * Sets the value of the pdpSwatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpSwatchType }
     *     
     */
    public void setPdpSwatch(PdpSwatchType value) {
        this.pdpSwatch = value;
    }

}
