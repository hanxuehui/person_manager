package com.ricky.manager.vo.labor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: 劳务分包VO
 * @Author: Ricky Charles
 * @Date: 2022-05-12 19:13
 **/

@Data
@ApiModel(value="LaborSubcontract对象", description="劳务分包")
public class LaborSubcontractVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String code;

    @ApiModelProperty(value = "负责人")
    @NotBlank(message = "负责人不能为空")
    private String director;

    @ApiModelProperty(value = "联系人")
    @NotBlank(message = "联系人不能为空")
    private String contractPerson;

    @ApiModelProperty(value = "主要从事专业")
    @NotBlank(message = "主要从事专业不能为空")
    private String mainProfession;

    @ApiModelProperty(value = "人数规模")
    @NotBlank(message = "人数规模不能为空")
    private String scale;

    @ApiModelProperty(value = "居住地")
    @NotBlank(message = "居住地不能为空")
    private String liveAddress;

    @ApiModelProperty(value = "大部分人员居住地")
    @NotBlank(message = "大部分居住不能为空")
    private String moreLiveAddress;

    @ApiModelProperty(value = "推荐人")
    @NotBlank(message = "推荐人不能为空")
    private String recommendPerson;

    @ApiModelProperty(value = "推荐原因")
    @NotBlank(message = "推荐原因不能为空")
    private String recommendReason;

    @ApiModelProperty(value = "项目经理评价")
    private String managerEvaluation;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "资质附件")
    private String certificate;

}
