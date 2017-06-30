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
 * <p>Java class for ProductImageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductImageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="PlpSwatch" type="{}PlpSwatchType" minOccurs="0"/>
 *         &lt;element name="PdpSwatch" type="{}PdpSwatchType" minOccurs="0"/>
 *         &lt;element name="PlpSmallImage" type="{}PlpSmallImageType" minOccurs="0"/>
 *         &lt;element name="PlpSmallAltImage" type="{}PlpSmallAltImageType" minOccurs="0"/>
 *         &lt;element name="PdpThumbnailImage" type="{}PdpThumbnailImageType" minOccurs="0"/>
 *         &lt;element name="PdpLargeImage" type="{}PdpLargeImageType" minOccurs="0"/>
 *         &lt;element name="PdpDetailImage" type="{}PdpDetailImageType" minOccurs="0"/>
 *         &lt;element name="PdpVideoImage" type="{}PdpVideoType" minOccurs="0"/>
 *         &lt;element name="PdpVideo360Image" type="{}PdpVideo360Type" minOccurs="0"/>
 *         &lt;element name="PdpAlternateImage" type="{}PdpAlternateImageType" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductImageType", propOrder = {

})
public class ProductImageType {

    @XmlElement(name = "PlpSwatch")
    protected PlpSwatchType plpSwatch;
    @XmlElement(name = "PdpSwatch")
    protected PdpSwatchType pdpSwatch;
    @XmlElement(name = "PlpSmallImage")
    protected PlpSmallImageType plpSmallImage;
    @XmlElement(name = "PlpSmallAltImage")
    protected PlpSmallAltImageType plpSmallAltImage;
    @XmlElement(name = "PdpThumbnailImage")
    protected PdpThumbnailImageType pdpThumbnailImage;
    @XmlElement(name = "PdpLargeImage")
    protected PdpLargeImageType pdpLargeImage;
    @XmlElement(name = "PdpDetailImage")
    protected PdpDetailImageType pdpDetailImage;
    @XmlElement(name = "PdpVideoImage")
    protected PdpVideoType pdpVideoImage;
    @XmlElement(name = "PdpVideo360Image")
    protected PdpVideo360Type pdpVideo360Image;
    @XmlElement(name = "PdpAlternateImage")
    protected PdpAlternateImageType pdpAlternateImage;

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

    /**
     * Gets the value of the plpSmallImage property.
     * 
     * @return
     *     possible object is
     *     {@link PlpSmallImageType }
     *     
     */
    public PlpSmallImageType getPlpSmallImage() {
        return plpSmallImage;
    }

    /**
     * Sets the value of the plpSmallImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlpSmallImageType }
     *     
     */
    public void setPlpSmallImage(PlpSmallImageType value) {
        this.plpSmallImage = value;
    }

    /**
     * Gets the value of the plpSmallAltImage property.
     * 
     * @return
     *     possible object is
     *     {@link PlpSmallAltImageType }
     *     
     */
    public PlpSmallAltImageType getPlpSmallAltImage() {
        return plpSmallAltImage;
    }

    /**
     * Sets the value of the plpSmallAltImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlpSmallAltImageType }
     *     
     */
    public void setPlpSmallAltImage(PlpSmallAltImageType value) {
        this.plpSmallAltImage = value;
    }

    /**
     * Gets the value of the pdpThumbnailImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpThumbnailImageType }
     *     
     */
    public PdpThumbnailImageType getPdpThumbnailImage() {
        return pdpThumbnailImage;
    }

    /**
     * Sets the value of the pdpThumbnailImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpThumbnailImageType }
     *     
     */
    public void setPdpThumbnailImage(PdpThumbnailImageType value) {
        this.pdpThumbnailImage = value;
    }

    /**
     * Gets the value of the pdpLargeImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpLargeImageType }
     *     
     */
    public PdpLargeImageType getPdpLargeImage() {
        return pdpLargeImage;
    }

    /**
     * Sets the value of the pdpLargeImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpLargeImageType }
     *     
     */
    public void setPdpLargeImage(PdpLargeImageType value) {
        this.pdpLargeImage = value;
    }

    /**
     * Gets the value of the pdpDetailImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpDetailImageType }
     *     
     */
    public PdpDetailImageType getPdpDetailImage() {
        return pdpDetailImage;
    }

    /**
     * Sets the value of the pdpDetailImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpDetailImageType }
     *     
     */
    public void setPdpDetailImage(PdpDetailImageType value) {
        this.pdpDetailImage = value;
    }

    /**
     * Gets the value of the pdpVideoImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpVideoType }
     *     
     */
    public PdpVideoType getPdpVideoImage() {
        return pdpVideoImage;
    }

    /**
     * Sets the value of the pdpVideoImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpVideoType }
     *     
     */
    public void setPdpVideoImage(PdpVideoType value) {
        this.pdpVideoImage = value;
    }

    /**
     * Gets the value of the pdpVideo360Image property.
     * 
     * @return
     *     possible object is
     *     {@link PdpVideo360Type }
     *     
     */
    public PdpVideo360Type getPdpVideo360Image() {
        return pdpVideo360Image;
    }

    /**
     * Sets the value of the pdpVideo360Image property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpVideo360Type }
     *     
     */
    public void setPdpVideo360Image(PdpVideo360Type value) {
        this.pdpVideo360Image = value;
    }

    /**
     * Gets the value of the pdpAlternateImage property.
     * 
     * @return
     *     possible object is
     *     {@link PdpAlternateImageType }
     *     
     */
    public PdpAlternateImageType getPdpAlternateImage() {
        return pdpAlternateImage;
    }

    /**
     * Sets the value of the pdpAlternateImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PdpAlternateImageType }
     *     
     */
    public void setPdpAlternateImage(PdpAlternateImageType value) {
        this.pdpAlternateImage = value;
    }

}
