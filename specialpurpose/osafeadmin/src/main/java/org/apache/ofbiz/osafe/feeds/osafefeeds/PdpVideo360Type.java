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
 * <p>Java class for PdpVideo360Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PdpVideo360Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}Url"/>
 *         &lt;element ref="{}ThruDate" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PdpVideo360Type", propOrder = {

})
public class PdpVideo360Type {

    @XmlElement(name = "Url", required = true, defaultValue = "")
    protected String url;
    @XmlElement(name = "ThruDate", defaultValue = "")
    protected String thruDate;

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
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

}
