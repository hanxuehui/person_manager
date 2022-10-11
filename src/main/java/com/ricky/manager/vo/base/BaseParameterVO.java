package com.ricky.manager.vo.base;

import com.ricky.manager.entity.base.PageWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 基本参数VO
 * @Author: Ricky Charles
 * @Date: 2022-05-13 10:29
 **/

@Setter(value = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@ApiModel(value="BaseParameterVO", description="基本参数VO")
public class BaseParameterVO extends PageWrapper {

    @ApiModelProperty(value = "创建时间-开始")
    private String startCrtTime;

    @ApiModelProperty(value = "创建时间-结束")
    private String endCrtTime;

    @ApiModelProperty(value = "创建人")
    private String crtPerson;

    @ApiModelProperty(value = "更新时间-开始")
    private String startUpdTime;

    @ApiModelProperty(value = "更新时间-结束")
    private String endUpdTime;

    @ApiModelProperty(value = "更新人")
    private String updPerson;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;


}
