package com.ricky.manager.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 劳务分包
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-09
 */
@Data
@Table(name = "labor_subcontract")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="LaborSubcontract对象", description="劳务分包")
public class LaborSubcontract implements Serializable {

    private static final long serialVersionUID = 989686764994571642L;

    @Id
    @GeneratedValue(generator = "JDBC")
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

    @ApiModelProperty(value = "创建时间")
    private Date crtTime;

    @ApiModelProperty(value = "创建人")
    private String crtPerson;

    @ApiModelProperty(value = "更新时间")
    private Date updTime;

    @ApiModelProperty(value = "更新人")
    private String updPerson;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;


}
