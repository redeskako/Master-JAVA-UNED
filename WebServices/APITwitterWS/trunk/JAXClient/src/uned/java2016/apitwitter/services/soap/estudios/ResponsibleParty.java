
package uned.java2016.apitwitter.services.soap.estudios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responsibleParty complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responsibleParty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="investigatorAffilation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="investigatorFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="investigatorTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responsiblePartyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responsibleParty", propOrder = {
    "investigatorAffilation",
    "investigatorFullName",
    "investigatorTitle",
    "responsiblePartyType"
})
public class ResponsibleParty {

    protected String investigatorAffilation;
    protected String investigatorFullName;
    protected String investigatorTitle;
    protected String responsiblePartyType;

    /**
     * Obtiene el valor de la propiedad investigatorAffilation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvestigatorAffilation() {
        return investigatorAffilation;
    }

    /**
     * Define el valor de la propiedad investigatorAffilation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvestigatorAffilation(String value) {
        this.investigatorAffilation = value;
    }

    /**
     * Obtiene el valor de la propiedad investigatorFullName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvestigatorFullName() {
        return investigatorFullName;
    }

    /**
     * Define el valor de la propiedad investigatorFullName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvestigatorFullName(String value) {
        this.investigatorFullName = value;
    }

    /**
     * Obtiene el valor de la propiedad investigatorTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvestigatorTitle() {
        return investigatorTitle;
    }

    /**
     * Define el valor de la propiedad investigatorTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvestigatorTitle(String value) {
        this.investigatorTitle = value;
    }

    /**
     * Obtiene el valor de la propiedad responsiblePartyType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsiblePartyType() {
        return responsiblePartyType;
    }

    /**
     * Define el valor de la propiedad responsiblePartyType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsiblePartyType(String value) {
        this.responsiblePartyType = value;
    }

}
