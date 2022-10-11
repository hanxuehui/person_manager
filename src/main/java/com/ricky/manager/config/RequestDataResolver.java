package com.ricky.manager.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 从Request中获取“data”的参数的值。
 *
 * @author xjsh
 */
@Slf4j
public class RequestDataResolver implements HandlerMethodArgumentResolver {

    /**
     * 请求参数中的data，保存在Request的Attribute中的键值
     */
    public static final String KEY_REQUEST_ATTRIBUTE_DATA = "_key_api_data";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(com.ricky.manager.config.RequestData.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        Class clazz = parameter.getParameterType();
        final String data = (String) request.getAttribute(KEY_REQUEST_ATTRIBUTE_DATA);

        Object bean = JSON.parseObject(data, clazz);
        return bean;
    }
}
