package com.ricky.manager.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Ricky Charles
 * @Date: 2021-08-19 17:01
 **/
@Component
public class TmsHelper {
    @Value("${tms.url}")
    public String url;

    @Value("${tms.app-key}")
    public String appKey;

    @Value("${tms.app-secret}")
    public String appSecret;

    @Value("${tms.public-app-secret}")
    public String publicAppSecret;
}
