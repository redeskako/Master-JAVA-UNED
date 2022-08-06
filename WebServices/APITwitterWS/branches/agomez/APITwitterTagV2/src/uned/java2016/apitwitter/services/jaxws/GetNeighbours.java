
package uned.java2016.apitwitter.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getNeighbours", namespace = "http://services.apitwitter.java2016.uned/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNeighbours", namespace = "http://services.apitwitter.java2016.uned/")
public class GetNeighbours {

    @XmlElement(name = "hashtag", namespace = "")
    private String hashtag;

    /**
     * 
     * @return
     *     returns String
     */
    public String getHashtag() {
        return this.hashtag;
    }

    /**
     * 
     * @param hashtag
     *     the value for the hashtag property
     */
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

}
