package com.hy.warehousemanagement.model;

/**
 * 系统错误码定义
 */
public enum SystemErrorCodeEnum {

    DATABASE_ERROR("C0300","数据库读写异常"),

    CREAT_ORDER_ID_ERROR("C03001","创建订单异常"),

    DATA_ERROR("C03002","前端数据传输异常"),

    GOODS_ALREADY_EXISTS("C03003","货物已经存在")
    ;



    //错误码
    private String errorCode;

    //错误描述
    private String errorDesc;

    SystemErrorCodeEnum(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

   public  String getErrorCode() {
        return errorCode;
   }

    public  String getErrorDesc() {
        return errorDesc;
    }
}
