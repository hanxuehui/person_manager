package com.ricky.manager.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户
 * @Author: Ricky Charles
 * @Date: 2022-05-10 19:26
 **/
@Data
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /** 用户登录账号 */
    private String account;

    /** 用户密码，未设置不允许登录 */
    private String password;

    /** 用户名 */
    private String userName;

    /** 用户状态（0：停用  1：启用 */
    private Integer userState;

    /** 用户身份（admin：总管理员 common：普通用户）*/
    private String role;

    /** 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crtTime;

    /** 创建人 */
    private String crtPerson;

    /** 修改时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updTime;

    /** 修改人 */
    private String updPerson;

    /** 注销时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelTime;

    /** 用户最后登录IP */
    private String lastVisitIp;

}
