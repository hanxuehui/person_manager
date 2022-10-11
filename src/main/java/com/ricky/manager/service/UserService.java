package com.ricky.manager.service;

import com.ricky.manager.auth.core.UsernameNotFoundException;
import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.vo.user.UserLoginVO;
import com.ricky.manager.vo.user.UserTokenVO;

/**
 * @Description: 用户管理接口
 * @Author: Ricky Charles
 * @Date: 2022-05-10 19:58
 **/
public interface UserService {
    /**
     * 用户登陆
     * @param data
     * @return
     * @throws UsernameNotFoundException
     */
    BaseResponseBean login(UserLoginVO data) throws UsernameNotFoundException;

    /**
     * 用户退出
     * @param vo
     */
    void logout(UserTokenVO vo);
}
