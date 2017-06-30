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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BigFishProductRatingFeedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BigFishProductRatingFeedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProductRating" type="{}ProductRatingType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Count" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BigFishProductRatingFeedType", propOrder = {
    "productRating"
})
public class BigFishProductRatingFeedType {

    @XmlElement(name = "ProductRating", required = true)
    protected List<ProductRatingType> productRating;
    @XmlAttribute(name = "Count")
    @XmlSchemaType(name = "anySimpleType")
    protected String count;

    /**
     * Gets the value of the productRating property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productRating property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductRating().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductRatingType }
     * 
     * 
     */
    public List<ProductRatingType> getProductRating() {
        if (productRating == null) {
            productRating = new ArrayList<ProductRatingType>();
        }
        return this.productRating;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount(String value) {
        this.count = value;
    }

}
