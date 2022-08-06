
package uned.java2016.apitwitter.services.soap.estudios.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getByHashtag", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByHashtag", namespace = "http://estudios.soap.services.apitwitter.java2016.uned/", propOrder = {
    "hashtag",
    "count"
})
public class GetByHashtag {

    @XmlElement(name = "hashtag", namespace = "")
    private String hashtag;
    @XmlElement(name = "count", namespace = "")
    private int count;

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

    /**
     * 
     * @return
     *     returns int
     */
    public int getCount() {
        return this.count;
    }

    /**
     * 
     * @param count
     *     the value for the count property
     */
    public void setCount(int count) {
        this.count = count;
    }

}
