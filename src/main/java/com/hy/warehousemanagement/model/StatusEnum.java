package com.hy.warehousemanagement.model;

/**
 * @author hy
 */

public enum StatusEnum {

    //layUi表单请求code响应
    CODE_SUCCESS("0","成功"),

    AJAX_CODE_SUCCESS("00000000","通讯成功"),

    AJAX_CODE_FAIL("00000001","通讯失败"),

    AJAX_STATUS_SUCCESS("09","业务成功"),

    AJAX_STATUS_FAIL("10","业务失败"),

    AJAX_STATUS_IN_PROCESS("01","业务超时");

    private final String code;

    private final String desc;

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
