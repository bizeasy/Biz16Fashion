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
 * <p>Java class for GatewayResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GatewayResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransCodeEnumId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AltReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GatewayCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GatewayFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GatewayMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GatewayResponseType", propOrder = {
    "transCodeEnumId",
    "amount",
    "referenceNumber",
    "altReferenceNumber",
    "transactionDate",
    "gatewayCode",
    "gatewayFlag",
    "gatewayMessage"
})
public class GatewayResponseType {

    @XmlElement(name = "TransCodeEnumId", required = true)
    protected String transCodeEnumId;
    @XmlElement(name = "Amount", required = true)
    protected String amount;
    @XmlElement(name = "ReferenceNumber", required = true)
    protected String referenceNumber;
    @XmlElement(name = "AltReferenceNumber", required = true)
    protected String altReferenceNumber;
    @XmlElement(name = "TransactionDate", required = true)
    protected String transactionDate;
    @XmlElement(name = "GatewayCode", required = true)
    protected String gatewayCode;
    @XmlElement(name = "GatewayFlag", required = true)
    protected String gatewayFlag;
    @XmlElement(name = "GatewayMessage", required = true)
    protected String gatewayMessage;

    /**
     * Gets the value of the transCodeEnumId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransCodeEnumId() {
        return transCodeEnumId;
    }

    /**
     * Sets the value of the transCodeEnumId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransCodeEnumId(String value) {
        this.transCodeEnumId = value;
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
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the altReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltReferenceNumber() {
        return altReferenceNumber;
    }

    /**
     * Sets the value of the altReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltReferenceNumber(String value) {
        this.altReferenceNumber = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionDate(String value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the gatewayCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayCode() {
        return gatewayCode;
    }

    /**
     * Sets the value of the gatewayCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayCode(String value) {
        this.gatewayCode = value;
    }

    /**
     * Gets the value of the gatewayFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayFlag() {
        return gatewayFlag;
    }

    /**
     * Sets the value of the gatewayFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayFlag(String value) {
        this.gatewayFlag = value;
    }

    /**
     * Gets the value of the gatewayMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayMessage() {
        return gatewayMessage;
    }

    /**
     * Sets the value of the gatewayMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayMessage(String value) {
        this.gatewayMessage = value;
    }

}
