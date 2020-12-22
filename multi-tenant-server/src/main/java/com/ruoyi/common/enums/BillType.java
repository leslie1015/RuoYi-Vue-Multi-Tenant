package com.ruoyi.common.enums;

/**
 * 单据类型
 */
public enum BillType {

    WXGD("GD", "维修工单"),
    SBBH("BH", "设备编号");

    private String code;

    private String desc;

    BillType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
