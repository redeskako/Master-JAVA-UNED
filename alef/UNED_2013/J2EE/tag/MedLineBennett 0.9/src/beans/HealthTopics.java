package beans;
//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2013.08.07 a las 06:13:44 PM CEST 
//




import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import beans.*;



/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="health-topic" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="also-called" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="full-summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="group" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="language-mapped-topic">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                           &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="mesh-heading" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="descriptor">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="other-language" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="vernacular-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="primary-institute" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="related-topic" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="see-reference" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="site" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                             &lt;element name="information-category" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="standard-description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/choice>
 *                           &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="language-mapped-url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                 &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="date-created" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="total" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="date-generated" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "healthTopic"
})
@XmlRootElement(name = "health-topics")
public class HealthTopics {
	
	@XmlElement(name = "health-topic")
    protected List<HealthTopics.HealthTopic> healthTopic;
    @XmlAttribute(name = "total")
    protected Short total;
    @XmlAttribute(name = "date-generated")
    protected String dateGenerated;

    /**
     * Gets the value of the healthTopic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the healthTopic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHealthTopic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HealthTopics.HealthTopic }
     * 
     * 
     */
    public List<HealthTopics.HealthTopic> getHealthTopic() {
        if (healthTopic == null) {
            healthTopic = new ArrayList<HealthTopics.HealthTopic>();
        }
        return this.healthTopic;
    }

    /**
     * Obtiene el valor de la propiedad total.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTotal() {
        return total;
    }

    /**
     * Define el valor de la propiedad total.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTotal(Short value) {
        this.total = value;
    }

    /**
     * Obtiene el valor de la propiedad dateGenerated.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateGenerated() {
        return dateGenerated;
    }

    /**
     * Define el valor de la propiedad dateGenerated.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateGenerated(String value) {
        this.dateGenerated = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="also-called" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="full-summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="group" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="language-mapped-topic">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
     *                 &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="mesh-heading" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="descriptor">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="other-language" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="vernacular-name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="primary-institute" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="related-topic" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="see-reference" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="site" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
     *                   &lt;element name="information-category" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="standard-description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/choice>
     *                 &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="language-mapped-url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
     *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="date-created" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "alsoCalled",
        "fullSummary",
        "group",
        "languageMappedTopic",
        "meshHeading",
        "otherLanguage",
        "primaryInstitute",
        "relatedTopic",
        "seeReference",
        "site"
    })
    public static class HealthTopic {

        @XmlElement(name = "also-called")
        protected List<String> alsoCalled;
        @XmlElement(name = "full-summary", required = true)
        protected String fullSummary;
        protected List<HealthTopics.HealthTopic.Group> group;
        @XmlElement(name = "language-mapped-topic", required = true)
        protected HealthTopics.HealthTopic.LanguageMappedTopic languageMappedTopic;
        @XmlElement(name = "mesh-heading")
        protected List<HealthTopics.HealthTopic.MeshHeading> meshHeading;
        @XmlElement(name = "other-language")
        protected List<HealthTopics.HealthTopic.OtherLanguage> otherLanguage;
        @XmlElement(name = "primary-institute")
        protected HealthTopics.HealthTopic.PrimaryInstitute primaryInstitute;
        @XmlElement(name = "related-topic")
        protected List<HealthTopics.HealthTopic.RelatedTopic> relatedTopic;
        @XmlElement(name = "see-reference")
        protected List<String> seeReference;
        protected List<HealthTopics.HealthTopic.Site> site;
        @XmlAttribute(name = "title")
        protected String title;
        @XmlAttribute(name = "url")
        @XmlSchemaType(name = "anyURI")
        protected String url;
        @XmlAttribute(name = "id")
        protected Short id;
        @XmlAttribute(name = "language")
        protected String language;
        @XmlAttribute(name = "date-created")
        protected String dateCreated;

        /**
         * Gets the value of the alsoCalled property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the alsoCalled property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAlsoCalled().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAlsoCalled() {
            if (alsoCalled == null) {
                alsoCalled = new ArrayList<String>();
            }
            return this.alsoCalled;
        }

        /**
         * Obtiene el valor de la propiedad fullSummary.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFullSummary() {
            return fullSummary;
        }

        /**
         * Define el valor de la propiedad fullSummary.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFullSummary(String value) {
            this.fullSummary = value;
        }

        /**
         * Gets the value of the group property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the group property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HealthTopics.HealthTopic.Group }
         * 
         * 
         */
        public List<HealthTopics.HealthTopic.Group> getGroup() {
            if (group == null) {
                group = new ArrayList<HealthTopics.HealthTopic.Group>();
            }
            return this.group;
        }

        /**
         * Obtiene el valor de la propiedad languageMappedTopic.
         * 
         * @return
         *     possible object is
         *     {@link HealthTopics.HealthTopic.LanguageMappedTopic }
         *     
         */
        public HealthTopics.HealthTopic.LanguageMappedTopic getLanguageMappedTopic() {
        	if (languageMappedTopic == null) {
                languageMappedTopic = new HealthTopics.HealthTopic.LanguageMappedTopic();
            }
            return languageMappedTopic;
        }

        /**
         * Define el valor de la propiedad languageMappedTopic.
         * 
         * @param value
         *     allowed object is
         *     {@link HealthTopics.HealthTopic.LanguageMappedTopic }
         *     
         */
        public void setLanguageMappedTopic(HealthTopics.HealthTopic.LanguageMappedTopic value) {
            this.languageMappedTopic = value;
        }

