package com.ricky.manager.auth.interceptor;

import com.alibaba.fastjson.JSON;
import com.ricky.manager.auth.annotation.IgnoreUserToken;
import com.ricky.manager.auth.config.TokenConfig;
import com.ricky.manager.auth.core.BaseResponse;
import com.ricky.manager.auth.core.TokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户token拦截认证
 *
 * @author xjsh
 */
@Slf4j
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenHelper tokenHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if (HttpMethod.OPTIONS.matches(method)){
            return super.preHandle(request, response, handler);
        }

        final boolean checkToken = hasCheckToken((HandlerMethod) handler);
        if (checkToken) {
            try {
                log.info("request URI:{}", request.getRequestURI());
                String token = getToken(request);
                setContext(token);
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().println(JSON.toJSONString(new BaseResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage())));
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 检查 annotation, 是否需要检查User TOKEN
     * @param handlerMethod
     * @return true：需要检查
     */
    private boolean hasCheckToken(HandlerMethod handlerMethod) {
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken ignoreUserToken = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (ignoreUserToken == null) {
            ignoreUserToken = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        return ignoreUserToken == null;
    }

    /**
     * 获取token。先从Request Header中获取，如果没有的话从Cookie中获取。
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(TokenConfig.TOKEN_HEADER);
        if (!StringUtils.hasText(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(TokenConfig.TOKEN_HEADER)) {
                        token = cookie.getValue();
                    }
                }
            }
        }

        //处理"Bearer "
        if (token != null && token.startsWith(TokenConfig.JWT_TOKEN_TYPE)) {
            token = token.substring(TokenConfig.JWT_TOKEN_TYPE.length());
        }
        return token;
    }

    /**
     * 从JWT token中获取当前用户的相关信息，并存入当前Context中。
     * @param token
     * @throws Exception
     */
    private void setContext(String token) throws Exception {
        BaseContext context = tokenHelper.getInfoFromToken(token);
        BaseContextHolder.set(context);
    }
}

