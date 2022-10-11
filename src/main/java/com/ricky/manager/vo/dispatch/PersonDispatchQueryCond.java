package com.ricky.manager.vo.dispatch;

import com.ricky.manager.vo.base.BaseParameterVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 人员调遣查询对象
 * @Author: Ricky Charles
 * @Date: 2022-05-14 15:16
 **/

@Data
@ApiModel(value="PersonDispatchQueryCond", description="人员调遣查询对象")
public class PersonDispatchQueryCond extends BaseParameterVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "原项目部")
    private String originDepartment;

    @ApiModelProperty(value = "现项目部")
    private String nowDepartment;

    @ApiModelProperty(value = "派遣开始时间")
    private String dispatchStartTime;

    @ApiModelProperty(value = "派遣结束时间")
    private String dispatchEndTime;

    @ApiModelProperty(value = "岗位")
    private String position;

    @ApiModelProperty(value = "备注")
    private String remark;

}
