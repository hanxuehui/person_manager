package com.ricky.manager.vo.overhaul;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description: 检修人员VO
 * @Author: Ricky Charles
 * @Date: 2022-05-13 20:08
 **/

@Data
@ApiModel(value="OverhaulPersonVO", description="检修人员VO")
public class OverhaulPersonVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String code;

    @ApiModelProperty(value = "性别")
    @NotBlank(message = "性别不能为空")
    private String sex;

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty(value = "身份证")
    @NotBlank(message = "身份证不能为空")
    private String idCard;

    @ApiModelProperty(value = "参加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinTime;

    @ApiModelProperty(value = "联系方式")
    @NotBlank(message = "联系方式不能为空")
    private String contactType;

    @ApiModelProperty(value = "家庭住址")
    @NotBlank(message = "家庭住址不能为空")
    private String homeAddress;

    @ApiModelProperty(value = "专业")
    @NotBlank(message = "专业不能为空")
    private String major;

    @ApiModelProperty(value = "专业年限")
    @NotBlank(message = "专业年限不能为空")
    private String majorYear;

    @ApiModelProperty(value = "岗位")
    @NotBlank(message = "岗位不能为空")
    private String position;

    @ApiModelProperty(value = "政治面貌")
    @NotBlank(message = "政治面貌不能为空")
    private String politicalOutlook;

    @ApiModelProperty(value = "初始学历")
    @NotBlank(message = "初始学历不能为空")
    private String initialEducation;

    @ApiModelProperty(value = "初始学历所学专业")
    @NotBlank(message = "初始学历所学专业不能为空")
    private String initialMajor;

    @ApiModelProperty(value = "初始学历毕业院校")
    @NotBlank(message = "初始学历毕业院校不能为空")
    private String initialSchool;

    @ApiModelProperty(value = "初始学历毕业时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date initialGraduationTime;

    @ApiModelProperty(value = "特种作业操作证")
    @NotBlank(message = "特种作业操作证不能为空")
    private String specialOperationCertificate;

    @ApiModelProperty(value = "安全人员资格证")
    @NotBlank(message = "安全人员资格证不能为空")
    private String safePersonCertificate;

    @ApiModelProperty(value = "执业资格证")
    @NotBlank(message = "执业资格证不能为空")
    private String qualificationCertificate;

    @ApiModelProperty(value = "归属单位")
    @NotBlank(message = "归属单位不能为空")
    private String belongUnit;

    @ApiModelProperty(value = "签订劳务合同时限")
    @NotBlank(message = "签订劳务合同时限不能为空")
    private String contractLimit;

    @ApiModelProperty(value = "业务特长")
    @NotBlank(message = "业务特长不能为空")
    private String businessExpertise;

    @ApiModelProperty(value = "推荐人")
    @NotBlank(message = "推荐人不能为空")
    private String recommendPerson;

    @ApiModelProperty(value = "推荐原因")
    @NotBlank(message = "推荐原因不能为空")
    private String recommendReason;

    @ApiModelProperty(value = "项目经理评价")
    private String managerEvaluation;

    @ApiModelProperty(value = "备注")
    @NotBlank(message = "不能为空")
    private String remark;
}
