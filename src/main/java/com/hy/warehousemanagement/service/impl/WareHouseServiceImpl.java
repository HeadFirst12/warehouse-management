package com.hy.warehousemanagement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.mapper.EntryWarehouseManagementMapper;
import com.hy.warehousemanagement.mapper.GoodsManagementMapper;
import com.hy.warehousemanagement.model.*;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.service.WareHouseService;
import com.hy.warehousemanagement.utils.AssembleResultUtil;
import com.hy.warehousemanagement.utils.CreatOrderIdUtil;
import com.hy.warehousemanagement.utils.GetResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 仓库管理服务层
 */
@Service
public class WareHouseServiceImpl implements WareHouseService {

    @Resource
    private EntryWarehouseManagementMapper entryWarehouseManagementMapper;

    @Resource
    private GoodsManagementMapper goodsManagementMapper;

    @Override
    public LayResult getEntryWarehouseList() {
        List<EntryWarehouseManagement> entryWarehouseManagements = entryWarehouseManagementMapper.queryEntryWarehouseList();
        Integer countEntryWarehouseNumber = entryWarehouseManagementMapper.countEntryWarehouseNumber();
        LayResult<EntryWarehouseManagement> entryWarehouseManagementLayResult = new LayResult<>();

        entryWarehouseManagementLayResult.setMsg("");
        entryWarehouseManagementLayResult.setCount(countEntryWarehouseNumber.toString());
        entryWarehouseManagementLayResult.setCode(StatusEnum.CODE_SUCCESS.getCode());
        entryWarehouseManagementLayResult.setData(entryWarehouseManagements);

        return entryWarehouseManagementLayResult;
    }

    @Override
    public LayResult countEntryWarehouseNumber() {
        return null;
    }

    @Override
    public void addEntryWarehouse(EntryWarehouseManagement entryWarehouseManagement) {
        //订单生成
        entryWarehouseManagement.setEntryOrderId(CreatOrderIdUtil.creatOrderId(Constant.ENTRY_ORDER_PREFIX, Constant.ORDER_LENGTH));
        //添加商品名
        entryWarehouseManagement.setEntryGoodsName("暂时随便填了");
        //添加商品入库前数量
        entryWarehouseManagement.setBeforeEntryGoodsNumber(0L);
        //添加商品入库后数量
        Long afterEntryGoodsNumber = entryWarehouseManagement.getEntryGoodsNumber() + entryWarehouseManagement.getBeforeEntryGoodsNumber();
        entryWarehouseManagement.setAfterEntryGoodsNumber(afterEntryGoodsNumber);
        //添加操作员id
        entryWarehouseManagement.setOperatorId(1L);

        Integer result = entryWarehouseManagementMapper.addEntryWarehouse(entryWarehouseManagement);
        if (result <= 0) {
            throw new WarehouseException(SystemErrorCodeEnum.DATABASE_ERROR);
        }

    }

    @Override
    public LayResult getWarehouseGoodsList() {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.queryGoodsList();
        Integer goodsNumber = goodsManagementMapper.countGoodsNumber();
        LayResult layResult = AssembleResultUtil.assembleLayResult(goodsManagements, goodsNumber);
        return layResult;
    }

    @Override
    public AjaxResult addGoods(GoodsManagement goodsManagement) {

        GoodsManagement goodsManagementByGoodsName = goodsManagementMapper.getGoodsManagementByGoodsName(goodsManagement.getGoodsName());

        AjaxResult ajaxResult;

        try {
            if (goodsManagementByGoodsName == null) {
                //库存中不存在该商品需要新增
                goodsManagement.setLastOperatorId(1L);
                goodsManagement.setGoodsId(CreatOrderIdUtil.getGoodsId());
                Integer statusResult = GetResultUtil.getStatusResult(goodsManagement);
                if (statusResult <= 0) {
                    throw new WarehouseException(SystemErrorCodeEnum.DATA_ERROR);
                }
                goodsManagement.setGoodsStatusId(statusResult);
                Integer insertResult = goodsManagementMapper.insertGoods(goodsManagement);

                ajaxResult = AssembleResultUtil.assembleAjaxResult(insertResult);

            } else {
                //库存中已经存在不能新增货物
                throw new WarehouseException(SystemErrorCodeEnum.GOODS_ALREADY_EXISTS);
            }
        } catch (WarehouseException e) {
            JSONObject exceptionInfoJson = AssembleResultUtil.getExceptionInfo(e.getMessage());
            ajaxResult = AssembleResultUtil.assembleAjaxResult(exceptionInfoJson);
            e.printStackTrace();
        }

        return ajaxResult;
    }

    @Override
    public void delGoods(GoodsManagement goodsManagement) {
        String goodsId = goodsManagement.getGoodsId();
        Integer delResult =  goodsManagementMapper.delGoodsManagementById(goodsId);
    }

    @Override
    public void editGoods(GoodsManagement goodsManagement) {
        Integer updateResult =  goodsManagementMapper.updateGoodsManagement(goodsManagement);
    }

    @Override
    public GoodsManagement getGoodsByGoodsId(Long goodsId) {
        GoodsManagement goodsManagementById = goodsManagementMapper.getGoodsManagementById(goodsId);
        return goodsManagementById;
    }

    @Override
    public LayResult searchGoodsByGoods(GoodsManagement goodsManagement) {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.selectGoodsByGoodsManagement(goodsManagement);
        Integer goodsNumber = goodsManagementMapper.countGoodsNumberByGoodsManagement(goodsManagement);
        LayResult layResult = AssembleResultUtil.assembleLayResult(goodsManagements, goodsNumber);
        return layResult;
    }


}
