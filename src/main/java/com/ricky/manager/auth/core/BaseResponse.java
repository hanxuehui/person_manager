package com.ricky.manager.auth.core;

import lombok.Data;

@Data
public class BaseResponse {

    /**
     * 默认成功的状态码：200
     */
    public final static int STATUS_OK = 200;

    private int status;
    private String message;

    public BaseResponse() {
        this(STATUS_OK, null);
    }

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
