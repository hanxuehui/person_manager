package com.ricky.manager.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @Description: 用户登陆VO
 * @Author: Ricky Charles
 * @Date: 2022-05-10 19:38
 **/
@Data
@ApiModel(description = "用户")
public class UserLoginVO {

    /** 用户登录账号 */
    @ApiModelProperty(value = "用户登录账号")
    @NotBlank(message = "账号不能为空")
    private String account;

    /** 用户密码，未设置不允许登录 */
    @ApiModelProperty(value = "用户密码，未设置不允许登录")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "登陆IP")
    private String remoteHost;

}
