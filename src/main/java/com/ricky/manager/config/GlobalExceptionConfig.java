package com.ricky.manager.config;

import com.ricky.manager.entity.base.BaseResponseBean;
import com.ricky.manager.exception.LogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author RickyCharles
 * @apiNote 全局统一异常处理类
 */
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionConfig {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionConfig.class);

	/**
	 * 业务层异常的异常会被这个方法捕获
	 */
    @ExceptionHandler(LogicException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponseBean businessExceptionHandler(LogicException ex) {
        logger.error(ex.getMessage(),ex);
        return BaseResponseBean.error(ex.getMessage(),null);
    }
}