        /**
         * Gets the value of the meshHeading property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the meshHeading property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMeshHeading().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HealthTopics.HealthTopic.MeshHeading }
         * 
         * 
         */
        public List<HealthTopics.HealthTopic.MeshHeading> getMeshHeading() {
            if (meshHeading == null) {
                meshHeading = new ArrayList<HealthTopics.HealthTopic.MeshHeading>();
            }
            return this.meshHeading;
        }

        /**
         * Gets the value of the otherLanguage property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherLanguage property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtherLanguage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HealthTopics.HealthTopic.OtherLanguage }
         * 
         * 
         */
        public List<HealthTopics.HealthTopic.OtherLanguage> getOtherLanguage() {
            if (otherLanguage == null) {
                otherLanguage = new ArrayList<HealthTopics.HealthTopic.OtherLanguage>();
            }
            return this.otherLanguage;
        }

        /**
         * Obtiene el valor de la propiedad primaryInstitute.
         * 
         * @return
         *     possible object is
         *     {@link HealthTopics.HealthTopic.PrimaryInstitute }
         *     
         */
        public HealthTopics.HealthTopic.PrimaryInstitute getPrimaryInstitute() {
        	if (primaryInstitute == null)
        		primaryInstitute = new HealthTopics.HealthTopic.PrimaryInstitute();
            return primaryInstitute;
        	
        }

        /**
         * Define el valor de la propiedad primaryInstitute.
         * 
         * @param value
         *     allowed object is
         *     {@link HealthTopics.HealthTopic.PrimaryInstitute }
         *     
         */
        public void setPrimaryInstitute(HealthTopics.HealthTopic.PrimaryInstitute value) {
            this.primaryInstitute = value;
        }

        /**
         * Gets the value of the relatedTopic property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the relatedTopic property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRelatedTopic().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HealthTopics.HealthTopic.RelatedTopic }
         * 
         * 
         */
        public List<HealthTopics.HealthTopic.RelatedTopic> getRelatedTopic() {
            if (relatedTopic == null) {
                relatedTopic = new ArrayList<HealthTopics.HealthTopic.RelatedTopic>();
            }
            return this.relatedTopic;
        }

        /**
         * Gets the value of the seeReference property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the seeReference property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSeeReference().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getSeeReference() {
            if (seeReference == null) {
                seeReference = new ArrayList<String>();
            }
            return this.seeReference;
        }

        /**
         * Gets the value of the site property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the site property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSite().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link HealthTopics.HealthTopic.Site }
         * 
         * 
         */
        public List<HealthTopics.HealthTopic.Site> getSite() {
            if (site == null) {
                site = new ArrayList<HealthTopics.HealthTopic.Site>();
            }
            return this.site;
        }

        /**
         * Obtiene el valor de la propiedad title.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitle() {
            return title;
        }

        /**
         * Define el valor de la propiedad title.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Obtiene el valor de la propiedad url.
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
         * Define el valor de la propiedad url.
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
         * Obtiene el valor de la propiedad id.
         * 
         * @return
         *     possible object is
         *     {@link Short }
         *     
         */
        public Short getId() {
            return id;
        }

        /**
         * Define el valor de la propiedad id.
         * 
         * @param value
         *     allowed object is
         *     {@link Short }
         *     
         */
        public void setId(Short value) {
            this.id = value;
        }

        /**
         * Obtiene el valor de la propiedad language.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLanguage() {
            return language;
        }

        /**
         * Define el valor de la propiedad language.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLanguage(String value) {
            this.language = value;
        }

        /**
         * Obtiene el valor de la propiedad dateCreated.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDateCreated() {
            return dateCreated;
        }

        /**
         * Define el valor de la propiedad dateCreated.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDateCreated(String value) {
            this.dateCreated = value;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Group {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "url")
            @XmlSchemaType(name = "anyURI")
            protected String url;
            @XmlAttribute(name = "id")
            protected Byte id;

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Obtiene el valor de la propiedad url.
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
             * Define el valor de la propiedad url.
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
             * Obtiene el valor de la propiedad id.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getId() {
                return id;
            }

            /**
             * Define el valor de la propiedad id.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setId(Byte value) {
                this.id = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
         *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class LanguageMappedTopic {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "url")
            @XmlSchemaType(name = "anyURI")
            protected String url;
            @XmlAttribute(name = "id")
            protected Short id;
            @XmlAttribute(name = "language")
            protected String language;

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
            	
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Obtiene el valor de la propiedad url.
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
             * Define el valor de la propiedad url.
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
             * Obtiene el valor de la propiedad id.
             * 
             * @return
             *     possible object is
             *     {@link Short }
             *     
             */
            public Short getId() {
                return id;
            }

            /**
             * Define el valor de la propiedad id.
             * 
             * @param value
             *     allowed object is
             *     {@link Short }
             *     
             */
            public void setId(Short value) {
                this.id = value;
            }

            /**
             * Obtiene el valor de la propiedad language.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLanguage() {
                return language;
            }

            /**
             * Define el valor de la propiedad language.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLanguage(String value) {
                this.language = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="descriptor">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "descriptor"
        })
        public static class MeshHeading {

            @XmlElement(required = true)
            protected HealthTopics.HealthTopic.MeshHeading.Descriptor descriptor;

            /**
             * Obtiene el valor de la propiedad descriptor.
             * 
             * @return
             *     possible object is
             *     {@link HealthTopics.HealthTopic.MeshHeading.Descriptor }
             *     
             */
            public HealthTopics.HealthTopic.MeshHeading.Descriptor getDescriptor() {
                return descriptor;
            }

