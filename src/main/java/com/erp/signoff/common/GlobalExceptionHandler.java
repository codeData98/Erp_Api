package com.erp.signoff.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局异常处理器：所有异常统一转换为 {@link Result} 返回。
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 业务异常：返回业务状态码与提示信息 */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("业务异常: {}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /** 请求参数异常 */
    @ExceptionHandler({MissingServletRequestParameterException.class,
                       MethodArgumentTypeMismatchException.class})
    public Result<Void> handleParamException(Exception e) {
        log.warn("参数异常: {}", e.getMessage());
        return Result.error(ResultCode.PARAM_ERROR, e.getMessage());
    }

    /** 未实现的功能（骨架阶段占位，实现完成后可移除） */
    @ExceptionHandler(UnsupportedOperationException.class)
    public Result<Void> handleNotImplemented(UnsupportedOperationException e) {
        log.warn("功能未实现: {}", e.getMessage());
        return Result.error(ResultCode.NOT_IMPLEMENTED, e.getMessage());
    }

    /** 兜底：其余未捕获异常 */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error(ResultCode.SYSTEM_ERROR);
    }
}
