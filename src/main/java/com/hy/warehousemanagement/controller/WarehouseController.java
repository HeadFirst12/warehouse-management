package com.hy.warehousemanagement.controller;

import com.hy.warehousemanagement.model.AjaxResult;
import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.service.WareHouseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public LayResult getOutWarehouseList() {
        LayResult entryWarehouseList = wareHouseService.getEntryWarehouseList();
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
    public LayResult getWarehouseWareList() {
        LayResult layResult = wareHouseService.getWarehouseGoodsList();
        return layResult;
    }

    /**
     * 添加库存
     */
    @PostMapping("/warehouse/goods-add")
    @ResponseBody
    public AjaxResult goodsAdd(@RequestBody GoodsManagement goodsManagement) {
        AjaxResult ajaxResult = wareHouseService.addGoods(goodsManagement);
        return ajaxResult;
    }

    /**
     * 删除库存
     */
    @PostMapping("/warehouse/goods-del")
    @ResponseBody
    public void goodsDel(@RequestBody GoodsManagement goodsManagement) {
        wareHouseService.delGoods(goodsManagement);
    }

    /**
     * 编辑库存
     */
    @PostMapping("/warehouse/goods-edit")
    @ResponseBody
    public void editGoods(@RequestBody GoodsManagement goodsManagement) {
        wareHouseService.editGoods(goodsManagement);
    }

    /**
     * 跳转到库存页面并回显数据
     */
    @GetMapping("/app/work-order/edit-goods-form")
    public ModelAndView gotoEditGoodsFormView(Long goodsId) {
        GoodsManagement goodsManagement = wareHouseService.getGoodsByGoodsId(goodsId);
        ModelAndView mad = new ModelAndView("/views/app/workorder/editgoodsform");
        mad.addObject(Constant.GOODS_MANAGEMENT,goodsManagement);
        return mad;
    }

    /**
     * 库存多条件搜索
     */
    @RequestMapping("/warehouse/goods-search")
    @ResponseBody
    public LayResult searchGoodsByGoods(GoodsManagement goodsManagement) {
        LayResult layResult = wareHouseService.searchGoodsByGoods(goodsManagement);
        return layResult;
    }
}
