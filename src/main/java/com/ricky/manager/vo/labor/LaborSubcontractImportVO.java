package com.ricky.manager.vo.labor;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Description: Excel导入劳务分包数据
 * @Author: Ricky Charles
 * @Date: 2022-05-15 17:29
 **/
@Data
@ApiModel(description = "Excel数据导入内部人员VO")
public class LaborSubcontractImportVO extends BaseRowModel {
    @ApiModelProperty(value = "编码")
    @ExcelProperty(index = 0)
    private String code;

    @ApiModelProperty(value = "负责人")
    @ExcelProperty(index = 1)
    private String director;

    @ApiModelProperty(value = "联系人")
    @ExcelProperty(index = 2)
    private String contractPerson;

    @ApiModelProperty(value = "主要从事专业")
    @ExcelProperty(index = 3)
    private String mainProfession;

    @ApiModelProperty(value = "人数规模")
    @ExcelProperty(index = 4)
    private String scale;

    @ApiModelProperty(value = "居住地")
    @ExcelProperty(index = 5)
    private String liveAddress;

    @ApiModelProperty(value = "大部分人员居住地")
    @ExcelProperty(index = 6)
    private String moreLiveAddress;

    @ApiModelProperty(value = "推荐人")
    @ExcelProperty(index = 7)
    private String recommendPerson;

    @ApiModelProperty(value = "推荐原因")
    @ExcelProperty(index = 8)
    private String recommendReason;

    @ApiModelProperty(value = "项目经理评价")
    @ExcelProperty(index = 9)
    private String managerEvaluation;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(index = 10)
    private String remark;

}
