
package uned.java2016.apitwitter.services.soap.estudios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getByNCTResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getByNCTResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://estudios.soap.services.apitwitter.java2016.uned/}clinicalStudy" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByNCTResponse", propOrder = {
    "_return"
})
public class GetByNCTResponse {

    @XmlElement(name = "return")
    protected ClinicalStudy _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link ClinicalStudy }
     *     
     */
    public ClinicalStudy getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link ClinicalStudy }
     *     
     */
    public void setReturn(ClinicalStudy value) {
        this._return = value;
    }

}
