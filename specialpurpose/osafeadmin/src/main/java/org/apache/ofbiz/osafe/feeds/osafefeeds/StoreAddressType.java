//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.28 at 07:09:01 PM IST 
//


package com.osafe.feeds.osafefeeds;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StoreAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StoreAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CityTown" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StateProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ZipPostCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StorePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StoreAddressType", propOrder = {
    "country",
    "address1",
    "address2",
    "address3",
    "cityTown",
    "stateProvince",
    "zipPostCode",
    "storePhone"
})
public class StoreAddressType {

    @XmlElement(name = "Country", required = true, defaultValue = "")
    protected String country;
    @XmlElement(name = "Address1", required = true, defaultValue = "")
    protected String address1;
    @XmlElement(name = "Address2", required = true, defaultValue = "")
    protected String address2;
    @XmlElement(name = "Address3", required = true, defaultValue = "")
    protected String address3;
    @XmlElement(name = "CityTown", required = true, defaultValue = "")
    protected String cityTown;
    @XmlElement(name = "StateProvince", required = true, defaultValue = "")
    protected String stateProvince;
    @XmlElement(name = "ZipPostCode", required = true, defaultValue = "")
    protected String zipPostCode;
    @XmlElement(name = "StorePhone", required = true, defaultValue = "")
    protected String storePhone;

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the address1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the address3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * Sets the value of the address3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress3(String value) {
        this.address3 = value;
    }

    /**
     * Gets the value of the cityTown property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityTown() {
        return cityTown;
    }

    /**
     * Sets the value of the cityTown property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityTown(String value) {
        this.cityTown = value;
    }

    /**
     * Gets the value of the stateProvince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * Sets the value of the stateProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateProvince(String value) {
        this.stateProvince = value;
    }

    /**
     * Gets the value of the zipPostCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipPostCode() {
        return zipPostCode;
    }

    /**
     * Sets the value of the zipPostCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipPostCode(String value) {
        this.zipPostCode = value;
    }

    /**
     * Gets the value of the storePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStorePhone() {
        return storePhone;
    }

    /**
     * Sets the value of the storePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStorePhone(String value) {
        this.storePhone = value;
    }

}
