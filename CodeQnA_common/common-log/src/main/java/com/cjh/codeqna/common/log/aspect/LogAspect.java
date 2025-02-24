package com.cjh.codeqna.common.log.aspect;

import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.common.log.annotation.Log;
import com.cjh.codeqna.common.log.service.SysOperLogService;
import com.cjh.codeqna.common.log.utils.LogUtil;
import com.cjh.codeqna.model.entity.log.SysOperLog;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: cjh
 * @Description: 日志切面类
 * @Create: 2025-02-24 15:26
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysOperLogService sysOperLogService;

    // 环绕通知
    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog) {
        // 业务方法调用之前，封装数据
        SysOperLog sysOperLog = new SysOperLog();
        LogUtil.beforeHandleLog(sysLog, joinPoint, sysOperLog);

        // 业务方法
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            // 调用业务方法之后，封装数据
            LogUtil.afterHandleLog(sysLog, proceed, sysOperLog, 0, null);
        } catch (Throwable e) {
            // e.printStackTrace();
            LogUtil.afterHandleLog(sysLog, proceed, sysOperLog, 1, e.getMessage());
            // 抛出运行时异常
            throw new CodeQnAException(ResultCodeEnum.DATA_ERROR);
        }

        // 调用service方法把日志信息添加到数据库里面
        sysOperLogService.insert(sysOperLog);
        return proceed;
    }
}
