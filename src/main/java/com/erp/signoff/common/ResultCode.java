package com.erp.signoff.common;

import lombok.Getter;

/**
 * 统一响应状态码。
 */
@Getter
public enum ResultCode {

    /** 成功 */
    SUCCESS(200, "操作成功"),

    /** 请求参数错误 */
    PARAM_ERROR(400, "请求参数错误"),

    /** 业务处理失败 */
    BUSINESS_ERROR(409, "业务处理失败"),

    /** 系统异常 */
    SYSTEM_ERROR(500, "系统异常"),

    /** 功能尚未实现（骨架阶段占位） */
    NOT_IMPLEMENTED(501, "功能尚未实现");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
