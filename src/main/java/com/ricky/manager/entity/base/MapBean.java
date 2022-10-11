package com.ricky.manager.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author RickyCharles
 * @apiNote
 */
@Data
@ApiModel(description = "基础Bean对象")
public class MapBean {
    @ApiModelProperty(value = "BeanId")
    private Long id;

    @ApiModelProperty(value = "BeanName")
    private String beanName;
}
