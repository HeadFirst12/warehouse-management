package com.hy.warehousemanagement.model;

public enum StatusEnum {

    //layUi表单请求code响应
    CODE_SUCCESS("0","成功");

    private String code;

    private String desc;

    StatusEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
