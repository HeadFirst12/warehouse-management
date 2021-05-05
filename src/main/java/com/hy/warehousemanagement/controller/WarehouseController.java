package com.hy.warehousemanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.model.AjaxResult;
import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.pojo.OutWarehouseManagement;
import com.hy.warehousemanagement.service.WareHouseService;
import com.hy.warehousemanagement.utils.AssembleResultUtil;
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
    @RequestMapping("/app/content/get-entry-warehouse-list")
    @ResponseBody
    public LayResult getEntryWarehouseList(LayRequest layRequest) {
        LayResult entryWarehouseList = wareHouseService.getEntryWarehouseList(layRequest);
        return entryWarehouseList;
    }

    /**
     * 添加入库日志
     */
    @PostMapping("/warehouse/entry-warehouse-add")
    @ResponseBody
    public AjaxResult WarehouseAdd(@RequestBody EntryWarehouseManagement entryWarehouseManagement) {
        AjaxResult ajaxResult = wareHouseService.addEntryWarehouse(entryWarehouseManagement);
        return ajaxResult;
    }

    /**
     * 入库多条件搜索
     */
    @RequestMapping("/warehouse/entry-goods-search")
    @ResponseBody
    public LayResult searchEntryGoodsByGoods(EntryWarehouseManagement entryWarehouseManagement,LayRequest layRequest) {
        LayResult layResult = wareHouseService.searchEntryGoodsByGoods(entryWarehouseManagement,layRequest);
        return layResult;
    }

    /**
     * 获取出库日志列表
     * @return
     */
    @RequestMapping("/app/content/get-out-warehouse-list")
    @ResponseBody
    public LayResult getOutWarehouseList(LayRequest layRequest) {
        LayResult OutWarehouseList = wareHouseService.getOutWarehouseList(layRequest);
        return OutWarehouseList;
    }

    /**
     * 添加入库日志
     */
    @PostMapping("/warehouse/out-warehouse-add")
    @ResponseBody
    public AjaxResult OutWarehouseAdd(@RequestBody OutWarehouseManagement outWarehouseManagement) {
        AjaxResult ajaxResult = wareHouseService.addOutWarehouse(outWarehouseManagement);
        return ajaxResult;
    }

    /**
     * 入库多条件搜索
     */
    @RequestMapping("/warehouse/out-goods-search")
    @ResponseBody
    public LayResult searchOutGoodsByGoods(OutWarehouseManagement outWarehouseManagement,LayRequest layRequest) {
        LayResult layResult = wareHouseService.searchOutGoodsByOutGoods(outWarehouseManagement,layRequest);
        return layResult;
    }


    /**
     * 获取库存列表
     */
    @RequestMapping("/warehouse/goods-list")
    @ResponseBody
    public LayResult getWarehouseWareList(LayRequest layRequest) {
        LayResult layResult = wareHouseService.getWarehouseGoodsList(layRequest);
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
    public AjaxResult goodsDel(@RequestBody JSONObject goodsIdListJson) {
        try {
            return wareHouseService.delGoods(goodsIdListJson);
        } catch (Exception e) {
            e.printStackTrace();
            return AssembleResultUtil.assembleAjaxFailResult();
        }
    }

    /**
     * 编辑库存
     */
    @PostMapping("/warehouse/goods-edit")
    @ResponseBody
    public AjaxResult editGoods(@RequestBody GoodsManagement goodsManagement) {
        return wareHouseService.editGoods(goodsManagement);
    }

    /**
     * 跳转到库存页面并回显数据
     */
    @GetMapping("/app/work-order/edit-goods-form")
    public ModelAndView gotoEditGoodsFormView(String goodsId) {
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
    public LayResult searchGoodsByGoods(GoodsManagement goodsManagement,LayRequest layRequest) {
        LayResult layResult = wareHouseService.searchGoodsByGoods(goodsManagement,layRequest);
        return layResult;
    }
}
