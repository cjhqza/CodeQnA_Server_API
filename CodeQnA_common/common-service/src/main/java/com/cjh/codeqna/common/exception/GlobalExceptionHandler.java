package com.cjh.codeqna.common.exception;

import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: cjh
 * @Description: 统一异常处理类
 * @Create: 2024-12-25 21:51
 */
@ControllerAdvice   // 增加一个Controller增强器，对Controller进行一个统一的操作和处理
public class GlobalExceptionHandler {
    // 全局异常处理
    @ExceptionHandler(Exception.class)  // 指定出现异常时执行
    @ResponseBody
    public Result error() {
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    // 自定义异常处理
    @ExceptionHandler(CodeQnAException.class)
    @ResponseBody
    public Result error(CodeQnAException e) {
        return Result.build(null, e.getResultCodeEnum());
    }
}
