package App;


import Services.CategoryServices;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class Application extends javax.ws.rs.core.Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> serviceSet = new HashSet<Class<?>>();
        serviceSet.add(CategoryServices.class);
        return super.getClasses();
    }
}
