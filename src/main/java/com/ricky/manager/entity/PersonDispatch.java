package com.ricky.manager.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * 人员调遣
 * </p>
 *
 * @author RickyCharles
 * @since 2022-05-09
 */
@Data
@Table(name = "person_dispatch")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PersonDispatch对象", description="人员调遣")
public class PersonDispatch implements Serializable {

    private static final long serialVersionUID = -32122343879389047L;;

    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "原项目部")
    private String originDepartment;

    @ApiModelProperty(value = "现项目部")
    private String nowDepartment;

    @ApiModelProperty(value = "派遣开始时间")
    private Date dispatchStartTime;

    @ApiModelProperty(value = "派遣结束时间")
    private Date dispatchEndTime;

    @ApiModelProperty(value = "岗位")
    private String position;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date crtTime;

    @ApiModelProperty(value = "创建人")
    private String crtPerson;

    @ApiModelProperty(value = "更新时间")
    private Date updTime;

    @ApiModelProperty(value = "更新人")
    private String updPerson;

    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;


}
