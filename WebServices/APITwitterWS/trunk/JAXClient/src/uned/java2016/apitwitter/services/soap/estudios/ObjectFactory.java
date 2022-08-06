
package uned.java2016.apitwitter.services.soap.estudios;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the uned.java2016.apitwitter.services.soap.estudios package. 
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

    private final static QName _EchoResponse_QNAME = new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "echoResponse");
    private final static QName _GetByNCTResponse_QNAME = new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "getByNCTResponse");
    private final static QName _GetByNCT_QNAME = new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "getByNCT");
    private final static QName _Echo_QNAME = new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "echo");
    private final static QName _GetByHashtag_QNAME = new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "getByHashtag");
    private final static QName _GetByHashtagResponse_QNAME = new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "getByHashtagResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: uned.java2016.apitwitter.services.soap.estudios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetByHashtagResponse }
     * 
     */
    public GetByHashtagResponse createGetByHashtagResponse() {
        return new GetByHashtagResponse();
    }

    /**
     * Create an instance of {@link GetByHashtag }
     * 
     */
    public GetByHashtag createGetByHashtag() {
        return new GetByHashtag();
    }

    /**
     * Create an instance of {@link GetByNCT }
     * 
     */
    public GetByNCT createGetByNCT() {
        return new GetByNCT();
    }

    /**
     * Create an instance of {@link Echo }
     * 
     */
    public Echo createEcho() {
        return new Echo();
    }

    /**
     * Create an instance of {@link EchoResponse }
     * 
     */
    public EchoResponse createEchoResponse() {
        return new EchoResponse();
    }

    /**
     * Create an instance of {@link GetByNCTResponse }
     * 
     */
    public GetByNCTResponse createGetByNCTResponse() {
        return new GetByNCTResponse();
    }

    /**
     * Create an instance of {@link ResponsibleParty }
     * 
     */
    public ResponsibleParty createResponsibleParty() {
        return new ResponsibleParty();
    }

    /**
     * Create an instance of {@link ClinicalStudy }
     * 
     */
    public ClinicalStudy createClinicalStudy() {
        return new ClinicalStudy();
    }

    /**
     * Create an instance of {@link Estudios }
     * 
     */
    public Estudios createEstudios() {
        return new Estudios();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EchoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://estudios.soap.services.apitwitter.java2016.uned/", name = "echoResponse")
    public JAXBElement<EchoResponse> createEchoResponse(EchoResponse value) {
        return new JAXBElement<EchoResponse>(_EchoResponse_QNAME, EchoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByNCTResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://estudios.soap.services.apitwitter.java2016.uned/", name = "getByNCTResponse")
    public JAXBElement<GetByNCTResponse> createGetByNCTResponse(GetByNCTResponse value) {
        return new JAXBElement<GetByNCTResponse>(_GetByNCTResponse_QNAME, GetByNCTResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByNCT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://estudios.soap.services.apitwitter.java2016.uned/", name = "getByNCT")
    public JAXBElement<GetByNCT> createGetByNCT(GetByNCT value) {
        return new JAXBElement<GetByNCT>(_GetByNCT_QNAME, GetByNCT.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Echo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://estudios.soap.services.apitwitter.java2016.uned/", name = "echo")
    public JAXBElement<Echo> createEcho(Echo value) {
        return new JAXBElement<Echo>(_Echo_QNAME, Echo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByHashtag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://estudios.soap.services.apitwitter.java2016.uned/", name = "getByHashtag")
    public JAXBElement<GetByHashtag> createGetByHashtag(GetByHashtag value) {
        return new JAXBElement<GetByHashtag>(_GetByHashtag_QNAME, GetByHashtag.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByHashtagResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://estudios.soap.services.apitwitter.java2016.uned/", name = "getByHashtagResponse")
    public JAXBElement<GetByHashtagResponse> createGetByHashtagResponse(GetByHashtagResponse value) {
        return new JAXBElement<GetByHashtagResponse>(_GetByHashtagResponse_QNAME, GetByHashtagResponse.class, null, value);
    }

}
