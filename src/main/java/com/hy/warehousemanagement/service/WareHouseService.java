package com.hy.warehousemanagement.service;

import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;

public interface WareHouseService {

    /**
     * 获取出库日志列表
     * @return
     */
    LayRequest getEntryWarehouseList();

    /**
     * 获取出库日志总数
     * @return
     */
    LayRequest countEntryWarehouseNumber();

    /**
     * 添加入库日志
     * @param entryWarehouseManagement
     */
    void addEntryWarehouse(EntryWarehouseManagement entryWarehouseManagement);

    /**
     * 获取库存列表
     * @return
     */
    LayRequest getWarehouseGoodsList();

    /**
     * 添加商品库存
     * @param goodsManagement
     */
    void addGoods(GoodsManagement goodsManagement);

    /**
     * 删除商品库存
     * @param goodsManagement
     */
    void delGoods(GoodsManagement goodsManagement);

    /**
     * 编辑库存
     * @param goodsManagement
     */
    void editGoods(GoodsManagement goodsManagement);

    /**
     * 通过goodsId获取
     * @param goodsId
     * @return
     */
    GoodsManagement getGoodsByGoodsId(Long goodsId);
}
