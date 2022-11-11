package App;

import Service.TestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApp extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> serviceSet = new HashSet<Class<?>>();
        serviceSet.add(TestService.class);
        return super.getClasses();
    }
}
