package com.ricky.manager.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description: 用户token
 * @Author: Ricky Charles
 * @Date: 2021-08-10 16:15
 **/
@Data
@ApiModel(description = "用户tokenVO")
public class UserTokenVO {

    @ApiModelProperty(value = "token")
    @NotBlank(message = "token不能为空")
    private String token;

    @ApiModelProperty(value = "userId")
    @NotBlank(message = "用户id不能为空")
    private String userId;

}
