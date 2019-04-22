package com.vmware.questions.question4.mapper;

import com.vmware.questions.question4.dao.SubscribedService;
import com.vmware.questions.question4.vo.ServiceStatusVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubscribedServiceMapper {
    @Insert("insert into servicestatus(service_id, consumer_id) values (#{consumerService.serviceId},#{consumerService.consumerId})")
    Integer subscribeService(@Param("consumerService") SubscribedService consumerService);
    @Select("select servicestatus.id,servicestatus.service_id as serviceId, service.name as serviceName,servicestatus.consumer_id as consumerId,consumer.name as consumerName from service " +
            "left join servicestatus on service.id = servicestatus.service_id " +
            "left join consumer on consumer.id=servicestatus.consumer_id " +
            "group by servicestatus.id,servicestatus.service_id,servicestatus.consumer_id " +
            "limit #{limit} offset #{offset}")
    List<ServiceStatusVo> getSubscribedServices(@Param("limit") int limit, @Param("offset") int offset);
    @Update("update servicestatus set service_id=#{consumerService.serviceId}, consumer_id=#{consumerService.consumerId} where id =#{consumerService.id}")
    Integer updateConsumedService(@Param("consumerService") SubscribedService consumerService);
    @Delete("delete from servicestatus where id=#{id}")
    Integer removeConsumedService(@Param("id") int id);
    @Select("select * from servicestatus where service_id =#{serviceId} and consumer_id=#{consumerId}")
    SubscribedService getSubscribedServiceByServiceAndConsumer(@Param("serviceId") int serviceId, @Param("consumerId") int consumerId);
    @Select("select count(1) from servicestatus")
    Long countSubscribedService();
}
