
package uned.java2016.apitwitter.services.soap.estudios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getByHashtag complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getByHashtag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hashtag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByHashtag", propOrder = {
    "hashtag",
    "count"
})
public class GetByHashtag {

    protected String hashtag;
    protected int count;

    /**
     * Obtiene el valor de la propiedad hashtag.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashtag() {
        return hashtag;
    }

    /**
     * Define el valor de la propiedad hashtag.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashtag(String value) {
        this.hashtag = value;
    }

    /**
     * Obtiene el valor de la propiedad count.
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * Define el valor de la propiedad count.
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

}
