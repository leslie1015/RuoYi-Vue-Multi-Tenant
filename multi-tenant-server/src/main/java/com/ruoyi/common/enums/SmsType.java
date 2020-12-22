package com.ruoyi.common.enums;

/**
 * 工单短信类型
 */
public enum SmsType {

    CREATE(1, "创建工单提醒"),
    ALLOCATION(2, "分配工单提醒"),
    FINISH(3, "完成工单提醒"),
    TIME_OUT(4, "工单超时提醒");

    // 类型
    private Integer smsType;

    private String desc;

    SmsType(Integer smsType, String desc) {
        this.smsType = smsType;
        this.desc = desc;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}