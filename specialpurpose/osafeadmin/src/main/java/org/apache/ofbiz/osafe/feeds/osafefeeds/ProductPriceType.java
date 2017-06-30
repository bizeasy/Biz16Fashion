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
 * <p>Java class for ProductPriceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductPriceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ListPrice" type="{}ListPriceType"/>
 *         &lt;element name="SalesPrice" type="{}SalesPriceType"/>
 *         &lt;element name="RecurringPrice" type="{}RecurringPriceType"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductPriceType", propOrder = {

})
public class ProductPriceType {

    @XmlElement(name = "ListPrice", required = true)
    protected ListPriceType listPrice;
    @XmlElement(name = "SalesPrice", required = true)
    protected SalesPriceType salesPrice;
    @XmlElement(name = "RecurringPrice", required = true)
    protected RecurringPriceType recurringPrice;

    /**
     * Gets the value of the listPrice property.
     * 
     * @return
     *     possible object is
     *     {@link ListPriceType }
     *     
     */
    public ListPriceType getListPrice() {
        return listPrice;
    }

    /**
     * Sets the value of the listPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListPriceType }
     *     
     */
    public void setListPrice(ListPriceType value) {
        this.listPrice = value;
    }

    /**
     * Gets the value of the salesPrice property.
     * 
     * @return
     *     possible object is
     *     {@link SalesPriceType }
     *     
     */
    public SalesPriceType getSalesPrice() {
        return salesPrice;
    }

    /**
     * Sets the value of the salesPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesPriceType }
     *     
     */
    public void setSalesPrice(SalesPriceType value) {
        this.salesPrice = value;
    }

    /**
     * Gets the value of the recurringPrice property.
     * 
     * @return
     *     possible object is
     *     {@link RecurringPriceType }
     *     
     */
    public RecurringPriceType getRecurringPrice() {
        return recurringPrice;
    }

    /**
     * Sets the value of the recurringPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecurringPriceType }
     *     
     */
    public void setRecurringPrice(RecurringPriceType value) {
        this.recurringPrice = value;
    }

}
