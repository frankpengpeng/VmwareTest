package com.vmware.questions.question4.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceStatusVo implements Serializable {
    private static final long serialVersionUID = -7361374995796005269L;

    private int id;
    private int serviceId;
    private int consumerId;
    private String serviceName;
    private String consumerName;
}
