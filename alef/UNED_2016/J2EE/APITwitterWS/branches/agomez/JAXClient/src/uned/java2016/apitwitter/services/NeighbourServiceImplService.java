
package uned.java2016.apitwitter.services;

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
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "NeighbourServiceImplService", targetNamespace = "http://services.apitwitter.java2016.uned/", wsdlLocation = "http://localhost:8080/APITwitterWeb/services/NeighboursWS?wsdl")
public class NeighbourServiceImplService
    extends Service
{

    private final static URL NEIGHBOURSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException NEIGHBOURSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName NEIGHBOURSERVICEIMPLSERVICE_QNAME = new QName("http://services.apitwitter.java2016.uned/", "NeighbourServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/APITwitterWeb/services/NeighboursWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        NEIGHBOURSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        NEIGHBOURSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public NeighbourServiceImplService() {
        super(__getWsdlLocation(), NEIGHBOURSERVICEIMPLSERVICE_QNAME);
    }

    public NeighbourServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), NEIGHBOURSERVICEIMPLSERVICE_QNAME, features);
    }

    public NeighbourServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, NEIGHBOURSERVICEIMPLSERVICE_QNAME);
    }

    public NeighbourServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, NEIGHBOURSERVICEIMPLSERVICE_QNAME, features);
    }

    public NeighbourServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NeighbourServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns NeighbourService
     */
    @WebEndpoint(name = "NeighbourServiceImplPort")
    public NeighbourService getNeighbourServiceImplPort() {
        return super.getPort(new QName("http://services.apitwitter.java2016.uned/", "NeighbourServiceImplPort"), NeighbourService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NeighbourService
     */
    @WebEndpoint(name = "NeighbourServiceImplPort")
    public NeighbourService getNeighbourServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.apitwitter.java2016.uned/", "NeighbourServiceImplPort"), NeighbourService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (NEIGHBOURSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw NEIGHBOURSERVICEIMPLSERVICE_EXCEPTION;
        }
        return NEIGHBOURSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
