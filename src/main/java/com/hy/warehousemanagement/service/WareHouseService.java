package com.hy.warehousemanagement.service;

import com.hy.warehousemanagement.model.AjaxResult;
import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.model.WarehouseData;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.pojo.OutWarehouseManagement;

public interface WareHouseService {

    /**
     * 获取出库日志列表
     * @return
     * @param layRequest
     */
    LayResult getEntryWarehouseList(LayRequest layRequest);

    /**
     * 添加入库日志
     * @param entryWarehouseManagement
     * @return
     */
    AjaxResult addEntryWarehouse(EntryWarehouseManagement entryWarehouseManagement);

    /**
     * 获取库存列表
     * @return
     * @param layRequest
     */
    LayResult getWarehouseGoodsList(LayRequest layRequest);

    /**
     * 添加商品库存
     * @param goodsManagement
     * @return
     */
    AjaxResult addGoods(GoodsManagement goodsManagement);

    /**
     * 删除商品库存
     * @param goodsManagement
     * @return
     */
    AjaxResult delGoods(GoodsManagement goodsManagement);

    /**
     * 编辑库存
     * @param goodsManagement
     * @return
     */
    AjaxResult editGoods(GoodsManagement goodsManagement);

    /**
     * 通过goodsId获取
     * @param goodsId
     * @return
     */
    GoodsManagement getGoodsByGoodsId(String goodsId);

    /**
     * 多条件查询库存
     * @param goodsManagement
     * @param layRequest
     * @return
     */
    LayResult searchGoodsByGoods(GoodsManagement goodsManagement, LayRequest layRequest);

    /**
     * 多条件库存日志查询
     * @param goodsManagement
     * @param layRequest
     * @return
     */
    LayResult searchEntryGoodsByGoods(EntryWarehouseManagement goodsManagement, LayRequest layRequest);

    /**
     * 获取出库日志列表
     * @param layRequest
     * @return
     */
    LayResult getOutWarehouseList(LayRequest layRequest);

    /**
     * 添加出库日志
     * @param outWarehouseManagement
     * @return
     */
    AjaxResult addOutWarehouse(OutWarehouseManagement outWarehouseManagement);

    /**
     * 多条件搜索出库日志
     * @param outWarehouseManagement
     * @param layRequest
     * @return
     */
    LayResult searchOutGoodsByOutGoods(OutWarehouseManagement outWarehouseManagement, LayRequest layRequest);

    /**
     * 获取库存中不同状态的库存数量
     * @return
     */
    WarehouseData selectWareHouseData();
}
