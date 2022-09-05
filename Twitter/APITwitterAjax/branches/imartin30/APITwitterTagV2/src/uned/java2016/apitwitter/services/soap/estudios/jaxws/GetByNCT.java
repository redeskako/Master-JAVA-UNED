
package uned.java2016.apitwitter.services.soap.estudios.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getByNCT", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByNCT", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/")
public class GetByNCT {

    @XmlElement(name = "nct", namespace = "")
    private String nct;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNct() {
        return this.nct;
    }

    /**
     * 
     * @param nct
     *     the value for the nct property
     */
    public void setNct(String nct) {
        this.nct = nct;
    }

}
