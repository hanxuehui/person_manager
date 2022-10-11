package com.ricky.manager.vo.inner;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: Excel导入数据内部人员VO
 * @Author: Ricky Charles
 * @Date: 2021-07-08 11:21
 **/
@Data
@ApiModel(description = "Excel数据导入内部人员VO")
public class InnerPersonImportVO extends BaseRowModel {
    @ApiModelProperty(value = "姓名")
    @ExcelProperty(index = 0)
    private String name;

    @ApiModelProperty(value = "编码")
    @ExcelProperty(index = 1)
    private String code;

    @ApiModelProperty(value = "性别")
    @ExcelProperty(index = 2)
    private String sex;

    @ApiModelProperty(value = "年龄")
    @ExcelProperty(index = 3)
    private Integer age;

    @ApiModelProperty(value = "身份证")
    @ExcelProperty(index = 4)
    private String idCard;

    @ApiModelProperty(value = "参加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 5)
    private Date joinTime;

    @ApiModelProperty(value = "联系方式")
    @ExcelProperty(index = 6)
    private String contactType;

    @ApiModelProperty(value = "家庭住址")
    @ExcelProperty(index = 7)
    private String homeAddress;

    @ApiModelProperty(value = "专业")
    @ExcelProperty(index = 8)
    private String major;

    @ApiModelProperty(value = "专业年限")
    @ExcelProperty(index = 9)
    private String majorYear;

    @ApiModelProperty(value = "岗位")
    @ExcelProperty(index = 10)
    private String position;

    @ApiModelProperty(value = "政治面貌")
    @ExcelProperty(index = 11)
    private String politicalOutlook;

    @ApiModelProperty(value = "初始学历")
    @ExcelProperty(index = 12)
    private String initialEducation;

    @ApiModelProperty(value = "初始学历所学专业")
    @ExcelProperty(index = 13)
    private String initialMajor;

    @ApiModelProperty(value = "初始学历毕业院校")
    @ExcelProperty(index = 14)
    private String initialSchool;

    @ApiModelProperty(value = "初始学历毕业时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 15)
    private Date initialGraduationTime;

    @ApiModelProperty(value = "特种作业操作证")
    @ExcelProperty(index = 16)
    private String specialOperationCertificate;

    @ApiModelProperty(value = "安全人员资格证")
    @ExcelProperty(index = 17)
    private String safePersonCertificate;

    @ApiModelProperty(value = "执业资格证")
    @ExcelProperty(index = 18)
    private String qualificationCertificate;

    @ApiModelProperty(value = "用工形式")
    @ExcelProperty(index = 19)
    private String employForm;

    @ApiModelProperty(value = "推荐岗位")
    @ExcelProperty(index = 20)
    private String recommendPosition;

    @ApiModelProperty(value = "业务特长")
    @ExcelProperty(index = 21)
    private String businessExpertise;

    @ApiModelProperty(value = "推荐单位")
    @ExcelProperty(index = 22)
    private String recommendUnit;

    @ApiModelProperty(value = "推荐部门")
    @ExcelProperty(index = 23)
    private String recommendDepartment;

    @ApiModelProperty(value = "计算机熟练程度")
    @ExcelProperty(index = 24)
    private String computerSkill;

    @ApiModelProperty(value = "证书")
    @ExcelProperty(index = 25)
    private String certificate;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(index = 26)
    private String remark;

}
