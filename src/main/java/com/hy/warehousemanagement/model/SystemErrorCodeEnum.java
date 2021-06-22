package com.hy.warehousemanagement.model;

/**
 * 系统错误码定义
 * @author hy
 */
public enum SystemErrorCodeEnum {

    DATABASE_ERROR("C0300","数据库读写异常")

    ,CREAT_ORDER_ID_ERROR("C03001","创建订单异常")

    ,DATA_ERROR("C03002","前端数据传输异常")

    ,GOODS_ALREADY_EXISTS("C03003","货物商品名已经存在")

    ,SYSTEM_ERROR("A01001","系统异常")

    ,UPDATE_GOODS_ERROR("C03004","更新库存数量未成功")

    ,GOODS_DATA_WRONGFUL("C03005","库存表中的数据不合法或前段传输的数据不合法，系统拒绝更新")

    ,UPDATE_GOODS_NUMBER_TOO_MUCH("C03006","更新的数据超过上限50%以上，系统拒绝更新")

    ,UPDATE_GOODS_NUMBER_TOO_LITTLE("C03007","更新的数据不足下限50%以下，系统拒绝更新")

    ,UPDATE_GOODS_FLOOR_MORE_THAN_CEILING("C03007","下限设置超过上限，不允许操作")
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
