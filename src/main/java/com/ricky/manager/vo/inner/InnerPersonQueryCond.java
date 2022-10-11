package com.ricky.manager.vo.inner;

import com.ricky.manager.vo.base.BaseParameterVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * @Description: 内部人员查询条件
 * @Author: Ricky Charles
 * @Date: 2022-05-13 10:05
 **/

@Data
@ApiModel(value="InnerPersonQueryCond", description="内部人员查询条件")
public class InnerPersonQueryCond extends BaseParameterVO {

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

    @ApiModelProperty(value = "用工形式")
    private String employForm;

    @ApiModelProperty(value = "推荐岗位")
    private String recommendPosition;

    @ApiModelProperty(value = "业务特长")
    private String businessExpertise;

    @ApiModelProperty(value = "推荐单位")
    private String recommendUnit;

    @ApiModelProperty(value = "推荐部门")
    private String recommendDepartment;

    @ApiModelProperty(value = "计算机熟练程度")
    private String computerSkill;

    @ApiModelProperty(value = "证书")
    private String certificate;

    @ApiModelProperty(value = "简历链接")
    private String resume;

    @ApiModelProperty(value = "备注")
    private String remark;

}
