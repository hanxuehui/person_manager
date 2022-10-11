package com.ricky.manager.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

/**
 * 签名计算和校验工具类
 *
 * @author xjsh
 */
@Slf4j
public class AuthUtils {
    /**
     * app_key 参数名
     */
    public static final String PARAM_APP_KEY = "app_key";
    /**
     * 时间戳 参数名
     */
    public static final String PARAM_TIMESTAMP = "timestamp";
    /**
     * Data 参数名
     */
    public static final String PARAM_DATA = "data";
    /**
     * 签名字符串 参数名
     */
    public static final String PARAM_SIGNATURE = "signature";


    /**
     * 计算签名， 算法：sha1(密钥+参数按字母升序排列+密钥)
     * @param appKey APP KEY
     * @param appSecret 密钥
     * @param timestamp 时间戳
     * @param data JSON String
     * @return
     */
    public static final String calSign(String appKey, String appSecret, String timestamp, String data) {
        Map<String, Object> map = new HashMap<>(3);
        map.put(AuthUtils.PARAM_APP_KEY, appKey);
        map.put(AuthUtils.PARAM_TIMESTAMP, timestamp);
        map.put(AuthUtils.PARAM_DATA, data);
        return calSign(appSecret, map);
    }

    /**
     * 计算签名， 算法：sha1(密钥+参数按字母升序排列+密钥)
     * @param secret 密钥
     * @param data 参数键值对
     * @return 签名或null
     */
    public static final String calSign(String secret, Map<String,Object> data) {

        List<String> keys = new ArrayList<>(data.keySet());
        Collections.sort(keys);

        // 组合内容
        StringBuffer sb = new StringBuffer(secret);
        for (String key : keys) {
            sb.append(key).append(data.get(key));
        }
        sb.append(secret);
        return DigestUtils.sha1Hex(sb.toString());
    }

}
