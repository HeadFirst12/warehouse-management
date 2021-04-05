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
public class EntryWarehouseManagement implements Serializable {

    private static final long serialVersionUID = 3394769242441837312L;
        
    //入库主键
    private Long id;
    //入库订单号
    private String entryOrderId;
    //入库商品编号
    private Long entryGoodsId;
    //入库商品名
    private String entryGoodsName;
    //入库商品数量
    private Long entryGoodsNumber;
    //入库商品时间
    private Date entryGoodsTime;
    //入库前商品数量
    private Long beforeEntryGoodsNumber;
    //入库后商品数量
    private Long afterEntryGoodsNumber;
    //操作员Id
    private Long operatorId;
    //创建时间
    private Date createTime;
}
