package com.ricky.manager.vo.profession;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Excel导入专业分包数据
 * @Author: Ricky Charles
 * @Date: 2022-05-15 17:42
 **/

@Data
@ApiModel(description = "Excel导入专业分包数据")
public class ProfessionSubcontractImportVO extends BaseRowModel {
    @ApiModelProperty(value = "单位名称")
    @ExcelProperty(index = 0)
    private String unitName;

    @ApiModelProperty(value = "编码")
    @ExcelProperty(index = 1)
    private String code;

    @ApiModelProperty(value = "资质类型")
    @ExcelProperty(index = 2)
    private String certificateType;

    @ApiModelProperty(value = "资质等级")
    @ExcelProperty(index = 3)
    private String certificateLevel;

    @ApiModelProperty(value = "主要从事专业")
    @ExcelProperty(index = 4)
    private String mainProfession;

    @ApiModelProperty(value = "人数规模")
    @ExcelProperty(index = 5)
    private String scale;

    @ApiModelProperty(value = "负责人")
    @ExcelProperty(index = 6)
    private String director;

    @ApiModelProperty(value = "联系方式")
    @ExcelProperty(index = 7)
    private String contactType;

    @ApiModelProperty(value = "单位地址")
    @ExcelProperty(index = 8)
    private String homeAddress;

    @ApiModelProperty(value = "推荐人")
    @ExcelProperty(index = 9)
    private String recommendPerson;

    @ApiModelProperty(value = "推荐原因")
    @ExcelProperty(index = 10)
    private String recommendReason;

    @ApiModelProperty(value = "项目经理评价")
    @ExcelProperty(index = 11)
    private String managerEvaluation;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(index = 12)
    private String remark;

}
