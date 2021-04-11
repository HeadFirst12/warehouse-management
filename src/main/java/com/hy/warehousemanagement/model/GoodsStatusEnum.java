package com.hy.warehousemanagement.model;

public enum GoodsStatusEnum {

    OVER_CEILING_STATUS(5,"11","超过上限"),
    NEAR_CEILING_STATUS(4,"10","接近上限"),
    NORMAL_STATUS(3,"09","正常"),
    NEAR_FLOOR_STATUS(2,"01","接近下限"),
    OVER_FLOOR_STATUS(1,"09","超过下限");

    //货物状态主键
    private Integer goodsStatusId;
    //货物状态
    private String goodsStatus;
    //货物状态描述
    private String goodsStatusDesc;

    GoodsStatusEnum(Integer goodsStatusId, String goodsStatus, String goodsStatusDesc) {
        this.goodsStatusId = goodsStatusId;
        this.goodsStatus = goodsStatus;
        this.goodsStatusDesc = goodsStatusDesc;
    }

    public Integer getGoodsStatusId() {
        return goodsStatusId;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public String getGoodsStatusDesc() {
        return goodsStatusDesc;
    }
}
