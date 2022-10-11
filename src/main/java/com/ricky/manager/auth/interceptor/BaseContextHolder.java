package com.ricky.manager.auth.interceptor;

/**
 *
 * @author xjsh
 */
public class BaseContextHolder {

    private static ThreadLocal<BaseContext> threadLocal = new ThreadLocal<>();

    static void set(BaseContext context) {
        threadLocal.set(context);
    }

    public static BaseContext getContext() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
