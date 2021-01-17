
package uned.java2016.apitwitter.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getClinicalsResponse", namespace = "http://services.apitwitter.java2016.uned/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getClinicalsResponse", namespace = "http://services.apitwitter.java2016.uned/")
public class GetClinicalsResponse {

    @XmlElement(name = "return", namespace = "")
    private uned.java2016.apitwitter.services.rs.jaxb.Clinicals _return;

    /**
     * 
     * @return
     *     returns Clinicals
     */
    public uned.java2016.apitwitter.services.rs.jaxb.Clinicals getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(uned.java2016.apitwitter.services.rs.jaxb.Clinicals _return) {
        this._return = _return;
    }

}
