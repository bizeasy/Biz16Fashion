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
 * <p>Java class for PdpAdditionalImageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PdpAdditionalImageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="PdpAdditionalThumbImage" type="{}PdpAdditionalThumbImageType" minOccurs="0"/>
 *         &lt;element name="PdpAdditionalLargeImage" type="{}PdpAdditionalLargeImageType" minOccurs="0"/>
 *         &lt;element name="PdpAdditionalDetailImage" type="{}PdpAdditionalDetailImageType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PdpAdditionalImageType", propOrder = {

})
public class PdpAdditionalImageType {

    @XmlElement(name = "PdpAdditionalThumbImage")
    protected PdpAdditionalThumbImageType pdpAdditionalThumbImage;
    @XmlElement(name = "PdpAdditionalLargeImage")
    protected PdpAdditionalLargeImageType pdpAdditionalLargeImage;
    @XmlElement(name = "PdpAdditionalDetailImage")
    protected PdpAdditionalDetailImageType pdpAdditionalDetailImage;

    /**
     * Gets the value of the pdpAdditionalThumbImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpAdditionalThumbImageType }
     *     
     */
    public PdpAdditionalThumbImageType getPdpAdditionalThumbImage() {
        return pdpAdditionalThumbImage;
    }

    /**
     * Sets the value of the pdpAdditionalThumbImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpAdditionalThumbImageType }
     *     
     */
    public void setPdpAdditionalThumbImage(PdpAdditionalThumbImageType value) {
        this.pdpAdditionalThumbImage = value;
    }

    /**
     * Gets the value of the pdpAdditionalLargeImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpAdditionalLargeImageType }
     *     
     */
    public PdpAdditionalLargeImageType getPdpAdditionalLargeImage() {
        return pdpAdditionalLargeImage;
    }

    /**
     * Sets the value of the pdpAdditionalLargeImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpAdditionalLargeImageType }
     *     
     */
    public void setPdpAdditionalLargeImage(PdpAdditionalLargeImageType value) {
        this.pdpAdditionalLargeImage = value;
    }

    /**
     * Gets the value of the pdpAdditionalDetailImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpAdditionalDetailImageType }
     *     
     */
    public PdpAdditionalDetailImageType getPdpAdditionalDetailImage() {
        return pdpAdditionalDetailImage;
    }

    /**
     * Sets the value of the pdpAdditionalDetailImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpAdditionalDetailImageType }
     *     
     */
    public void setPdpAdditionalDetailImage(PdpAdditionalDetailImageType value) {
        this.pdpAdditionalDetailImage = value;
    }

}
