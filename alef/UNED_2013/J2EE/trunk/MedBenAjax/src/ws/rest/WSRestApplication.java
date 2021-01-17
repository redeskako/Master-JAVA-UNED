package ws.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class WSRestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(WSRest.class);
        return s;
    }
}