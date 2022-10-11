package com.ricky.manager.vo.profession;

import com.ricky.manager.vo.base.BaseParameterVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: 专业分包查询条件
 * @Author: Ricky Charles
 * @Date: 2022-05-14 11:28
 **/

@Data
@ApiModel(value="ProfessionSubcontractQueryCond", description="专业分包查询条件")
public class ProfessionSubcontractQueryCond extends BaseParameterVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "资质类型")
    private String certificateType;

    @ApiModelProperty(value = "资质等级")
    private String certificateLevel;

    @ApiModelProperty(value = "主要从事专业")
    private String mainProfession;

    @ApiModelProperty(value = "人数规模")
    private String scale;

    @ApiModelProperty(value = "负责人")
    private String director;

    @ApiModelProperty(value = "联系方式")
    private String contactType;

    @ApiModelProperty(value = "单位地址")
    private String homeAddress;

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
