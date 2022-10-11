package com.ricky.manager.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RickyCharles
 * @apiNote
 */
@Setter(value = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@ApiModel(description = "Page页基础数据")
public class PageWrapper {
    /** 页码 */
    @ApiModelProperty(value = "页码，默认1")
    private Integer page = 1;
    /** 每页的记录数 */
    @ApiModelProperty(value = "每页的记录数，默认10")
    private Integer pageSize = 10;
}
