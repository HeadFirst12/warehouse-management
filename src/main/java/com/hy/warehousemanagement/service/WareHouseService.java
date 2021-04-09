package com.hy.warehousemanagement.service;

import com.hy.warehousemanagement.model.AjaxResult;
import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;

public interface WareHouseService {

    /**
     * 获取出库日志列表
     * @return
     */
    LayResult getEntryWarehouseList();

    /**
     * 获取出库日志总数
     * @return
     */
    LayResult countEntryWarehouseNumber();

    /**
     * 添加入库日志
     * @param entryWarehouseManagement
     */
    void addEntryWarehouse(EntryWarehouseManagement entryWarehouseManagement);

    /**
     * 获取库存列表
     * @return
     */
    LayResult getWarehouseGoodsList();

    /**
     * 添加商品库存
     * @param goodsManagement
     * @return
     */
    AjaxResult addGoods(GoodsManagement goodsManagement);

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

    /**
     * 多条件查询库存
     * @param goodsManagement
     * @return
     */
    LayResult searchGoodsByGoods(GoodsManagement goodsManagement);
}
