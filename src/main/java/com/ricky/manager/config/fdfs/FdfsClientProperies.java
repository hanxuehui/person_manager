package com.ricky.manager.config.fdfs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: FastFS 配置文件
 * @Author: Ricky Charles
 * @Date: 2022-03-14 14:52
 **/
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "fastdfs")
@Data
public class FdfsClientProperies {

    @Value("${fastdfs.connect_timeout_in_seconds:2}")
    private String connect_timeout_in_seconds;

    @Value("${fastdfs.network_timeout_in_seconds:30}")
    private String network_timeout_in_seconds;

    @Value("${fastdfs.charset:UTF-8}")
    private String charset;

    @Value("${fastdfs.http_tracker_http_port:22122}")
    private String http_tracker_http_port;

    @Value("${fastdfs.http_anti_steal_token:no}")
    private String http_anti_steal_token;

    @Value("${fastdfs.http_secret_key:FastDFS1234567890}")
    private String http_secret_key;

//    @Value("${fastdfs.tracker_servers:192.168.24.2:22122}")
//    @Value("${fastdfs.tracker_servers:49.232.194.68:22122}")
    @Value("${fastdfs.tracker_servers:120.48.82.97:22122}")
    private String tracker_servers;

//    @Value("${fastdfs.domain:http://wl.sinovacbio.cn:8888
//    @Value("${fastdfs.domain:http://49.232.194.68:8888/}")
    @Value("${fastdfs.domain:http://120.48.82.97:8888/}")
    private String domain;
}
