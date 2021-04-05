package com.hy.warehousemanagement.utils;

import com.hy.warehousemanagement.pojo.GoodsManagement;

public class GetResultUtil {

    public static Integer getStatusResult(GoodsManagement goodsManagement) {

        Long quantityCeiling = goodsManagement.getQuantityCeiling();
        Long quantityFloor = goodsManagement.getQuantityFloor();
        Long goodsNumber = goodsManagement.getGoodsNumber();

        //数据可用性校验
        if (quantityCeiling < 0 || quantityFloor < 0 || goodsNumber < 0) {
            return -1;
        }

        //超过上限
        if (goodsNumber > quantityCeiling) {
            return -1;
        }

        //超过下限
        if (goodsNumber < quantityFloor) {
            return -1;
        }

        //接近上限
        if (goodsNumber > quantityCeiling * 0.9) {
            return 2;
        }

        //如果下限值为0
        if (quantityFloor == 0) {
            //接近下限
            if (goodsNumber < 10) {
                return 3;
            }
        } else {
            //接近下限
            if (goodsNumber < quantityFloor * 0.1) {
                return 3;
            }
        }

        return 1;
    }
}
