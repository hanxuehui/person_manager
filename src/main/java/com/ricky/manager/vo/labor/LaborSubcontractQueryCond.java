package com.ricky.manager.vo.labor;

import com.ricky.manager.vo.base.BaseParameterVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 劳务分包查询条件
 * @Author: Ricky Charles
 * @Date: 2022-05-13 10:05
 **/

@Data
@ApiModel(value="LaborSubcontractQueryCond", description="劳务分包查询条件")
public class LaborSubcontractQueryCond extends BaseParameterVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "负责人")
    private String director;

    @ApiModelProperty(value = "联系人")
    private String contractPerson;

    @ApiModelProperty(value = "主要从事专业")
    private String mainProfession;

    @ApiModelProperty(value = "人数规模")
    private String scale;

    @ApiModelProperty(value = "居住地")
    private String liveAddress;

    @ApiModelProperty(value = "大部分人员居住地")
    private String moreLiveAddress;

    @ApiModelProperty(value = "推荐人")
    private String recommendPerson;

    @ApiModelProperty(value = "推荐原因")
    private String recommendReason;

    @ApiModelProperty(value = "项目经理评价")
    private String managerEvaluation;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "资质附件")
    private String certificate;
}
