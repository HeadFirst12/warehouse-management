package com.hy.warehousemanagement.controller;

import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.service.WareHouseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class WarehouseController {

    @Resource
    private WareHouseService wareHouseService;

    /**
     * 获取入库日志列表
     * @return
     */
    @RequestMapping("/app/content/get-out-warehouse-list")
    @ResponseBody
    public LayRequest getOutWarehouseList() {
        LayRequest entryWarehouseList = wareHouseService.getEntryWarehouseList();
        return entryWarehouseList;
    }

    /**
     * 添加入库日志
     */
    @PostMapping("/warehouse/entry-warehouse-add")
    @ResponseBody
    public void entryWarehouseAdd(@RequestBody EntryWarehouseManagement entryWarehouseManagement) {
        wareHouseService.addEntryWarehouse(entryWarehouseManagement);
    }

    /**
     * 获取库存列表
     */
    @RequestMapping("/warehouse/goods-list")
    @ResponseBody
    public LayRequest getWarehouseWareList() {
        LayRequest layRequest = wareHouseService.getWarehouseGoodsList();
        return layRequest;
    }

    /**
     * 添加库存
     */
    @PostMapping("/warehouse/goods-add")
    @ResponseBody
    public void goodsAdd(@RequestBody GoodsManagement goodsManagement) {
        System.out.println(goodsManagement);
        wareHouseService.addGoods(goodsManagement);
    }
}
