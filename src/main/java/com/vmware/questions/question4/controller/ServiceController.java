package com.vmware.questions.question4.controller;

import com.alibaba.fastjson.JSONObject;
import com.vmware.questions.question4.dao.SubscribedService;
import com.vmware.questions.question4.service.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.Set;

@Controller
public class ServiceController {

    @Autowired
    private ServiceProvider serviceProvider;

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response subscribeService(SubscribedService request){
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<>();
        Set<ConstraintViolation<SubscribedService>> validatorErrors = validator.validate(request);
        if(validatorErrors.isEmpty()){
            serviceResponse = serviceProvider.consumeService(request);
            return Response.status(Response.Status.CREATED).entity(serviceResponse).build();
        }
        else {
            serviceResponse.put("error", validatorErrors.toString());
            return Response.status(400).entity(serviceResponse).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response unSubscribeService(@PathParam("id") int id){
        JSONObject serviceResponse = new JSONObject();
        serviceResponse = serviceResponse = serviceProvider.unSubScribeService(id);
        return Response.status(Response.Status.OK).entity(serviceResponse.toString()).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSubscribeService(@PathParam("id") int id, SubscribedService request){
        JSONObject serviceResponse = new JSONObject();
        Set<ConstraintViolation<SubscribedService>> validatorErrors = validator.validate(request);
        if(validatorErrors.isEmpty()){
            request.setId(id);
            serviceResponse = serviceProvider.updateConsumeServie(request);
            return Response.status(Response.Status.OK).entity(serviceResponse.toJSONString()).build();
        }
        else {
            serviceResponse.put("error", validatorErrors.toString());
            return Response.status(400).entity(serviceResponse).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getSubscribedService(@QueryParam("limit")@DefaultValue("15") int limit, @QueryParam("offset")@DefaultValue("0") int offset){
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<>();
        serviceResponse = serviceProvider.getConsumedServices(limit, offset);

        return Response.status(Response.Status.OK).entity(serviceResponse).build();
    }
}
