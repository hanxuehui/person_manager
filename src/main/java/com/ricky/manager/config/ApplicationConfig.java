package com.ricky.manager.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricky.manager.auth.interceptor.UserAuthRestInterceptor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author RikcyCharles
 * @apiNote 系统配置
 */
@Configuration
@EnableAutoConfiguration
public class ApplicationConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addFormatters(FormatterRegistry registry){
		registry.addConverterFactory(new CustomEnumConverterFactory());
		super.addFormatters(registry);
	}
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getExternalAuthInterceptor()).addPathPatterns("/api/ExternalCtrl/**");
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
	    //registry.addInterceptor(refererInterceptor).addPathPatterns("/**")
        //        .excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        registry.addInterceptor(getUserAuthRestInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        super.addInterceptors(registry);
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestDataResolver());
    }

    
	private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/cache/**",
                "/sinovac_order/api/v1/login",
                "/sinovac_order/api/v1/logout",
                "/login",
                "/logout",
                "/api/log/save",
                "/sinovac_order/api/ExternalCtrl/**",
                "/sinovac_order/api/TmsCtrl/**",
                "/api/TmsCtrl/**",
                "/api/ExternalCtrl/**",
                "/sinovac_order/error",
                "/error",
        };
        Collections.addAll(list, urls);
        return list;
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }
    @Bean
    ExternalAuthInterceptor getExternalAuthInterceptor() {
		// TODO Auto-generated method stub
		return new ExternalAuthInterceptor();
	}

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setDefaultSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    @Bean
    public TaskScheduler scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        return scheduler;
    }
}
