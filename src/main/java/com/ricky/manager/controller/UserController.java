package com.ricky.manager.controller;

import com.ricky.manager.auth.annotation.IgnoreUserToken;
import com.ricky.manager.auth.core.UsernameNotFoundException;
import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.service.UserService;
import com.ricky.manager.vo.user.UserLoginVO;
import com.ricky.manager.vo.user.UserTokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author RickyCharles
 * @apiNote
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "用户管理", tags = "用户管理接口")
public class UserController {

	@Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param data 登录信息
     * @return
     */
    @ApiOperation(value = "用户登录", notes="{account:xx, password:xx}")
    @PostMapping("/login")
    @IgnoreUserToken
    public BaseResponseBean login(@Validated @RequestBody UserLoginVO data, HttpServletRequest request) throws UsernameNotFoundException {
        Assert.hasText(data.getAccount() ,"请输入账号或密码!");
        Assert.hasText(data.getPassword() ,"请输入账号或密码!");
        data.setRemoteHost(request.getRemoteHost());
        return userService.login(data);
    }

    /**
     * 用户退出
     * @return
     */
    @ApiOperation(value = "用户退出", notes="{token:xx, userId:xx}")
    @PostMapping("/logout")
    public BaseResponseBean logout(@Validated @RequestBody UserTokenVO vo){
        userService.logout(vo);
        return BaseResponseBean.ok("退出成功");
    }

}
