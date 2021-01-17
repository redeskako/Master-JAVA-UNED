package pro.jbelo.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jbelo
 * @date 2017 07.
 */
@ApplicationPath("webresources")
public class RestAppConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(pro.jbelo.services.CountryService.class);
        resources.add(pro.jbelo.services.HealthIndicatorService.class);
        resources.add(pro.jbelo.services.DataService.class);
        return resources;
    }
}
