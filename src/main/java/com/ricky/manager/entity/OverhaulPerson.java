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
 * 检修人员
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-09
 */
@Data
@Table(name = "overhaul_person")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OverhaulPerson对象", description="检修人员")
public class OverhaulPerson implements Serializable {

    private static final long serialVersionUID = -94260574080915280L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "参加时间")
    private Date joinTime;

    @ApiModelProperty(value = "联系方式")
    private String contactType;

    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "专业年限")
    private String majorYear;

    @ApiModelProperty(value = "岗位")
    private String position;

    @ApiModelProperty(value = "政治面貌")
    private String politicalOutlook;

    @ApiModelProperty(value = "初始学历")
    private String initialEducation;

    @ApiModelProperty(value = "初始学历所学专业")
    private String initialMajor;

    @ApiModelProperty(value = "初始学历毕业院校")
    private String initialSchool;

    @ApiModelProperty(value = "初始学历毕业时间")
    private Date initialGraduationTime;

    @ApiModelProperty(value = "特种作业操作证")
    private String specialOperationCertificate;

    @ApiModelProperty(value = "安全人员资格证")
    private String safePersonCertificate;

    @ApiModelProperty(value = "执业资格证")
    private String qualificationCertificate;

    @ApiModelProperty(value = "归属单位")
    private String belongUnit;

    @ApiModelProperty(value = "签订劳务合同时限")
    private String contractLimit;

    @ApiModelProperty(value = "业务特长")
    private String businessExpertise;

    @ApiModelProperty(value = "推荐人")
    private String recommendPerson;

    @ApiModelProperty(value = "推荐原因")
    private String recommendReason;

    @ApiModelProperty(value = "项目经理评价")
    private String managerEvaluation;

    @ApiModelProperty(value = "备注")
    private String remark;

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
