
package es.uned.sw.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.12
 * Fri Sep 12 17:32:22 CEST 2014
 * Generated source version: 2.7.12
 */

@XmlRootElement(name = "sumarResponse", namespace = "http://sw.uned.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sumarResponse", namespace = "http://sw.uned.es/")

public class SumarResponse {

    @XmlElement(name = "return")
    private java.lang.String _return;

    public java.lang.String getReturn() {
        return this._return;
    }

    public void setReturn(java.lang.String new_return)  {
        this._return = new_return;
    }

}

