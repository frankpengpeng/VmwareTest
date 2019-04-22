package com.vmware.questions.question4.service;

import com.alibaba.fastjson.JSONObject;
import com.vmware.questions.question4.dao.SubscribedService;
import com.vmware.questions.question4.mapper.SubscribedServiceMapper;
import com.vmware.questions.question4.response.ResponsePageData;
import com.vmware.questions.question4.vo.ServiceStatusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ServiceProvider {

    @Autowired
    private SubscribedServiceMapper subscribedServiceMapper;

    public LinkedHashMap getConsumedServices(int limit, int offset){
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();

        Long count = subscribedServiceMapper.countSubscribedService();
        List<ServiceStatusVo> data = subscribedServiceMapper.getSubscribedServices(limit, offset);

        serviceResponse.put("get_success","Successfully get subscribed services");
        serviceResponse.put("data", data);
        serviceResponse.put("count", count);
        serviceResponse.put("limit", limit);
        serviceResponse.put("offset", offset);
        return serviceResponse;
    }

    public LinkedHashMap consumeService(SubscribedService request){
        LinkedHashMap<Object, Object> serviceResponse = new LinkedHashMap<Object, Object>();
        SubscribedService service =
                subscribedServiceMapper.getSubscribedServiceByServiceAndConsumer(request.getServiceId(), request.getConsumerId());
        if(service != null){
            serviceResponse.put("duplicate_consumed","service already been consumed by user");
        } else {
            Integer consumeService = subscribedServiceMapper.subscribeService(request);
            if(consumeService != 1){
                serviceResponse.put("create_failed", "unable to subscribe service");
            } else {
                serviceResponse.put("create_success", "Successfully subscribed service");
            }
        }
        return serviceResponse;
    }

    public JSONObject updateConsumeServie(SubscribedService request){
        JSONObject result = new JSONObject();
        SubscribedService service =
                subscribedServiceMapper.getSubscribedServiceByServiceAndConsumer(request.getServiceId(), request.getConsumerId());
        if(service != null){
            result.put("duplicate_consumed","service already been consumed by user");
        } else {
            int unSubscribedService = subscribedServiceMapper.updateConsumedService(request);
            if (unSubscribedService == 0) {
                result.put("update_failed", "unable to update subscribed service");
            } else {
                result.put("update_success", "Successfully updated subscribed service");
            }
        }
        return result;
    }

    public JSONObject unSubScribeService(int serviceStatusId){
        JSONObject serviceResponse = new JSONObject();
        int unSubscribedService = subscribedServiceMapper.removeConsumedService(serviceStatusId);
        if(unSubscribedService == 0){
            serviceResponse.put("delete_failed", "unable to unsubscribe service");
        } else {
            serviceResponse.put("delete_success", "Successfully unsubscribe service");
        }
        return serviceResponse;
    }
}
