
package uned.java2016.apitwitter.services.soap.estudios.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getByNCTResponse", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByNCTResponse", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/")
public class GetByNCTResponse {

    @XmlElement(name = "return", namespace = "")
    private uned.java2016.apitwitter.dao.ClinicalStudy _return;

    /**
     * 
     * @return
     *     returns ClinicalStudy
     */
    public uned.java2016.apitwitter.dao.ClinicalStudy getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(uned.java2016.apitwitter.dao.ClinicalStudy _return) {
        this._return = _return;
    }

}
