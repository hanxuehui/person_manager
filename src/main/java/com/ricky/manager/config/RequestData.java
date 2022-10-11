package com.ricky.manager.config;

import java.lang.annotation.*;

/**
 * 注解，用于标识从Request中获取“data”的参数的值。
 * @author xjsh
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestData {
}
