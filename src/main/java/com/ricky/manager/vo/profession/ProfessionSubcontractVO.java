package com.ricky.manager.vo.profession;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Description: 专业分包VO
 * @Author: Ricky Charles
 * @Date: 2022-05-14 11:33
 **/

@Data
@ApiModel(value="ProfessionSubcontractVO", description="专业分包VO")
public class ProfessionSubcontractVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "单位名称")
    @NotBlank(message = "单位名称不能为空")
    private String unitName;

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String code;

    @ApiModelProperty(value = "资质类型")
    @NotBlank(message = "资质类型不能为空")
    private String certificateType;

    @ApiModelProperty(value = "资质等级")
    @NotBlank(message = "资质等级不能为空")
    private String certificateLevel;

    @ApiModelProperty(value = "主要从事专业")
    @NotBlank(message = "主要从事专业不能为空")
    private String mainProfession;

    @ApiModelProperty(value = "人数规模")
    @NotBlank(message = "人数规模不能为空")
    private String scale;

    @ApiModelProperty(value = "负责人")
    @NotBlank(message = "负责人不能为空")
    private String director;

    @ApiModelProperty(value = "联系方式")
    private String contactType;

    @ApiModelProperty(value = "单位地址")
    private String homeAddress;

    @ApiModelProperty(value = "推荐人")
    private String recommendPerson;

    @ApiModelProperty(value = "推荐原因")
    private String recommendReason;

    @ApiModelProperty(value = "项目经理评价")
    private String managerEvaluation;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "资质附件文件")
    private MultipartFile certificateFile;

    @ApiModelProperty(value = "资质附件")
    private String certificate;
}
