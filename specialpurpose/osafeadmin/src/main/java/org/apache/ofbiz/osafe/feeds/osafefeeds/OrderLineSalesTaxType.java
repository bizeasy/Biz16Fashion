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
 * <p>Java class for OrderLineSalesTaxType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderLineSalesTaxType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShipGroupSequenceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxPercent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxAuthorityGeo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TaxAuthorityParty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SalesTax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderLineSalesTaxType", propOrder = {
    "shipGroupSequenceId",
    "taxPercent",
    "taxAuthorityGeo",
    "taxAuthorityParty",
    "salesTax"
})
public class OrderLineSalesTaxType {

    @XmlElement(name = "ShipGroupSequenceId", required = true)
    protected String shipGroupSequenceId;
    @XmlElement(name = "TaxPercent", required = true)
    protected String taxPercent;
    @XmlElement(name = "TaxAuthorityGeo", required = true)
    protected String taxAuthorityGeo;
    @XmlElement(name = "TaxAuthorityParty", required = true)
    protected String taxAuthorityParty;
    @XmlElement(name = "SalesTax", required = true)
    protected String salesTax;

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
     * Gets the value of the salesTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesTax() {
        return salesTax;
    }

    /**
     * Sets the value of the salesTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesTax(String value) {
        this.salesTax = value;
    }

}
