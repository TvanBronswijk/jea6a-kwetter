package nl.fontys.kwetter.web.api;

import nl.fontys.kwetter.web.api.endpoint.PostEndpoint;
import nl.fontys.kwetter.web.api.endpoint.UserEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("api")
public class RestBase extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);

        // Add Jackson feature.
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);

        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(PostEndpoint.class);
        resources.add(UserEndpoint.class);
    }

}
