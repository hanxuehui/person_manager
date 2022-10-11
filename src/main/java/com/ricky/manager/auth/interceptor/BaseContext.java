package com.ricky.manager.auth.interceptor;

import lombok.Data;

/**
 * @author rickycharles
 */
@Data
public class BaseContext {

    private String token;

    private String account;

    private String name;

    private String userId;

    private String role;

}
