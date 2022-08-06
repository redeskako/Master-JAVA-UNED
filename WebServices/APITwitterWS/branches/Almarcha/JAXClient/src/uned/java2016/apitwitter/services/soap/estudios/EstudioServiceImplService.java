
package uned.java2016.apitwitter.services.soap.estudios;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EstudioServiceImplService", targetNamespace = "http://estudios.soap.services.apitwitter.java2016.uned/", wsdlLocation = "http://localhost:8080/APITwitterWeb/services/soap/ClinicalStudyWS?wsdl")
public class EstudioServiceImplService
    extends Service
{

    private final static URL ESTUDIOSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException ESTUDIOSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName ESTUDIOSERVICEIMPLSERVICE_QNAME = new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "EstudioServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/APITwitterWeb/services/soap/ClinicalStudyWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ESTUDIOSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        ESTUDIOSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public EstudioServiceImplService() {
        super(__getWsdlLocation(), ESTUDIOSERVICEIMPLSERVICE_QNAME);
    }

    public EstudioServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ESTUDIOSERVICEIMPLSERVICE_QNAME, features);
    }

    public EstudioServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, ESTUDIOSERVICEIMPLSERVICE_QNAME);
    }

    public EstudioServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ESTUDIOSERVICEIMPLSERVICE_QNAME, features);
    }

    public EstudioServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EstudioServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EstudioService
     */
    @WebEndpoint(name = "EstudioServiceImplPort")
    public EstudioService getEstudioServiceImplPort() {
        return super.getPort(new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "EstudioServiceImplPort"), EstudioService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EstudioService
     */
    @WebEndpoint(name = "EstudioServiceImplPort")
    public EstudioService getEstudioServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://estudios.soap.services.apitwitter.java2016.uned/", "EstudioServiceImplPort"), EstudioService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ESTUDIOSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw ESTUDIOSERVICEIMPLSERVICE_EXCEPTION;
        }
        return ESTUDIOSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
