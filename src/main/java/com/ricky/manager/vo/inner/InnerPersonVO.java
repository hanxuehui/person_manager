package com.ricky.manager.vo.inner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @Description: 内部人员
 * @Author: Ricky Charles
 * @Date: 2022-05-13 19:08
 **/

@Data
@ApiModel(value="InnerPerson对象", description="内部人员")
public class InnerPersonVO {

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
    @NotNull(message = "参加时间不能为空")
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
    @NotNull(message = "初始学历毕业时间不能为空")
    private Date initialGraduationTime;

    @ApiModelProperty(value = "特种作业操作证")
    @NotBlank(message = "特种作业操作证不能为空")
    private String specialOperationCertificate;

    @ApiModelProperty(value = "安全人员资格证")
    @NotBlank(message = "安然人员资格证不能为空")
    private String safePersonCertificate;

    @ApiModelProperty(value = "执业资格证")
    @NotBlank(message = "执业资格证不能为空")
    private String qualificationCertificate;

    @ApiModelProperty(value = "用工形式")
    @NotBlank(message = "用工形式不能为空")
    private String employForm;

    @ApiModelProperty(value = "推荐岗位")
    @NotBlank(message = "推荐岗位不能为空")
    private String recommendPosition;

    @ApiModelProperty(value = "业务特长")
    @NotBlank(message = "业务特长不能为空")
    private String businessExpertise;

    @ApiModelProperty(value = "推荐单位")
    @NotBlank(message = "推荐单位不能为空")
    private String recommendUnit;

    @ApiModelProperty(value = "推荐部门")
    @NotBlank(message = "推荐部门不能为空")
    private String recommendDepartment;

    @ApiModelProperty(value = "计算机熟练程度")
    @NotBlank(message = "计算机熟练程度不能为空")
    private String computerSkill;

    @ApiModelProperty(value = "证书")
    private String certificate;

    @ApiModelProperty(value = "简历文件")
    private MultipartFile resumeFile;

    @ApiModelProperty(value = "简历")
    private String resume;

    @ApiModelProperty(value = "备注")
    private String remark;
}
