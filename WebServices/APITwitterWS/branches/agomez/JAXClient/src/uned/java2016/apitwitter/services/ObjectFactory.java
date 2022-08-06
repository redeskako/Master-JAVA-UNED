
package uned.java2016.apitwitter.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uned.java2016.apitwitter.services package. 
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

    private final static QName _Echo_QNAME = new QName("http://services.apitwitter.java2016.uned/", "echo");
    private final static QName _EchoResponse_QNAME = new QName("http://services.apitwitter.java2016.uned/", "echoResponse");
    private final static QName _GetNeighboursResponse_QNAME = new QName("http://services.apitwitter.java2016.uned/", "getNeighboursResponse");
    private final static QName _GetNeighbours_QNAME = new QName("http://services.apitwitter.java2016.uned/", "getNeighbours");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uned.java2016.apitwitter.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EchoResponse }
     * 
     */
    public EchoResponse createEchoResponse() {
        return new EchoResponse();
    }

    /**
     * Create an instance of {@link Echo }
     * 
     */
    public Echo createEcho() {
        return new Echo();
    }

    /**
     * Create an instance of {@link GetNeighboursResponse }
     * 
     */
    public GetNeighboursResponse createGetNeighboursResponse() {
        return new GetNeighboursResponse();
    }

    /**
     * Create an instance of {@link GetNeighbours }
     * 
     */
    public GetNeighbours createGetNeighbours() {
        return new GetNeighbours();
    }

    /**
     * Create an instance of {@link Neighbours }
     * 
     */
    public Neighbours createNeighbours() {
        return new Neighbours();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Echo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.apitwitter.java2016.uned/", name = "echo")
    public JAXBElement<Echo> createEcho(Echo value) {
        return new JAXBElement<Echo>(_Echo_QNAME, Echo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.apitwitter.java2016.uned/", name = "echoResponse")
    public JAXBElement<EchoResponse> createEchoResponse(EchoResponse value) {
        return new JAXBElement<EchoResponse>(_EchoResponse_QNAME, EchoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNeighboursResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.apitwitter.java2016.uned/", name = "getNeighboursResponse")
    public JAXBElement<GetNeighboursResponse> createGetNeighboursResponse(GetNeighboursResponse value) {
        return new JAXBElement<GetNeighboursResponse>(_GetNeighboursResponse_QNAME, GetNeighboursResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNeighbours }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.apitwitter.java2016.uned/", name = "getNeighbours")
    public JAXBElement<GetNeighbours> createGetNeighbours(GetNeighbours value) {
        return new JAXBElement<GetNeighbours>(_GetNeighbours_QNAME, GetNeighbours.class, null, value);
    }

}
