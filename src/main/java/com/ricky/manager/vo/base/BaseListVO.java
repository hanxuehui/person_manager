package com.ricky.manager.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description: 集合VO
 * @Author: Ricky Charles
 * @Date: 2022-05-13 09:54
 **/

@Data
@ApiModel(value="BaseListVO", description="集合VO")
public class BaseListVO {

    @ApiModelProperty(value = "id集合")
    @NotNull(message = "id集合不能为空")
    private List<Integer> ids;
}
