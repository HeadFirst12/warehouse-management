package com.hy.warehousemanagement.utils;

import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.model.GoodsStatusEnum;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import com.hy.warehousemanagement.pojo.GoodsManagement;

public class GetResultUtil {

    public static Integer getStatusResult(GoodsManagement goodsManagement) {

        Long quantityCeiling = goodsManagement.getQuantityCeiling();
        Long quantityFloor = goodsManagement.getQuantityFloor();
        Long goodsNumber = goodsManagement.getGoodsNumber();

        //初始化一个结果值
        Integer result = GoodsStatusEnum.NORMAL_STATUS.getGoodsStatusId();

        //数据可用性校验
        if (quantityCeiling < 0 || quantityFloor < 0 || goodsNumber < 0) {
            throw new WarehouseException(SystemErrorCodeEnum.GOODS_DATA_WRONGFUL);
        }
        if(quantityCeiling < quantityFloor) {
            throw new WarehouseException(SystemErrorCodeEnum.UPDATE_GOODS_FLOOR_MORE_THAN_CEILING);
        }

        //如果上限为0直接掉
        if (goodsNumber >= quantityCeiling * 1.5) {
            //数量大于上限50%,拒绝
            throw new WarehouseException(SystemErrorCodeEnum.UPDATE_GOODS_NUMBER_TOO_MUCH);
        } else if (goodsNumber < quantityCeiling * 1.5 && goodsNumber >= quantityCeiling) {
            //数量小于上限的50%,但是还是高于等于上限 给高于上限的状态
            return GoodsStatusEnum.OVER_CEILING_STATUS.getGoodsStatusId();
        } else if (goodsNumber < quantityCeiling && goodsNumber >= quantityCeiling * 0.9) {
            //数量小于上限，但是超过上限的90% 给接近上限的状态
            return GoodsStatusEnum.NEAR_CEILING_STATUS.getGoodsStatusId();
        }

        if (quantityFloor != 0) {
            //下限如果为0 的话是要特殊处理一下
            if (goodsNumber < quantityCeiling * 0.9 && goodsNumber >= quantityFloor * 1.1) {
                //数量小于上限的90%,并且大于下限110% 给正常的状态
                return GoodsStatusEnum.NORMAL_STATUS.getGoodsStatusId();
            } else if (goodsNumber < quantityFloor * 1.1 && goodsNumber >= quantityFloor) {
                //数量小于下限的110%,但是大于等于下限 给接近下限的状态
                return GoodsStatusEnum.NEAR_FLOOR_STATUS.getGoodsStatusId();
            } else if (goodsNumber < quantityFloor && goodsNumber >= quantityFloor * 0.5) {
                //数量小于下限，但大于下限的50% 给超出下限状态
                return GoodsStatusEnum.OVER_FLOOR_STATUS.getGoodsStatusId();
            } else if (goodsNumber < quantityFloor * 0.5) {
                throw new WarehouseException(SystemErrorCodeEnum.UPDATE_GOODS_NUMBER_TOO_LITTLE);
            }
        } else {
            //如果下限为0
            if(goodsNumber >= 10) {
                //库存大于10 给正常状态
                return GoodsStatusEnum.NORMAL_STATUS.getGoodsStatusId();
            }else {
                //库存小于10 给接近下限状态
                return GoodsStatusEnum.NEAR_FLOOR_STATUS.getGoodsStatusId();
            }
        }

        return result;
    }
}
