package com.ricky.manager.vo.dispatch;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: Excel导入人员调遣数据
 * @Author: Ricky Charles
 * @Date: 2022-05-15 17:51
 **/
@Data
@ApiModel(description = "Excel导入人员调遣数据")
public class PersonDispatchImportVO extends BaseRowModel {

    @ApiModelProperty(value = "姓名")
    @ExcelProperty(index = 0)
    private String name;

    @ApiModelProperty(value = "编码")
    @ExcelProperty(index = 1)
    private String code;

    @ApiModelProperty(value = "原项目部")
    @ExcelProperty(index = 2)
    private String originDepartment;

    @ApiModelProperty(value = "现项目部")
    @ExcelProperty(index = 3)
    private String nowDepartment;

    @ApiModelProperty(value = "派遣开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 4)
    private Date dispatchStartTime;

    @ApiModelProperty(value = "派遣结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(index = 5)
    private Date dispatchEndTime;

    @ApiModelProperty(value = "岗位")
    @ExcelProperty(index = 6)
    private String position;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(index = 7)
    private String remark;
}
