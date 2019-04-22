package com.vmware.questions.question4.apiversion;

import com.vmware.questions.question4.controller.ServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

@Component
@Path("v1")
public class APIV1 {
    @Autowired
    private ServiceController serviceController;

    @Path("services")
    public ServiceController getServiceController(){
        return serviceController;
    }
}
