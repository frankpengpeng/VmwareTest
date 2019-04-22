package com.vmware.questions.question4.apiversion;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("/api")
public class RestJerseyRegister extends ResourceConfig {
    public RestJerseyRegister() {
        register(RequestContextFilter.class);
        registerClasses(APIV1.class);
    }
}
