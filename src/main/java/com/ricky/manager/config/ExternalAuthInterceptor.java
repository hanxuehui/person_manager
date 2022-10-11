package com.ricky.manager.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricky.manager.exception.LogicException;
import com.ricky.manager.helper.TmsHelper;
import com.ricky.manager.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ExternalAuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TmsHelper tmsHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 String appKey = request.getParameter(AuthUtils.PARAM_APP_KEY);
	     String timestamp = request.getParameter(AuthUtils.PARAM_TIMESTAMP);
	     String signature = request.getParameter(AuthUtils.PARAM_SIGNATURE);
		 String data=getParamData(request);
		 log.info("从Body中读取参数，request:{}, data:{}", request.getRequestURL().toString(),data);
		 if(data==null || !isJsonString(data)){
			  log.warn("Data参数不能为空且必须为JSON字符串，request:{}, data:{}", request.getRequestURL().toString(), data);
			  throw new LogicException("Data参数不能为空且必须为JSON字符串");
		 }
		 String requestData = data;
		 // 如果参数中没有值的话，从Body中获取参数值
        if (!StringUtils.hasText(appKey) || !StringUtils.hasText(timestamp) || !StringUtils.hasText(signature)) {
            log.info("从Body中读取参数，request:{}, data:{}", request.getRequestURL().toString(), data);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);
            appKey = jsonNode.get(AuthUtils.PARAM_APP_KEY).asText();
            timestamp = jsonNode.get(AuthUtils.PARAM_TIMESTAMP).asText();
            signature = jsonNode.get(AuthUtils.PARAM_SIGNATURE).asText();
            requestData = jsonNode.get(AuthUtils.PARAM_DATA).toString();
         }
		 //根据公钥查询私钥
		 String appSecret=tmsHelper.publicAppSecret;
		 if(StringUtils.isEmpty(appSecret)){
			 log.warn("API KEY有误，appKey:{}", appKey);
	         throw new LogicException("API KEY有误");
		 }
		 
		 String calSign = AuthUtils.calSign(appKey, appSecret, timestamp, requestData);
		 if(!signature.equals(calSign)){
			log.info("data:{}", data);
	        log.warn("签名错误， appKey:{}, appSecret:{}, signature:{}, calSign:{}", appKey, appSecret, signature, calSign);
	        throw new LogicException("签名错误");
		 }
		 // 放到request当中，用于在控制层获取
	     request.setAttribute(RequestDataResolver.KEY_REQUEST_ATTRIBUTE_DATA, requestData);
		 return super.preHandle(request, response, handler);
	}
	
	/**
     * 判断指定字符串是否为JSON字符串
     *
     * @param json
     * @return true, 表示字符串为
     */
    private boolean isJsonString(String json) {
        try {
            JSON.parseObject(json);
        } catch (JSONException e) {
            try {
                JSON.parseArray(json);
            } catch (JSONException e1) {
                return false;
            }
        }
        return true;
    }
    /**
     * 获取查询字符串、Form键值对、Request Body中的JSON字符串
     *
     * @param request
     * @return
     */
	private String getParamData(HttpServletRequest request) {
		try {
			String json=request.getParameter(AuthUtils.PARAM_DATA);
			if(StringUtils.hasText(json)){
				return json;
			}else{
				return request.getInputStream().toString();
			}
		} catch (Exception e) {
			log.warn("将request请求转换成JSON失败！", e);
			return null;
		}
	}

}