            /**
             * Define el valor de la propiedad descriptor.
             * 
             * @param value
             *     allowed object is
             *     {@link HealthTopics.HealthTopic.MeshHeading.Descriptor }
             *     
             */
            public void setDescriptor(HealthTopics.HealthTopic.MeshHeading.Descriptor value) {
                this.descriptor = value;
            }


            /**
             * <p>Clase Java para anonymous complex type.
             * 
             * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Descriptor {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "id")
                protected String id;

                /**
                 * Obtiene el valor de la propiedad value.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Define el valor de la propiedad value.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Obtiene el valor de la propiedad id.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getId() {
                    return id;
                }

                /**
                 * Define el valor de la propiedad id.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setId(String value) {
                    this.id = value;
                }

            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="vernacular-name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class OtherLanguage {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "vernacular-name")
            protected String vernacularName;
            @XmlAttribute(name = "url")
            @XmlSchemaType(name = "anyURI")
            protected String url;

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Obtiene el valor de la propiedad vernacularName.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVernacularName() {
                return vernacularName;
            }

            /**
             * Define el valor de la propiedad vernacularName.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVernacularName(String value) {
                this.vernacularName = value;
            }

            /**
             * Obtiene el valor de la propiedad url.
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
             * Define el valor de la propiedad url.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUrl(String value) {
                this.url = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class PrimaryInstitute {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "url")
            @XmlSchemaType(name = "anyURI")
            protected String url;

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Obtiene el valor de la propiedad url.
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
             * Define el valor de la propiedad url.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUrl(String value) {
                this.url = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}short" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class RelatedTopic {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "url")
            @XmlSchemaType(name = "anyURI")
            protected String url;
            @XmlAttribute(name = "id")
            protected Short id;

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Obtiene el valor de la propiedad url.
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
             * Define el valor de la propiedad url.
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
             * Obtiene el valor de la propiedad id.
             * 
             * @return
             *     possible object is
             *     {@link Short }
             *     
             */
            public Short getId() {
                return id;
            }

