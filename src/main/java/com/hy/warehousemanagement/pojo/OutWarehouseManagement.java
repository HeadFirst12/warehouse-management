package com.hy.warehousemanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OutWarehouseManagement implements Serializable {

    private static final long serialVersionUID = 8871196140216257477L;

    //出货主键
    private Long outGoodsId;
    //出库订单号
    private String outOrderId;
    //出货商品名
    private String outGoodsName;
    //出货商品数量
    private Long outGoodsNumber;
    //出货商品时间
    private Date outGoodsTime;
    //出货前商品数量
    private Long beforeOutGoodsNumber;
    //出货后商品数量
    private Long afterOutGoodsNumber;
    //操作员Id
    private Long operatorId;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

}
