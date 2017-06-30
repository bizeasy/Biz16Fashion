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
 * <p>Java class for OrderItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProductId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipGroupSequenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SequenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderItemStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderItemShipDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderItemCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderItemShipMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderItemTrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderItemType", propOrder = {
    "productId",
    "shipGroupSequenceId",
    "sequenceId",
    "orderItemStatus",
    "orderItemShipDate",
    "orderItemCarrier",
    "orderItemShipMethod",
    "orderItemTrackingNumber"
})
public class OrderItemType {

    @XmlElement(name = "ProductId")
    protected String productId;
    @XmlElement(name = "ShipGroupSequenceId")
    protected String shipGroupSequenceId;
    @XmlElement(name = "SequenceId", defaultValue = "")
    protected String sequenceId;
    @XmlElement(name = "OrderItemStatus")
    protected String orderItemStatus;
    @XmlElement(name = "OrderItemShipDate", defaultValue = "")
    protected String orderItemShipDate;
    @XmlElement(name = "OrderItemCarrier", defaultValue = "")
    protected String orderItemCarrier;
    @XmlElement(name = "OrderItemShipMethod", defaultValue = "")
    protected String orderItemShipMethod;
    @XmlElement(name = "OrderItemTrackingNumber", defaultValue = "")
    protected String orderItemTrackingNumber;

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductId(String value) {
        this.productId = value;
    }

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
     * Gets the value of the sequenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceId() {
        return sequenceId;
    }

    /**
     * Sets the value of the sequenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceId(String value) {
        this.sequenceId = value;
    }

    /**
     * Gets the value of the orderItemStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemStatus() {
        return orderItemStatus;
    }

    /**
     * Sets the value of the orderItemStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemStatus(String value) {
        this.orderItemStatus = value;
    }

    /**
     * Gets the value of the orderItemShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemShipDate() {
        return orderItemShipDate;
    }

    /**
     * Sets the value of the orderItemShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemShipDate(String value) {
        this.orderItemShipDate = value;
    }

    /**
     * Gets the value of the orderItemCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemCarrier() {
        return orderItemCarrier;
    }

    /**
     * Sets the value of the orderItemCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemCarrier(String value) {
        this.orderItemCarrier = value;
    }

    /**
     * Gets the value of the orderItemShipMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemShipMethod() {
        return orderItemShipMethod;
    }

    /**
     * Sets the value of the orderItemShipMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemShipMethod(String value) {
        this.orderItemShipMethod = value;
    }

    /**
     * Gets the value of the orderItemTrackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemTrackingNumber() {
        return orderItemTrackingNumber;
    }

    /**
     * Sets the value of the orderItemTrackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemTrackingNumber(String value) {
        this.orderItemTrackingNumber = value;
    }

}
