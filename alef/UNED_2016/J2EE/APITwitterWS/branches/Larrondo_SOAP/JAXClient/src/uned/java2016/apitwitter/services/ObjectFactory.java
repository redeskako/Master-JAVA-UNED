
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
    private final static QName _getClinicalsResponse_QNAME = new QName("http://services.apitwitter.java2016.uned/", "getClinicalsResponse");
    private final static QName _getClinicals_QNAME = new QName("http://services.apitwitter.java2016.uned/", "getClinicals");

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
     * Create an instance of {@link GetClinicalsResponse }
     * 
     */
    public GetClinicalsResponse creategetClinicalsResponse() {
        return new GetClinicalsResponse();
    }

    /**
     * Create an instance of {@link GetClinicals }
     * 
     */
    public GetClinicals creategetClinicals() {
        return new GetClinicals();
    }

    /**
     * Create an instance of {@link Clinicals }
     * 
     */
    public Clinicals createNeighbours() {
        return new Clinicals();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClinicalsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.apitwitter.java2016.uned/", name = "getClinicalsResponse")
    public JAXBElement<GetClinicalsResponse> creategetClinicalsResponse(GetClinicalsResponse value) {
        return new JAXBElement<GetClinicalsResponse>(_getClinicalsResponse_QNAME, GetClinicalsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClinicals }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.apitwitter.java2016.uned/", name = "getClinicals")
    public JAXBElement<GetClinicals> creategetClinicals(GetClinicals value) {
        return new JAXBElement<GetClinicals>(_getClinicals_QNAME, GetClinicals.class, null, value);
    }

}
