package com.ricky.manager.vo.inner;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: Excel导出数据内部人员VO
 * @Author: Ricky Charles
 * @Date: 2021-07-08 11:21
 **/
@Data
@ApiModel(description = "Excel数据导出内部人员VO")
public class InnerPersonExportVO extends BaseRowModel {
    @ApiModelProperty(value = "姓名")
    @ExcelProperty(value="姓名", index = 0)
    private String name;

    @ApiModelProperty(value = "编码")
    @ExcelProperty(value="编码", index = 1)
    private String code;

    @ApiModelProperty(value = "性别")
    @ExcelProperty(value="性别", index = 2)
    private String sex;

    @ApiModelProperty(value = "年龄")
    @ExcelProperty(value="年龄", index = 3)
    private Integer age;

    @ApiModelProperty(value = "身份证")
    @ExcelProperty(value="身份证", index = 4)
    private String idCard;

    @ApiModelProperty(value = "参加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value="参加时间", index = 5)
    private Date joinTime;

    @ApiModelProperty(value = "联系方式")
    @ExcelProperty(value="联系方式", index = 6)
    private String contactType;

    @ApiModelProperty(value = "家庭住址")
    @ExcelProperty(value="家庭住址", index = 7)
    private String homeAddress;

    @ApiModelProperty(value = "专业")
    @ExcelProperty(value="专业", index = 8)
    private String major;

    @ApiModelProperty(value = "专业年限")
    @ExcelProperty(value="专业年限", index = 9)
    private String majorYear;

    @ApiModelProperty(value = "岗位")
    @ExcelProperty(value="岗位", index = 10)
    private String position;

    @ApiModelProperty(value = "政治面貌")
    @ExcelProperty(value="政治面貌", index = 11)
    private String politicalOutlook;

    @ApiModelProperty(value = "初始学历")
    @ExcelProperty(value="初始学历", index = 12)
    private String initialEducation;

    @ApiModelProperty(value = "初始学历所学专业")
    @ExcelProperty(value="初始学历所学专业", index = 13)
    private String initialMajor;

    @ApiModelProperty(value = "初始学历毕业院校")
    @ExcelProperty(value="初始学历毕业院校", index = 14)
    private String initialSchool;

    @ApiModelProperty(value = "初始学历毕业时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value="初始学历毕业时间", index = 15)
    private Date initialGraduationTime;

    @ApiModelProperty(value = "特种作业操作证")
    @ExcelProperty(value="特种作业操作证", index = 16)
    private String specialOperationCertificate;

    @ApiModelProperty(value = "安全人员资格证")
    @ExcelProperty(value="安全人员资格证", index = 17)
    private String safePersonCertificate;

    @ApiModelProperty(value = "执业资格证")
    @ExcelProperty(value="执业资格证", index = 18)
    private String qualificationCertificate;

    @ApiModelProperty(value = "用工形式")
    @ExcelProperty(value="用工形式", index = 19)
    private String employForm;

    @ApiModelProperty(value = "推荐岗位")
    @ExcelProperty(value="推荐岗位", index = 20)
    private String recommendPosition;

    @ApiModelProperty(value = "业务特长")
    @ExcelProperty(value="业务特长", index = 21)
    private String businessExpertise;

    @ApiModelProperty(value = "推荐单位")
    @ExcelProperty(value="推荐单位", index = 22)
    private String recommendUnit;

    @ApiModelProperty(value = "推荐部门")
    @ExcelProperty(value="推荐部门", index = 23)
    private String recommendDepartment;

    @ApiModelProperty(value = "计算机熟练程度")
    @ExcelProperty(value="计算机熟练程度", index = 24)
    private String computerSkill;

    @ApiModelProperty(value = "证书")
    @ExcelProperty(value="证书", index = 25)
    private String certificate;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(value="备注", index = 26)
    private String remark;

}
