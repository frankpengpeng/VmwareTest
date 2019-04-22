package com.vmware.questions.question4.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponsePageData<T> implements Serializable {
    private static final long serialVersionUID = -7361374995796005268L;

    private List<T> data;
    private long total;
    private int limit;
    private int offset;
}
