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
public class GoodsManagement implements Serializable {
    
    private static final long serialVersionUID = -7341281593878953146L;
        
    //货物主键
    private Long goodsId;
    //货物名字
    private String goodsName;
    //货物数量
    private Long goodsNumber;
    //昨天货物数量
    private Long yesterdayGoodsNumber;
    //上周货物数量
    private Long lastWeekGoodsNumber;
    //上月货物数量
    private Long lastMonthGoodsNumber;
    //上季度货物数量
    private Long lastQuarterGoodsNumber;
    //去年货物数量
    private Long lastYearGoodsNumber;
    //最后一位操作该货物的操作员id
    private Long lastOperatorId;
    //货物上限
    private Long quantityCeiling;
    //货物下限
    private Long quantityFloor;
    //库存状态id
    private Integer goodsStatusId;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}
