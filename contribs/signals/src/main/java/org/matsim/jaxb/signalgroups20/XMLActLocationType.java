//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.20 at 07:21:23 PM MESZ 
//


package org.matsim.jaxb.signalgroups20;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actLocationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actLocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.matsim.org/files/dtd}facilityId" minOccurs="0"/>
 *         &lt;element ref="{http://www.matsim.org/files/dtd}linkId" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actLocationType", propOrder = {
    "facilityId",
    "linkId"
})
final class XMLActLocationType {

    private XMLFacilityId facilityId;
    private XMLLinkId linkId;

    /**
     * Gets the value of the facilityId property.
     * 
     * @return
     *     possible object is
     *     {@link XMLFacilityId }
     *     
     */
    public XMLFacilityId getFacilityId() {
        return facilityId;
    }

    /**
     * Sets the value of the facilityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLFacilityId }
     *     
     */
    public void setFacilityId(XMLFacilityId value) {
        this.facilityId = value;
    }

    /**
     * Gets the value of the linkId property.
     * 
     * @return
     *     possible object is
     *     {@link XMLLinkId }
     *     
     */
    public XMLLinkId getLinkId() {
        return linkId;
    }

    /**
     * Sets the value of the linkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLLinkId }
     *     
     */
    public void setLinkId(XMLLinkId value) {
        this.linkId = value;
    }

}