            /**
             * Define el valor de la propiedad id.
             * 
             * @param value
             *     allowed object is
             *     {@link Short }
             *     
             */
            public void setId(Short value) {
                this.id = value;
            }

        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="information-category" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *         &lt;element name="standard-description" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="language-mapped-url" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "informationCategory",
            "organization",
            "standardDescription"
        })
        public static class Site {

            @XmlElement(name = "information-category")
            protected List<String> informationCategory;
            protected List<String> organization;
            @XmlElement(name = "standard-description")
            protected List<String> standardDescription;
            @XmlAttribute(name = "title")
            protected String title;
            @XmlAttribute(name = "url")
            protected String url;
            @XmlAttribute(name = "language-mapped-url")
            @XmlSchemaType(name = "anyURI")
            protected String languageMappedUrl;

            /**
             * Gets the value of the informationCategory property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the informationCategory property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getInformationCategory().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getInformationCategory() {
                if (informationCategory == null) {
                    informationCategory = new ArrayList<String>();
                }
                return this.informationCategory;
            }

            /**
             * Gets the value of the organization property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the organization property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getOrganization().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getOrganization() {
                if (organization == null) {
                    organization = new ArrayList<String>();
                }
                return this.organization;
            }

            /**
             * Gets the value of the standardDescription property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the standardDescription property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getStandardDescription().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getStandardDescription() {
                if (standardDescription == null) {
                    standardDescription = new ArrayList<String>();
                }
                return this.standardDescription;
            }


            /**
             * Obtiene el valor de la propiedad title.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Define el valor de la propiedad title.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }

            /**
             * Obtiene el valor de la propiedad url.
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
             * Define el valor de la propiedad url.
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
             * Obtiene el valor de la propiedad languageMappedUrl.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLanguageMappedUrl() {
                return languageMappedUrl;
            }

            /**
             * Define el valor de la propiedad languageMappedUrl.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLanguageMappedUrl(String value) {
                this.languageMappedUrl = value;
            }

        }

    }
    
    public HealthTopics inicio(File f){
    	//File file = new File("mplus_topics_2013-07-12.xml");
    	
    	//File file = new File("medlinereducida.xml");
    	HealthTopics medlinexml = new HealthTopics();   	
    	try {
			JAXBContext jaxbContext = JAXBContext.newInstance(HealthTopics.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		medlinexml = (HealthTopics) jaxbUnmarshaller.unmarshal(f);
    		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return medlinexml;
    }
    
   /**
	 * Método que, dados unas criterios de búsqueda concretos sobre el XML,
	 * devuelve una lista que corresponde a los registros del XML que cumplen la condición
	 * con la paginación ya realizada.
	 * @param ruta: string -> Ruta donde se encuentra el XML.
	 * @param campo: String -> Campo de la tabla sobre el que se realiza la
	 *                         búsqueda ('*' para cualquiera).
	 * @param palabra: String -> Palabra que se busca.
	 * @param pag: int -> Página que se quiere mostrar.
	 * @param numRegistros: int -> Número de registros que se muestran por página.
	 * @return lista: ArrayList<Healthtopic> -> Lista de registros que cumplen las condiciones.
	 */
    public ArrayList<HealthTopic> BusquedaHTXML(String ruta, String campo, String palabra, int pag, int numRegistros)
    {
   	 //Para devolver sólo los elementos que nos interesan.
   	 ArrayList<HealthTopic> miLista = new ArrayList<HealthTopic>();
   	 List<HealthTopics.HealthTopic.Site> sites;
   	 File f = new File(ruta);
	    java.util.Date date = new java.util.Date();
	    HealthTopics xml = this.inicio(f);
	    HealthTopic reg;
	    // Variable booleana para controlar si se
	    // cumplen las condiciones de búsqueda.
	    Boolean cumpleCondicion;
	    
	    //Si hay algo que buscar
	    if (!palabra.equals("")) {
		    int cont = 0;
		    int cont_list = 0;
		    int elem_from = (pag - 1) * numRegistros;
		    int elem_to = elem_from + numRegistros - 1;
		    //Se tiene que cumplir que no se hayan seleccionado todos los numRegistros o que no hayamos llegado al final de la lista
		    while ((cont_list <= elem_to) && (cont < xml.getHealthTopic().size())) {
		   	 cumpleCondicion = false;
		   	 // Obtenemos el siguiente registro del XML
		   	 reg = xml.getHealthTopic().get(cont);
		   	 if (campo.equals("*")) {
		   		 //Debemos buscar en todos los campos
		   		 if ((reg.getUrl().contains(palabra)) || (reg.getLanguage().contains(palabra)) ||
			   		 (reg.getSite().toString().contains(palabra)) || (reg.getLanguageMappedTopic().toString().contains(palabra)) ||
			   		 (reg.getGroup().toString().contains(palabra)) || (reg.getOtherLanguage().toString().contains(palabra)) ||
			   		 (reg.getMeshHeading().toString().contains(palabra)) || (reg.getSeeReference().toString().contains(palabra)) ||
			   		 (reg.getRelatedTopic().toString().contains(palabra)) || (reg.getTitle().contains(palabra)) ||
			   		 (reg.getFullSummary().contains(palabra)) || (reg.getAlsoCalled().toString().contains(palabra)) ||
			   		 (reg.getPrimaryInstitute().toString().contains(palabra)))
		   			 cumpleCondicion = true;
		    	 }
		   	 // Si hay que buscar por un campo específico del XML, 
		   	 // excepto 'Site' u 'Organization'.
		   	 else if (((campo.equals("Title")) && (reg.getTitle().contains(palabra))) ||
		   		 ((campo.equals("FullSummary")) && (reg.getFullSummary().contains(palabra))))
		   		 cumpleCondicion = true;
		   	 
		   	 // Si hay que buscar por 'Site' u 'Organization' recorremos la lista
		   	 // de sites, y comparamos con el nombre de cada uno hasta encontrar
		   	 // alguna coincidencia.
		   	 if (campo.equals("Site") || campo.equals("Organization")) {
		   		 sites = reg.getSite();
		   		 for (HealthTopics.HealthTopic.Site st: sites) {
		   			 if (campo.equals("Site") && st.getTitle().contains(palabra)) {
		   				 cumpleCondicion = true;
		   			 	 break;
		   		 	 }
		   			 if (campo.equals("Organization")) {
			   			 for (String org: st.getOrganization()) {
			   				 if (org.contains(palabra)) {
			   					 cumpleCondicion = true;
			   				 	 break;
			   			 	 }
			   			 }
		   		 	 }
		   		 	 if (cumpleCondicion) break;
		   		 }				 
	   	 	 }
	    		 if (cumpleCondicion) {
	    			 if ((cont_list >= elem_from) && (cont_list <= elem_to))
	    				 miLista.add(reg);
	    			 cont_list++;
	    		 }
		    	 cont++;
		    }
	    } 
		return miLista;	
	}

   /**
    * Método que, dados unas criterios de búsqueda concretos sobre el XML,
    * devuelve una lista que corresponde a los registros del XML que cumplen la condición.
    * @param ruta: string -> Ruta donde se encuentra el XML.
    * @param campo: String -> Campo de la tabla sobre el que se realiza la
    *                         búsqueda ('*' para cualquiera).
    * @param palabra: String -> Palabra que se busca.
    * @return lista: ArrayList<Healthtopic> -> Lista de registros que cumplen las condiciones.
    */
    public ArrayList<HealthTopic> BusquedaHealthTopicXML(String ruta, String campo, String palabra) 
    {
   	 //Para devolver sólo los elementos que nos interesan.
   	 ArrayList<HealthTopic> miLista = new ArrayList<HealthTopic>();
   	 List<HealthTopics.HealthTopic.Site> sites;
   	 File f = new File(ruta);
	    java.util.Date date = new java.util.Date();
	    HealthTopics xml = this.inicio(f);
	    HealthTopic reg;
	    // Variable booleana para controlar si se
	    // cumplen las condiciones de búsqueda.
	    Boolean cumpleCondicion;
	    
	    //Si hay algo que buscar
	    if (!palabra.equals("")) {
	   	 int cont = 0;
		    // Si no hemos llegado al final de la lista.
	   	 while (cont < xml.getHealthTopic().size()) {
	   		 cumpleCondicion = false;
	   		 // Obtenemos el siguiente registro del XML
		   	 reg = xml.getHealthTopic().get(cont);
		   	 if (campo.equals("*")) {
		   		 //Debemos buscar en todos los campos
		   		 if ((reg.getUrl().contains(palabra)) || (reg.getLanguage().contains(palabra)) ||
		   			 (reg.getSite().toString().contains(palabra)) || (reg.getLanguageMappedTopic().toString().contains(palabra)) ||
		   			 (reg.getGroup().toString().contains(palabra)) || (reg.getOtherLanguage().toString().contains(palabra)) ||
		   			 (reg.getMeshHeading().toString().contains(palabra)) || (reg.getSeeReference().toString().contains(palabra)) ||
		   			 (reg.getRelatedTopic().toString().contains(palabra)) || (reg.getTitle().contains(palabra)) ||
		   			 (reg.getFullSummary().contains(palabra)) || (reg.getAlsoCalled().toString().contains(palabra)) ||
		   			 (reg.getPrimaryInstitute().toString().contains(palabra)))
		   			 cumpleCondicion = true;
		   	 }
		    	 // Si hay que buscar por un campo específico del XML, 
		   	 // excepto 'Site' u 'Organization'.
		   	 else if (((campo.equals("Title")) && (reg.getTitle().contains(palabra))) ||
		   		 ((campo.equals("FullSummary")) && (reg.getFullSummary().contains(palabra))))
		   		 cumpleCondicion = true;
		   	 
		   	 // Si hay que buscar por 'Site' u 'Organization' recorremos la lista
		   	 // de sites, y comparamos con el nombre de cada uno hasta encontrar
		   	 // alguna coincidencia.
		   	 if (campo.equals("Site") || campo.equals("Organization")) {
		   		 sites = reg.getSite();
		   		 for (HealthTopics.HealthTopic.Site st: sites) {
		   			 if (campo.equals("Site") && st.getTitle().contains(palabra)) {
		   				 cumpleCondicion = true;
		   			 	 break;
		   		 	 }
		   			 if (campo.equals("Organization")) {
			   			 for (String org: st.getOrganization()) {
			   				 if (org.contains(palabra)) {
			   					 cumpleCondicion = true;
			   				 	 break;
			   			 	 }
			   			 }
		   		 	 }
		   		 	 if (cumpleCondicion) break;
		   		 }				 
	   	 	 }
		   		 
		   	 if (cumpleCondicion) miLista.add(reg);
		   	 cont++;
		    }
	    }
		 return miLista;
    }
    
   /**
 	 * Método que, dados unas criterios de búsqueda concretos, devuelve una lista que corresponde
 	 * a los registros del fichero XML que cumplen la condición, buscando tanto en el propio XML como 
 	 * en la tabla 'bennett' de la BD 'medlinebennett' de MySQL
 	 * devuelve una lista que corresponde a los registros del XML que cumplen la condición.
 	 * @param ruta: string -> Ruta donde se encuentra el XML.
 	 * @param campo: String -> Campo de la tabla sobre el que se realiza la
 	 *                         búsqueda ('*' para cualquiera).
 	 * @param palabra: String -> Palabra que se busca.
 	 * @return lista: ArrayList<Healthtopic> -> Lista de registros que cumplen las condiciones.
 	 */
    public ArrayList<HealthTopic> BusquedaMixtaMedlineBennett(String ruta, String campo, String palabra)
 	 {
   	 //Para devolver sólo los elementos que nos interesan.
   	 ArrayList<HealthTopic> miLista = new ArrayList<HealthTopic>();
   	 List<HealthTopics.HealthTopic.Site> sites;
   	 File f = new File(ruta);
   	 java.util.Date date = new java.util.Date();
   	 HealthTopics xml = this.inicio(f);
   	 HealthTopic reg;
   	 // Variable para acceder a la base de datos de MySQL.
   	 BBDD bd = new BBDD();
       // Variable booleana para controlar si se
   	 // cumplen las condiciones de búsqueda.
   	 Boolean cumpleCondicion;
   	
   	 //Si hay algo que buscar
   	 if (!palabra.equals("")) {
   		 int cont = 0;
   		 // Mientras no hayamos llegado al final de la lista de HealthTopic.
   		 while (cont < xml.getHealthTopic().size()) {
   			 cumpleCondicion = false; 
   			 // Obtenemos el siguiente registro del XML
   			 reg = xml.getHealthTopic().get(cont);
   			 // Si tenemos que buscar en todos los campos del XML.
   			 if (campo.equals("*")) {
   				 if ((reg.getUrl().contains(palabra)) || (reg.getLanguage().contains(palabra)) ||
   					 (reg.getSite().toString().contains(palabra)) || (reg.getLanguageMappedTopic().toString().contains(palabra)) ||
   					 (reg.getGroup().toString().contains(palabra)) || (reg.getOtherLanguage().toString().contains(palabra)) ||
 	    				 (reg.getMeshHeading().toString().contains(palabra)) || (reg.getSeeReference().toString().contains(palabra)) ||
 	    				 (reg.getRelatedTopic().toString().contains(palabra)) || (reg.getTitle().contains(palabra)) ||
 	    				 (reg.getFullSummary().contains(palabra)) || (reg.getAlsoCalled().toString().contains(palabra)) ||
 	    				 (reg.getPrimaryInstitute().toString().contains(palabra)))
 	    				 cumpleCondicion = true;
 		    	 }
   			 // Si hay que buscar por un campo específico del XML, 
		   	 // excepto 'Site' u 'Organization'.
		   	 else if (((campo.equals("Title")) && (reg.getTitle().contains(palabra))) ||
		   		 ((campo.equals("FullSummary")) && (reg.getFullSummary().contains(palabra))))
		   		 cumpleCondicion = true;
		   	 
		   	 // Si hay que buscar por 'Site' u 'Organization' recorremos la lista
		   	 // de sites, y comparamos con el nombre de cada uno hasta encontrar
		   	 // alguna coincidencia.
		   	 if (campo.equals("Site") || campo.equals("Organization")) {
		   		 sites = reg.getSite();
		   		 for (HealthTopics.HealthTopic.Site st: sites) {
		   			 if (campo.equals("Site") && st.getTitle().contains(palabra)) {
		   				 cumpleCondicion = true;
		   			 	 break;
		   		 	 }
		   			 if (campo.equals("Organization")) {
			   			 for (String org: st.getOrganization()) {
			   				 if (org.contains(palabra)) {
			   					 cumpleCondicion = true;
			   				  	 break;
			   			 	 }
			   			 }
		   		 	 }
		   		 	 if (cumpleCondicion) break;
		   		 }				 
	   	    }
   			
   			 // Si no se cumplen las condiciones en el XML, determinanos si 
   			 // además es necesaria la búsqueda en la tabla 'bennett' de MySQL, 
   			 // en función de si hay que buscar en todos los campos o en 
   			 // alguno específico de dicha tabla ('City' o 'State').
				 if ((!cumpleCondicion) && ((campo.equals("*")) || 
					 (campo.equals("City")) || (campo.equals("State")))) {
					 bd.abrirConexion();
					 cumpleCondicion = (bd.buscaSitesEnBennett(reg.site, campo, palabra).size() > 0);
					 bd.cerrarConexion();
				 }
				
				 // Si finalmente se cumplen las condiciones de búsqueda, añadimos el registro
				 // a la lista.
   			 if (cumpleCondicion) miLista.add(reg);
 		    	 cont++;
 		    }
   	} 
   	return miLista;	
 	}
    
    
   /**
	 * Método que, dados unas criterios de búsqueda concretos sobre el XML,
	 * devuelve una lista que corresponde a los registros del XML que cumplen la condición.
	 * @param ruta: string -> Ruta donde se encuentra el XML.
	 * @param campo: String -> Campo de la tabla sobre el que se realiza la
	 *                         búsqueda ('*' para cualquiera).
	 * @param palabra: String -> Palabra que se busca.
	 * @return lista: ArrayList<Healthtopic> -> Lista de registros que cumplen las condiciones.
	 */
   public ArrayList<HealthTopic> PaginacionBusquedaXML(ArrayList<HealthTopic> lista, int pag, int numRegistro)
	{
   	ArrayList<HealthTopic> sublista = new ArrayList<HealthTopic>();
   	
    	int fromIndex = (pag - 1) * numRegistro;
    	int toIndex = fromIndex + numRegistro - 1;
    	if (toIndex > lista.size() - 1) toIndex = lista.size() - 1;
    	if (toIndex >= 0) 
    		sublista.addAll(lista.subList(fromIndex, toIndex + 1));
    	return sublista;
	}


    /*public static void main(String[] args) {

    	try {

    		//File file = new File("medlinereducida.xml");
    		File file = new File("mplus_topics_2013-07-12.xml");
    		JAXBContext jaxbContext = JAXBContext.newInstance(HealthTopics.class);

    		

    		BBDD bd = new BBDD();
    		bd.abrirConexion();
    		
    		 * BENNETT
    		 
    		
    		String csvFile = "HospListSept2012.csv";
    		BufferedReader br = null;
    		String line = "";
    		String cvsSplitBy = ",";
    		int numlinea = 1;
    	 
    		try {
    	 
    			br = new BufferedReader(new FileReader(csvFile));
    			while ((line = br.readLine()) != null && numlinea <20) {
    			  if(numlinea>1){
    			    // use comma as separator
    				String[] registro = line.split(cvsSplitBy,25);
    				java.sql.Date twitfu = null;
    				java.sql.Date youtubedate = null;
    				if(registro[12]!=null) twitfu = bd.castFecha(registro[12]);
    				if(registro[10]!=null) youtubedate = bd.castFecha(registro[10]);
    				
    				Bennett ben = new Bennett(registro[6],registro[1],registro[15],registro[3],
    						registro[23],registro[20],registro[14],registro[21],registro[16],
    						registro[8],registro[19],registro[17],registro[0],registro[5],
    						registro[18],registro[7],registro[22],registro[4],registro[12],
    						twitfu,registro[13],registro[2],registro[9],youtubedate,registro[24]);
    				
    				ben.insert(bd.getConnection());
    				
    				System.out.print("1. " + registro[0]);
    				System.out.print("2. " + registro[1]);
    				System.out.print("3. " + registro[2]);
    				System.out.print("4. " + registro[3]);
    				System.out.print("5. " + registro[4]);
    				System.out.print("6. " + registro[5]);
    				System.out.print("7. " + registro[6]);
    				System.out.print("8. " + registro[7]);
    				System.out.print("9. " + registro[8]);
    				System.out.print("10. " + registro[9]);
    				System.out.print("11. " + registro[10]);
    				System.out.print("12. " + registro[11]);
    				System.out.print("13. " + registro[12]);
    				System.out.print("14. " + registro[13]);
    				System.out.print("15. " + registro[14]);
    				System.out.print("16. " + registro[15]);
    				System.out.print("17. " + registro[16]);
    				System.out.print("18. " + registro[17]);
    				System.out.print("19. " + registro[18]);
    				System.out.print("20. " + registro[19]);
    				System.out.print("21. " + registro[20]);
    				System.out.print("22. " + registro[21]);
    				System.out.print("23. " + registro[22]);
    				System.out.print("24. " + registro[23]);
    				System.out.print("25. " + registro[24]);
    				System.out.println("---------------------------------------------------------------");
    			  }
    			  numlinea++;
    	    	}
    	 
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			if (br != null) {
    				try {
    					br.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		}
    	 
    		System.out.println("Done");
    		System.exit(0);
    		
    		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    		HealthTopics medlinexml = (HealthTopics) jaxbUnmarshaller.unmarshal(file);
    		System.out.println(medlinexml.getDateGenerated());
    		System.out.println(medlinexml.getTotal());
    		
    		
    		
    		 * METADATA
    		 

    		Metadata md = new Metadata();
    		md.setTotal(medlinexml.getTotal());
    		md.setFecha(bd.castFecha(medlinexml.getDateGenerated()));
    		md.insert(bd.getConnection());

    		*//**
    		 * En la tabla Site se incluye un campo clave autonumérico para la normalización de la base de
    		 * datos. Para evitar consultar el Id de cada Site al incluir los registros de Sitedescription,
    		 * Siteinfocategory y Siteorganization, se incluye esta variable.
    		 *//*
    		int siteId = 1;
    		long start = System.currentTimeMillis();
    		System.out.println("Hora de comienzo: " + new Date());

    		for (int indice = 296; indice < medlinexml.getHealthTopic().size(); indice++){
    			long end = System.currentTimeMillis();
    			long elapsed = end - start;
    			int pro = indice + 1;
    			System.out.println("Registro " + pro + " de 1.888." );
    			System.out.printf("%d min, %d sec", 
    					TimeUnit.MILLISECONDS.toMinutes(elapsed),
    					TimeUnit.MILLISECONDS.toSeconds(elapsed) - 
    					TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsed))
    					);
    			System.out.println("-----------------------------");
    			*//**
    			 * MySQL sólo admite como formato fecha la clase java.sql.Date. En el parseo la fecha se devuelve
    			 * como un String en formato yyyy-mm-dd. Es necesario convertir el 'String' en tipo java.util.Date
    			 * para pasarlo a formato long (milisegundos) que es uno de los parámetros que utiliza uno de los
    			 * constructores de java.sql.Date.
    			 *//*

    			Date fec = new Date(1900-01-01);
    			java.sql.Date fecha = new java.sql.Date(1900-01-01);
    			try {
    				fec = new SimpleDateFormat("MM/dd/yyyy").parse(medlinexml.getHealthTopic().get(indice).getDateCreated());
    				long fec1 = fec.getTime();
    				fecha = new java.sql.Date(fec1);

    			} catch (ParseException e) {
    				System.out.println("Formato de fecha erróneo. El formato esperado es MM/dd/yyyy.");
    				e.printStackTrace();
    			}

    			*//**
    			 * Un HealthTopic puede o no tener un PrimaryInstitute y su respectiva URL. Si se pasase directamente
    			 * a la base de datos y el valor estuviera vacío, nos daría error.
    			 *//*
    			//TODO posiblemente esta parte no sea necesaria, ya se ha incluido modificación en getPrimaryInstitute()	

    			String priminst = "";
    			if(medlinexml.getHealthTopic().get(indice).getPrimaryInstitute().getValue() != null)
    				priminst = medlinexml.getHealthTopic().get(indice).getPrimaryInstitute().getValue();
    			String priminsturl = "";
    			if(medlinexml.getHealthTopic().get(indice).getPrimaryInstitute().getUrl() != null)
    				priminst = medlinexml.getHealthTopic().get(indice).getPrimaryInstitute().getUrl();
    			//TODO se cargan los datos mediante el método previamente creado en BBDD, aunque siguiendo
    			//la lógica del programa se deberían cargar desde el bean Healthtopic.
    			bd.ejecutarHealthtopic((int) medlinexml.getHealthTopic().get(indice).getId(),
    					medlinexml.getHealthTopic().get(indice).getTitle(),
    					medlinexml.getHealthTopic().get(indice).getLanguage(),
    					fecha,
    					medlinexml.getHealthTopic().get(indice).getUrl(),
    					medlinexml.getHealthTopic().get(indice).getFullSummary(),
    					priminst,
    					priminsturl);

    			
    			 * ALSO CALLED
    			 
    			for (int j=0; j<medlinexml.getHealthTopic().get(indice).getAlsoCalled().size();j++){
    				Alsocalled ac = new Alsocalled();
    				ac.setHealthTopic_Id(medlinexml.getHealthTopic().get(indice).getId());
    				ac.setAlsoCalled(medlinexml.getHealthTopic().get(indice).getAlsoCalled().get(j));
    				ac.insert(bd.getConnection());
    			}

    			
    			 * GROUP
    			 
    			for (int j=0; j<medlinexml.getHealthTopic().get(indice).getGroup().size();j++){
    				Group gr = new Group();
    				gr.setHealthTopic_Id(medlinexml.getHealthTopic().get(indice).getId());
    				gr.setIdGroup(medlinexml.getHealthTopic().get(indice).getGroup().get(j).getId());
    				gr.setHealthTopic_Id(medlinexml.getHealthTopic().get(indice).getId());
    				gr.setName(medlinexml.getHealthTopic().get(indice).getGroup().get(j).getValue());
    				gr.setUrl(medlinexml.getHealthTopic().get(indice).getGroup().get(j).getUrl());
    				gr.insert(bd.getConnection());
    			}

    			
    			 * LANGUAGEMAPPEDTOPIC
    			 

    			if(medlinexml.getHealthTopic().get(indice).getLanguageMappedTopic().getValue() != null){
    				Languagemappedtopic lmt = new Languagemappedtopic();
    				lmt.setHealthTopics_Id(medlinexml.getHealthTopic().get(indice).getId());
    				lmt.setIdLanguageMappedTopic(medlinexml.getHealthTopic().get(indice).getLanguageMappedTopic().getId());
    				lmt.setUrl(medlinexml.getHealthTopic().get(indice).getLanguageMappedTopic().getUrl());
    				lmt.setLanguage(medlinexml.getHealthTopic().get(indice).getLanguageMappedTopic().getLanguage());
    				lmt.setName(medlinexml.getHealthTopic().get(indice).getLanguageMappedTopic().getValue());
    				lmt.insert(bd.getConnection());
    			}

    			
    			 * MESHHEADING
    			 

    			for (int j=0; j<medlinexml.getHealthTopic().get(indice).getMeshHeading().size();j++){
    				Meshheading mh = new Meshheading();
    				mh.setHealthTopic_Id(medlinexml.getHealthTopic().get(indice).getId());
    				mh.setIdMeshHeading(medlinexml.getHealthTopic().get(indice).getMeshHeading().get(j).getDescriptor().getId());
    				mh.setDescriptor(medlinexml.getHealthTopic().get(indice).getMeshHeading().get(j).getDescriptor().getValue());
    				mh.insert(bd.getConnection());
    			}

    			
    			 * OTHERLANGUAGE
    			 

    			for (int j=0; j<medlinexml.getHealthTopic().get(indice).getOtherLanguage().size();j++){
    				Otherlanguage ol = new Otherlanguage();
    				ol.setHealthTopic_Id(medlinexml.getHealthTopic().get(indice).getId());
    				ol.setName(medlinexml.getHealthTopic().get(indice).getOtherLanguage().get(j).getValue());
    				ol.setVernacularName(medlinexml.getHealthTopic().get(indice).getOtherLanguage().get(j).getVernacularName());
    				ol.setUrl(medlinexml.getHealthTopic().get(indice).getOtherLanguage().get(j).getUrl());
    				ol.insert(bd.getConnection());
    			}

    			
    			 * RELATEDTOPIC
    			 

    			for (int j=0; j<medlinexml.getHealthTopic().get(indice).getRelatedTopic().size();j++){
    				Relatedtopic rt = new Relatedtopic();
    				rt.setHealthTopic_Id(medlinexml.getHealthTopic().get(indice).getId());
    				rt.setName(medlinexml.getHealthTopic().get(indice).getRelatedTopic().get(j).getValue());
    				rt.setIdRelatedTopic(medlinexml.getHealthTopic().get(indice).getRelatedTopic().get(j).getId());
    				rt.setUrl(medlinexml.getHealthTopic().get(indice).getRelatedTopic().get(j).getUrl());
    				rt.insert(bd.getConnection());
    			}

    			
    			 * SEEREFERENCE
    			 

    			for (int j=0; j<medlinexml.getHealthTopic().get(indice).getSeeReference().size();j++){
    				Seereference sr = new Seereference();
    				sr.setHealthTopic_Id(medlinexml.getHealthTopic().get(indice).getId());
    				sr.setReference(medlinexml.getHealthTopic().get(indice).getSeeReference().get(j));
    				sr.insert(bd.getConnection());
    			}

    			
    			 * SITE
    			 

    			for (int j=0; j<medlinexml.getHealthTopic().get(indice).getSite().size();j++){
    				Site si = new Site();
    				Sitedescription sd = new Sitedescription();
    				Siteinfocategory sic = new Siteinfocategory();
    				Siteorganization so = new Siteorganization();
    				
    				 * SITE
    				 
    				si.setHealthtopic(medlinexml.getHealthTopic().get(indice).getId());
    				si.setName(medlinexml.getHealthTopic().get(indice).getSite().get(j).getTitle());
    				si.setLanguageMappedURL(medlinexml.getHealthTopic().get(indice).getSite().get(j).getLanguageMappedUrl());
    				si.setUrl(medlinexml.getHealthTopic().get(indice).getSite().get(j).getUrl());
    				si.insert(bd.getConnection());
    				
    				 * SITEDESCRIPTION
    				 
    				for (int k=0; k<medlinexml.getHealthTopic().get(indice).getSite().get(j).getStandardDescription().size(); k++){
    					sd.setSite_URL_Id(siteId);
    					sd.setDescription(medlinexml.getHealthTopic().get(indice).getSite().get(j).getStandardDescription().get(k));
    					sd.insert(bd.getConnection());
    				}
    				
    				 * SITEINFOCATEGORY
    				 
    				for (int l=0; l<medlinexml.getHealthTopic().get(indice).getSite().get(j).getInformationCategory().size(); l++){
    					sic.setSite_URL_Id(siteId);
    					sic.setInfoCategory(medlinexml.getHealthTopic().get(indice).getSite().get(j).getInformationCategory().get(l));
    					sic.insert(bd.getConnection());
    				}
    				
    				 * SITEORGANIZATION
    				 
    				for (int m=0; m<medlinexml.getHealthTopic().get(indice).getSite().get(j).getOrganization().size(); m++){
    					so.setSite_URL_Id(siteId);
    					so.setOrganization(medlinexml.getHealthTopic().get(indice).getSite().get(j).getOrganization().get(m));
    					so.insert(bd.getConnection());
    				}
    				siteId++;
    			}



    		}

    	} catch (JAXBException e) {
    		e.printStackTrace();
    	}
    }*/

}

