package com.ricky.manager.entity.base;

import lombok.Getter;
import lombok.Setter;

/**
 * @author RickyCharles
 * @apiNote 基础返回数据包装类
 */
@Setter
@Getter
public class BaseResponseBean {
    /**
     * 默认成功的状态码：200
     */
    public final static int STATUS_OK = 200;
    /**
     * 默认失败的状态码：500
     */
    public final static int STATUS_FAIL = 500;


    private Integer status;
    private String msg;
    private Object obj;

    private BaseResponseBean() {
    }

    public static BaseResponseBean build() {
        return new BaseResponseBean();
    }

    public static BaseResponseBean ok(String msg, Object obj) {
        return new BaseResponseBean(STATUS_OK, msg, obj);
    }

    public static BaseResponseBean ok(String msg) {
        return new BaseResponseBean(STATUS_OK, msg, null);
    }

    public static BaseResponseBean error(String msg, Object obj) {
        return new BaseResponseBean(STATUS_FAIL, msg, obj);
    }

    public static BaseResponseBean error(String msg) {
        return new BaseResponseBean(STATUS_FAIL, msg, null);
    }

    private BaseResponseBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public BaseResponseBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResponseBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public BaseResponseBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
