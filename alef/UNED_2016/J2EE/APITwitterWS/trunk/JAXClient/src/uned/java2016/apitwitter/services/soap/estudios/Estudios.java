
package uned.java2016.apitwitter.services.soap.estudios;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estudios complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="estudios">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estudio" type="{http://estudios.soap.services.apitwitter.java2016.uned/}clinicalStudy" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estudios", propOrder = {
    "estudio"
})
public class Estudios {

    protected List<ClinicalStudy> estudio;

    /**
     * Gets the value of the estudio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estudio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstudio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClinicalStudy }
     * 
     * 
     */
    public List<ClinicalStudy> getEstudio() {
        if (estudio == null) {
            estudio = new ArrayList<ClinicalStudy>();
        }
        return this.estudio;
    }

}
