package com.cjh.codeqna.common.exception;

import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 自定义异常
 * @Create: 2024-12-25 21:56
 */
@Data
public class CodeQnAException extends RuntimeException{
    private Integer code ;          // 错误状态码
    private String message ;        // 错误消息

    private ResultCodeEnum resultCodeEnum ;     // 封装错误状态码和错误消息

    public CodeQnAException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum ;
        this.code = resultCodeEnum.getCode() ;
        this.message = resultCodeEnum.getMessage();
    }

    public CodeQnAException(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }
}
