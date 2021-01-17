
package uned.java2016.apitwitter.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNeighbours complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNeighbours">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hashtag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNeighbours", propOrder = {
    "hashtag"
})
public class GetNeighbours {

    protected String hashtag;

    /**
     * Gets the value of the hashtag property.
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
     * Sets the value of the hashtag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashtag(String value) {
        this.hashtag = value;
    }

}
