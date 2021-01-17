
package servlets;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servlets package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SoapSearchResponse_QNAME = new QName("http://servlets/", "SoapSearchResponse");
    private final static QName _SoapSearch_QNAME = new QName("http://servlets/", "SoapSearch");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servlets
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SoapSearchResponse }
     * 
     */
    public SoapSearchResponse createSoapSearchResponse() {
        return new SoapSearchResponse();
    }

    /**
     * Create an instance of {@link SoapSearch }
     * 
     */
    public SoapSearch createSoapSearch() {
        return new SoapSearch();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapSearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servlets/", name = "SoapSearchResponse")
    public JAXBElement<SoapSearchResponse> createSoapSearchResponse(SoapSearchResponse value) {
        return new JAXBElement<SoapSearchResponse>(_SoapSearchResponse_QNAME, SoapSearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SoapSearch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servlets/", name = "SoapSearch")
    public JAXBElement<SoapSearch> createSoapSearch(SoapSearch value) {
        return new JAXBElement<SoapSearch>(_SoapSearch_QNAME, SoapSearch.class, null, value);
    }

}
