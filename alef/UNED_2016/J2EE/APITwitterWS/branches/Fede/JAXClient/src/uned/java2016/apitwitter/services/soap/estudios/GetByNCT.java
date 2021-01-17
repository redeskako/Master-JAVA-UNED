
package uned.java2016.apitwitter.services.soap.estudios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getByNCT complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getByNCT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByNCT", propOrder = {
    "nct"
})
public class GetByNCT {

    protected String nct;

    /**
     * Obtiene el valor de la propiedad nct.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNct() {
        return nct;
    }

    /**
     * Define el valor de la propiedad nct.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNct(String value) {
        this.nct = value;
    }

}
