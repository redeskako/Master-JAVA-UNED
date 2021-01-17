
package uned.java2016.apitwitter.services.soap.estudios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para clinicalStudy complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="clinicalStudy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="briefSummary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="briefTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detailedDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstReceivedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="hashtags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastChangedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="locationFacilityFame" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nctId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officialTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overallStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oversightInfoAuthority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryOutcomeMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responsibleParty" type="{http://estudios.soap.services.apitwitter.java2016.uned/}responsibleParty" minOccurs="0"/>
 *         &lt;element name="studyDesign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="verificationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clinicalStudy", propOrder = {
    "briefSummary",
    "briefTitle",
    "detailedDescription",
    "firstReceivedDate",
    "hashtags",
    "lastChangedDate",
    "locationFacilityFame",
    "nctId",
    "officialTitle",
    "organization",
    "overallStatus",
    "oversightInfoAuthority",
    "primaryOutcomeMeasure",
    "responsibleParty",
    "studyDesign",
    "verificationDate"
})
public class ClinicalStudy {

    protected String briefSummary;
    protected String briefTitle;
    protected String detailedDescription;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar firstReceivedDate;
    protected String hashtags;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChangedDate;
    protected String locationFacilityFame;
    protected String nctId;
    protected String officialTitle;
    protected String organization;
    protected String overallStatus;
    protected String oversightInfoAuthority;
    protected String primaryOutcomeMeasure;
    protected ResponsibleParty responsibleParty;
    protected String studyDesign;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar verificationDate;

    /**
     * Obtiene el valor de la propiedad briefSummary.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBriefSummary() {
        return briefSummary;
    }

    /**
     * Define el valor de la propiedad briefSummary.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBriefSummary(String value) {
        this.briefSummary = value;
    }

    /**
     * Obtiene el valor de la propiedad briefTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBriefTitle() {
        return briefTitle;
    }

    /**
     * Define el valor de la propiedad briefTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBriefTitle(String value) {
        this.briefTitle = value;
    }

    /**
     * Obtiene el valor de la propiedad detailedDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailedDescription() {
        return detailedDescription;
    }

    /**
     * Define el valor de la propiedad detailedDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailedDescription(String value) {
        this.detailedDescription = value;
    }

    /**
     * Obtiene el valor de la propiedad firstReceivedDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstReceivedDate() {
        return firstReceivedDate;
    }

    /**
     * Define el valor de la propiedad firstReceivedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstReceivedDate(XMLGregorianCalendar value) {
        this.firstReceivedDate = value;
    }

    /**
     * Obtiene el valor de la propiedad hashtags.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashtags() {
        return hashtags;
    }

    /**
     * Define el valor de la propiedad hashtags.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashtags(String value) {
        this.hashtags = value;
    }

    /**
     * Obtiene el valor de la propiedad lastChangedDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastChangedDate() {
        return lastChangedDate;
    }

    /**
     * Define el valor de la propiedad lastChangedDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastChangedDate(XMLGregorianCalendar value) {
        this.lastChangedDate = value;
    }

    /**
     * Obtiene el valor de la propiedad locationFacilityFame.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationFacilityFame() {
        return locationFacilityFame;
    }

    /**
     * Define el valor de la propiedad locationFacilityFame.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationFacilityFame(String value) {
        this.locationFacilityFame = value;
    }

    /**
     * Obtiene el valor de la propiedad nctId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNctId() {
        return nctId;
    }

    /**
     * Define el valor de la propiedad nctId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNctId(String value) {
        this.nctId = value;
    }

    /**
     * Obtiene el valor de la propiedad officialTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficialTitle() {
        return officialTitle;
    }

    /**
     * Define el valor de la propiedad officialTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficialTitle(String value) {
        this.officialTitle = value;
    }

    /**
     * Obtiene el valor de la propiedad organization.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Define el valor de la propiedad organization.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganization(String value) {
        this.organization = value;
    }

    /**
     * Obtiene el valor de la propiedad overallStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverallStatus() {
        return overallStatus;
    }

    /**
     * Define el valor de la propiedad overallStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverallStatus(String value) {
        this.overallStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad oversightInfoAuthority.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOversightInfoAuthority() {
        return oversightInfoAuthority;
    }

    /**
     * Define el valor de la propiedad oversightInfoAuthority.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOversightInfoAuthority(String value) {
        this.oversightInfoAuthority = value;
    }

    /**
     * Obtiene el valor de la propiedad primaryOutcomeMeasure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryOutcomeMeasure() {
        return primaryOutcomeMeasure;
    }

    /**
     * Define el valor de la propiedad primaryOutcomeMeasure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryOutcomeMeasure(String value) {
        this.primaryOutcomeMeasure = value;
    }

    /**
     * Obtiene el valor de la propiedad responsibleParty.
     * 
     * @return
     *     possible object is
     *     {@link ResponsibleParty }
     *     
     */
    public ResponsibleParty getResponsibleParty() {
        return responsibleParty;
    }

    /**
     * Define el valor de la propiedad responsibleParty.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponsibleParty }
     *     
     */
    public void setResponsibleParty(ResponsibleParty value) {
        this.responsibleParty = value;
    }

    /**
     * Obtiene el valor de la propiedad studyDesign.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyDesign() {
        return studyDesign;
    }

    /**
     * Define el valor de la propiedad studyDesign.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyDesign(String value) {
        this.studyDesign = value;
    }

    /**
     * Obtiene el valor de la propiedad verificationDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerificationDate() {
        return verificationDate;
    }

    /**
     * Define el valor de la propiedad verificationDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerificationDate(XMLGregorianCalendar value) {
        this.verificationDate = value;
    }

}
