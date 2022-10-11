package com.ricky.manager.vo.dispatch;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: Excel导出人员调遣数据
 * @Author: Ricky Charles
 * @Date: 2022-05-15 17:51
 **/
@Data
@ApiModel(description = "Excel导出人员调遣数据")
public class PersonDispatchExportVO extends BaseRowModel {

    @ApiModelProperty(value = "姓名")
    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ApiModelProperty(value = "编码")
    @ExcelProperty(value = "编码", index = 1)
    private String code;

    @ApiModelProperty(value = "原项目部")
    @ExcelProperty(value = "原项目部", index = 2)
    private String originDepartment;

    @ApiModelProperty(value = "现项目部")
    @ExcelProperty(value = "现项目部", index = 3)
    private String nowDepartment;

    @ApiModelProperty(value = "派遣开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "派遣开始时间", index = 4)
    private Date dispatchStartTime;

    @ApiModelProperty(value = "派遣结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "派遣结束时间", index = 5)
    private Date dispatchEndTime;

    @ApiModelProperty(value = "岗位")
    @ExcelProperty(value = "岗位", index = 6)
    private String position;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(value = "备注", index = 7)
    private String remark;
}
