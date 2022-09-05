package es.uned.sw;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.12
 * 2014-09-12T19:01:38.097+02:00
 * Generated source version: 2.7.12
 * 
 */
@WebServiceClient(name = "CuentasService", 
                  wsdlLocation = "http://localhost:8080/JAX_Servidor/services/CuentasPort?wsdl",
                  targetNamespace = "http://sw.uned.es/") 
public class CuentasService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://sw.uned.es/", "CuentasService");
    public final static QName CuentasPort = new QName("http://sw.uned.es/", "CuentasPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/JAX_Servidor/services/CuentasPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CuentasService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/JAX_Servidor/services/CuentasPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CuentasService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CuentasService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CuentasService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CuentasService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CuentasService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CuentasService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns Cuentas
     */
    @WebEndpoint(name = "CuentasPort")
    public Cuentas getCuentasPort() {
        return super.getPort(CuentasPort, Cuentas.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Cuentas
     */
    @WebEndpoint(name = "CuentasPort")
    public Cuentas getCuentasPort(WebServiceFeature... features) {
        return super.getPort(CuentasPort, Cuentas.class, features);
    }

}
