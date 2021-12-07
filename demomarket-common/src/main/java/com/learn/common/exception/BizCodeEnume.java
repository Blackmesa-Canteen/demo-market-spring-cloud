package com.learn.common.exception;

/**
 * @author 996Worker
 * @program demo-Mail-demo
 * @description
 * @create 2021-12-06 18:54
 */
public enum BizCodeEnume {
    UNKNOWN_EXCEPTION(10000, "未知异常"),
    VALIDATION_EXCEPTION(10001, "参数格式错误");

    private final Integer code;
    private final String message;

    BizCodeEnume(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}