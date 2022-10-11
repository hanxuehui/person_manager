package com.ricky.manager.service.impl;

import com.ricky.manager.auth.core.TokenHelper;
import com.ricky.manager.auth.core.UsernameNotFoundException;
import com.ricky.manager.auth.interceptor.BaseContext;
import com.ricky.manager.entity.User;
import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.mapper.UserMapper;
import com.ricky.manager.service.UserService;
import com.ricky.manager.vo.user.UserLoginVO;
import com.ricky.manager.vo.user.UserTokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

/**
 * @Description: 用户管理实现类
 * @Author: Ricky Charles
 * @Date: 2022-05-10 19:59
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenHelper tokenHelper;

    @Override
    public BaseResponseBean login(UserLoginVO data) throws UsernameNotFoundException {
        List<User> users = getUserList(data.getAccount());
        if (CollectionUtils.isEmpty(users)){
            return BaseResponseBean.error("用户名或密码输入错误!");
        }else {
            User user = users.get(0);
            if (!user.getPassword().equals(data.getPassword()) || user.getUserState() == 0){
                throw new UsernameNotFoundException("账号不存在, account:" + data.getAccount());
            }else{
                BaseContext context = new BaseContext();
                context.setUserId(user.getId().toString());
                context.setAccount(user.getAccount());
                context.setName(user.getUserName());
                context.setRole(user.getRole());
                String token = UUID.randomUUID().toString();
                context.setToken(token);
                tokenHelper.saveToken(context);
                return BaseResponseBean.ok("登录成功",context);
            }
        }
    }

    @Override
    public void logout(UserTokenVO vo) {
        User user = userMapper.selectByPrimaryKey(Long.parseLong(vo.getUserId()));
        if (!ObjectUtils.isEmpty(user)){
            tokenHelper.removeToken(user.getAccount(), vo.getToken());
        }
    }

    private List<User> getUserList(String account) throws UsernameNotFoundException {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (org.springframework.util.StringUtils.hasText(account)){
            criteria.andEqualTo("account", account);
        }
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()) {
            log.info("用户名不存在:{}", account);
            throw new UsernameNotFoundException("账号不存在, account:" + account);
        }else{
            return users;
        }
    }
}
