package com.ricky.manager.vo.dispatch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 人员调遣VO
 * @Author: Ricky Charles
 * @Date: 2022-05-14 15:16
 **/
@Data
@ApiModel(value="PersonDispatchVO", description="人员调遣VO")
public class PersonDispatchVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "原项目部")
    @NotBlank(message = "原项目部不能为空")
    private String originDepartment;

    @ApiModelProperty(value = "现项目部")
    @NotBlank(message = "现项目部不能为空")
    private String nowDepartment;

    @ApiModelProperty(value = "派遣开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dispatchStartTime;

    @ApiModelProperty(value = "派遣结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dispatchEndTime;

    @ApiModelProperty(value = "岗位")
    @NotBlank(message = "岗位不能为空")
    private String position;

    @ApiModelProperty(value = "备注")
    private String remark;
}
