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
 * <p>Java class for AdjustmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdjustmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipGroupSequenceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdjustmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxPercent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxAuthorityGeo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxAuthorityParty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdjustMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdjustPoints" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdjustConversion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdjustMemberId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PromotionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdjustmentType", propOrder = {
    "shipGroupSequenceId",
    "adjustmentType",
    "amount",
    "taxPercent",
    "taxAuthorityGeo",
    "taxAuthorityParty",
    "adjustMethod",
    "adjustPoints",
    "adjustConversion",
    "adjustMemberId",
    "promotionCode"
})
public class AdjustmentType {

    @XmlElement(name = "ShipGroupSequenceId", required = true)
    protected String shipGroupSequenceId;
    @XmlElement(name = "AdjustmentType", required = true)
    protected String adjustmentType;
    @XmlElement(name = "Amount", required = true)
    protected String amount;
    @XmlElement(name = "TaxPercent", required = true)
    protected String taxPercent;
    @XmlElement(name = "TaxAuthorityGeo", required = true)
    protected String taxAuthorityGeo;
    @XmlElement(name = "TaxAuthorityParty", required = true)
    protected String taxAuthorityParty;
    @XmlElement(name = "AdjustMethod", required = true)
    protected String adjustMethod;
    @XmlElement(name = "AdjustPoints", required = true)
    protected String adjustPoints;
    @XmlElement(name = "AdjustConversion", required = true)
    protected String adjustConversion;
    @XmlElement(name = "AdjustMemberId", required = true)
    protected String adjustMemberId;
    @XmlElement(name = "PromotionCode", required = true)
    protected String promotionCode;

    /**
     * Gets the value of the shipGroupSequenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipGroupSequenceId() {
        return shipGroupSequenceId;
    }

    /**
     * Sets the value of the shipGroupSequenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipGroupSequenceId(String value) {
        this.shipGroupSequenceId = value;
    }

    /**
     * Gets the value of the adjustmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustmentType() {
        return adjustmentType;
    }

    /**
     * Sets the value of the adjustmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustmentType(String value) {
        this.adjustmentType = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the taxPercent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxPercent() {
        return taxPercent;
    }

    /**
     * Sets the value of the taxPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxPercent(String value) {
        this.taxPercent = value;
    }

    /**
     * Gets the value of the taxAuthorityGeo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxAuthorityGeo() {
        return taxAuthorityGeo;
    }

    /**
     * Sets the value of the taxAuthorityGeo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxAuthorityGeo(String value) {
        this.taxAuthorityGeo = value;
    }

    /**
     * Gets the value of the taxAuthorityParty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxAuthorityParty() {
        return taxAuthorityParty;
    }

    /**
     * Sets the value of the taxAuthorityParty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxAuthorityParty(String value) {
        this.taxAuthorityParty = value;
    }

    /**
     * Gets the value of the adjustMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustMethod() {
        return adjustMethod;
    }

    /**
     * Sets the value of the adjustMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustMethod(String value) {
        this.adjustMethod = value;
    }

    /**
     * Gets the value of the adjustPoints property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustPoints() {
        return adjustPoints;
    }

    /**
     * Sets the value of the adjustPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustPoints(String value) {
        this.adjustPoints = value;
    }

    /**
     * Gets the value of the adjustConversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustConversion() {
        return adjustConversion;
    }

    /**
     * Sets the value of the adjustConversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustConversion(String value) {
        this.adjustConversion = value;
    }

    /**
     * Gets the value of the adjustMemberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustMemberId() {
        return adjustMemberId;
    }

    /**
     * Sets the value of the adjustMemberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustMemberId(String value) {
        this.adjustMemberId = value;
    }

    /**
     * Gets the value of the promotionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * Sets the value of the promotionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionCode(String value) {
        this.promotionCode = value;
    }

}
