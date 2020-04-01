//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.20 at 07:21:05 PM MESZ 
//


package org.matsim.jaxb.signalcontrol20;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.matsim.jaxb.Adapter1;


/**
 * <p>Java class for signalGroupSettingsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signalGroupSettingsType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.matsim.org/files/dtd}idRefType">
 *       &lt;sequence>
 *         &lt;element name="onset">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="sec" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dropping">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="sec" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signalGroupSettingsType", propOrder = {
    "onset",
    "dropping"
})
public final class XMLSignalGroupSettingsType extends XMLIdRefType {

    @XmlElement(required = true)
    protected XMLSignalGroupSettingsType.XMLOnset onset;
    @XmlElement(required = true)
    protected XMLSignalGroupSettingsType.XMLDropping dropping;

    /**
     * Gets the value of the onset property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSignalGroupSettingsType.XMLOnset }
     *     
     */
    public XMLSignalGroupSettingsType.XMLOnset getOnset() {
        return onset;
    }

    /**
     * Sets the value of the onset property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLSignalGroupSettingsType.XMLOnset }
     *     
     */
    public void setOnset(XMLSignalGroupSettingsType.XMLOnset value) {
        this.onset = value;
    }

    /**
     * Gets the value of the dropping property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSignalGroupSettingsType.XMLDropping }
     *     
     */
    public XMLSignalGroupSettingsType.XMLDropping getDropping() {
        return dropping;
    }

    /**
     * Sets the value of the dropping property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLSignalGroupSettingsType.XMLDropping }
     *     
     */
    public void setDropping(XMLSignalGroupSettingsType.XMLDropping value) {
        this.dropping = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="sec" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class XMLDropping {

        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer sec;

        /**
         * Gets the value of the sec property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Integer getSec() {
            return sec;
        }

        /**
         * Sets the value of the sec property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSec(Integer value) {
            this.sec = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="sec" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class XMLOnset {

        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(Adapter1 .class)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer sec;

        /**
         * Gets the value of the sec property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Integer getSec() {
            return sec;
        }

        /**
         * Sets the value of the sec property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSec(Integer value) {
            this.sec = value;
        }

    }

}
