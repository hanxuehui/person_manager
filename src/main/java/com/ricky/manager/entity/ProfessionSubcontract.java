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
 * 专业分包
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-09
 */
@Data
@Table(name = "profession_subcontract")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProfessionSubcontract对象", description="专业分包")
public class ProfessionSubcontract implements Serializable {

    private static final long serialVersionUID = -59514996611472196L;

    @Id
    @GeneratedValue(generator = "JDBC")
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
