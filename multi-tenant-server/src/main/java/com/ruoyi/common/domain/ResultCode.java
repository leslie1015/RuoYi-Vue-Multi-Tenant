package com.ruoyi.common.domain;

public enum ResultCode {
    SUCCESS(0,"操作成功"),
    FAILURE(1,"操作失败"),
    LOGIN_FAILURE(2, "认证失败");

    private String desc;
    private int code;

    private ResultCode(int code, String desc)
    {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc()
    {
        return desc;
    }

    public int getCode()
    {
        return code;
    }

    public static ResultCode getByCode(int code) {
        for (ResultCode errorCode : ResultCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return null;
    }
}
