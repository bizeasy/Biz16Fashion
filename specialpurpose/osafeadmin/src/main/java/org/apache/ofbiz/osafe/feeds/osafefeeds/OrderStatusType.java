//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.28 at 07:09:01 PM IST 
//


package org.apache.ofbiz.osafe.feeds.osafefeeds;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProductStoreId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrderStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderShipDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderShipCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderShipMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderTrackingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderItem" type="{}OrderItemType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderStatusType", propOrder = {
    "productStoreId",
    "orderId",
    "orderStatus",
    "orderShipDate",
    "orderShipCarrier",
    "orderShipMethod",
    "orderTrackingNumber",
    "orderNote",
    "orderItem"
})
public class OrderStatusType {

    @XmlElement(name = "ProductStoreId", required = true)
    protected String productStoreId;
    @XmlElement(name = "OrderId", required = true)
    protected String orderId;
    @XmlElement(name = "OrderStatus")
    protected String orderStatus;
    @XmlElement(name = "OrderShipDate", defaultValue = "")
    protected String orderShipDate;
    @XmlElement(name = "OrderShipCarrier", defaultValue = "")
    protected String orderShipCarrier;
    @XmlElement(name = "OrderShipMethod", defaultValue = "")
    protected String orderShipMethod;
    @XmlElement(name = "OrderTrackingNumber", defaultValue = "")
    protected String orderTrackingNumber;
    @XmlElement(name = "OrderNote", defaultValue = "")
    protected String orderNote;
    @XmlElement(name = "OrderItem")
    protected List<OrderItemType> orderItem;

    /**
     * Gets the value of the productStoreId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductStoreId() {
        return productStoreId;
    }

    /**
     * Sets the value of the productStoreId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductStoreId(String value) {
        this.productStoreId = value;
    }

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the orderStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the value of the orderStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderStatus(String value) {
        this.orderStatus = value;
    }

    /**
     * Gets the value of the orderShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderShipDate() {
        return orderShipDate;
    }

    /**
     * Sets the value of the orderShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderShipDate(String value) {
        this.orderShipDate = value;
    }

    /**
     * Gets the value of the orderShipCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderShipCarrier() {
        return orderShipCarrier;
    }

    /**
     * Sets the value of the orderShipCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderShipCarrier(String value) {
        this.orderShipCarrier = value;
    }

    /**
     * Gets the value of the orderShipMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderShipMethod() {
        return orderShipMethod;
    }

    /**
     * Sets the value of the orderShipMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderShipMethod(String value) {
        this.orderShipMethod = value;
    }

    /**
     * Gets the value of the orderTrackingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    /**
     * Sets the value of the orderTrackingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTrackingNumber(String value) {
        this.orderTrackingNumber = value;
    }

    /**
     * Gets the value of the orderNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNote() {
        return orderNote;
    }

    /**
     * Sets the value of the orderNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNote(String value) {
        this.orderNote = value;
    }

    /**
     * Gets the value of the orderItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderItemType }
     * 
     * 
     */
    public List<OrderItemType> getOrderItem() {
        if (orderItem == null) {
            orderItem = new ArrayList<OrderItemType>();
        }
        return this.orderItem;
    }

}
