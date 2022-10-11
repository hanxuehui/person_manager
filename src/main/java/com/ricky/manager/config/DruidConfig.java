package com.ricky.manager.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author RickyCharles
 * @apiNote Druid连接池配置类
 */
@Configuration
@EnableAutoConfiguration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 打印慢查询sql日志,Filter是alibaba.druid下的filter
     * @return
     */
    @Bean
    public Filter statFilter(){
        StatFilter statFilter = new StatFilter();
        //设置慢日志的时间,超过5s的sql为满查询sql
        statFilter.setSlowSqlMillis(5000);
        //是否打印慢日志
        statFilter.setLogSlowSql(true);
        //是否合并慢日志
        statFilter.setMergeSql(true);
        return statFilter;
    }

    /**
     * 添加监控
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }
}
