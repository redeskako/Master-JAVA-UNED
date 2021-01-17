
package uned.java2016.apitwitter.services.soap.estudios.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getByHashtagResponse", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByHashtagResponse", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/")
public class GetByHashtagResponse {

    @XmlElement(name = "return", namespace = "")
    private uned.java2016.apitwitter.services.rs.jaxb.Estudios _return;

    /**
     * 
     * @return
     *     returns Estudios
     */
    public uned.java2016.apitwitter.services.rs.jaxb.Estudios getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(uned.java2016.apitwitter.services.rs.jaxb.Estudios _return) {
        this._return = _return;
    }

}
