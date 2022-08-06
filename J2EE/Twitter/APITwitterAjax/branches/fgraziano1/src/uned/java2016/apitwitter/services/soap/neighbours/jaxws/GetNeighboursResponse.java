
package uned.java2016.apitwitter.services.soap.neighbours.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getNeighboursResponse", namespace = "http://neighbours.soap.services.apitwitter.java2016.uned/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNeighboursResponse", namespace = "http://neighbours.soap.services.apitwitter.java2016.uned/")
public class GetNeighboursResponse {

    @XmlElement(name = "return", namespace = "")
    private uned.java2016.apitwitter.services.rs.jaxb.Neighbours _return;

    /**
     * 
     * @return
     *     returns Neighbours
     */
    public uned.java2016.apitwitter.services.rs.jaxb.Neighbours getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(uned.java2016.apitwitter.services.rs.jaxb.Neighbours _return) {
        this._return = _return;
    }

}
