package com.hy.warehousemanagement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.filter.BaseWarehouseFilter;
import com.hy.warehousemanagement.model.*;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.pojo.GoodsStatusManagement;
import com.hy.warehousemanagement.pojo.OutWarehouseManagement;
import com.hy.warehousemanagement.service.WareHouseService;
import com.hy.warehousemanagement.utils.AssembleResultUtil;
import com.hy.warehousemanagement.utils.CreatOrderIdUtil;
import com.hy.warehousemanagement.utils.GetResultUtil;
import com.hy.warehousemanagement.utils.PageUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 仓库管理服务层
 */
@Service
public class WareHouseServiceImpl extends BaseWarehouseFilter implements WareHouseService {

    //入库日志实现
    @Override
    public LayResult getEntryWarehouseList(LayRequest layRequest) {
        List<EntryWarehouseManagement> entryWarehouseManagements = entryWarehouseManagementMapper.queryEntryWarehouseList(PageUtil.getPage(layRequest));
        Integer countEntryWarehouseNumber = entryWarehouseManagementMapper.countEntryWarehouseNumber();
        List<JSONObject> entryGoodsJsonArray = mapperEntryGoods(entryWarehouseManagements);
        LayResult layResult = AssembleResultUtil.assembleLayResult(entryGoodsJsonArray, countEntryWarehouseNumber);

        return layResult;
    }

    @Override
    public AjaxResult addEntryWarehouse(EntryWarehouseManagement entryWarehouseManagement) {
        GoodsManagement goodsManagementById = goodsManagementMapper.getGoodsManagementById(entryWarehouseManagement.getEntryGoodsId());
        //订单生成
        entryWarehouseManagement.setEntryOrderId(CreatOrderIdUtil.creatOrderId(Constant.ENTRY_ORDER_PREFIX, Constant.ORDER_LENGTH));
        //添加商品名
        entryWarehouseManagement.setEntryGoodsName(goodsManagementById.getGoodsName());
        //添加商品入库前数量
        entryWarehouseManagement.setBeforeEntryGoodsNumber(goodsManagementById.getGoodsNumber());
        //添加商品入库后数量
        Long afterEntryGoodsNumber = entryWarehouseManagement.getEntryGoodsNumber() + entryWarehouseManagement.getBeforeEntryGoodsNumber();
        entryWarehouseManagement.setAfterEntryGoodsNumber(afterEntryGoodsNumber);
        //操作员
        entryWarehouseManagement.setOperatorId(authenticationFilter.getNowUserId());

        AjaxResult ajaxResult;

        //更新库存数量
        Integer updateGoodsResult = null;
        try {
            updateGoodsResult = updateGoodsNumber(goodsManagementById,afterEntryGoodsNumber);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = AssembleResultUtil.assembleAjaxResult(e.getMessage());
            return ajaxResult;
        }

        //只有库存数量更新成功才去插入库日志
        if(updateGoodsResult > 0) {
            Integer result = entryWarehouseManagementMapper.addEntryWarehouse(entryWarehouseManagement);
            ajaxResult = AssembleResultUtil.assembleAjaxResult(result);
        }else {
            ajaxResult = AssembleResultUtil.assembleAjaxResult(SystemErrorCodeEnum.UPDATE_GOODS_ERROR);
        }

        return ajaxResult;
    }

    @Override
    public LayResult searchEntryGoodsByGoods(EntryWarehouseManagement entryWarehouseManagement, LayRequest layRequest) {
        List<EntryWarehouseManagement> EntryWarehouseManagements = entryWarehouseManagementMapper.selectEntryGoodsByEntryGoods(entryWarehouseManagement,PageUtil.getPage(layRequest));
        Integer countEntryWarehouseNumber = entryWarehouseManagementMapper.countEntryWarehouseNumber();
        List<JSONObject> EntryWarehouseJSONArray = mapperEntryGoods(EntryWarehouseManagements);
        LayResult layResult = AssembleResultUtil.assembleLayResult(EntryWarehouseJSONArray, countEntryWarehouseNumber);
        return layResult;
    }

    //库存管理实现
    @Override
    public LayResult getWarehouseGoodsList(LayRequest layRequest) {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.queryGoodsList(PageUtil.getPage(layRequest));
        Integer goodsNumber = goodsManagementMapper.countGoodsNumber();
        List<JSONObject> goodsManagementJSONArray = mapperGoodsManagement(goodsManagements);
        LayResult layResult = AssembleResultUtil.assembleLayResult(goodsManagementJSONArray, goodsNumber);
        return layResult;
    }

    @Override
    public AjaxResult addGoods(GoodsManagement goodsManagement) {

        GoodsManagement goodsManagementByGoodsName = goodsManagementMapper.getGoodsManagementByGoodsName(goodsManagement.getGoodsName());

        AjaxResult ajaxResult;

        try {
            if (goodsManagementByGoodsName == null) {
                //库存中不存在该商品需要新增
                goodsManagement.setGoodsId(CreatOrderIdUtil.getGoodsId());
                goodsManagement.setGoodsStatusId(GetResultUtil.getStatusResult(goodsManagement));
                Integer insertResult = goodsManagementMapper.insertGoods(goodsManagement);
                ajaxResult = AssembleResultUtil.assembleAjaxResult(insertResult);
            } else {
                //库存中已经存在不能新增货物
                throw new WarehouseException(SystemErrorCodeEnum.GOODS_ALREADY_EXISTS);
            }
        } catch (WarehouseException e) {
            ajaxResult = AssembleResultUtil.assembleAjaxResult(e.getMessage());
            e.printStackTrace();
        }

        return ajaxResult;
    }

    @Override
    public AjaxResult delGoods(GoodsManagement goodsManagement) {
        String goodsId = goodsManagement.getGoodsId();
        Integer delResult =  goodsManagementMapper.delGoodsManagementById(goodsId);
        AjaxResult ajaxResult = AssembleResultUtil.assembleAjaxResult(delResult);
        return ajaxResult;
    }

    @Override
    public AjaxResult editGoods(GoodsManagement goodsManagement) {
        Long nowUserId = authenticationFilter.getNowUserId();
        goodsManagement.setLastOperatorId(nowUserId);
        goodsManagement.setUpdateTime(new Date());
        Integer updateResult =  goodsManagementMapper.updateGoodsManagement(goodsManagement);
        AjaxResult ajaxResult = AssembleResultUtil.assembleAjaxResult(updateResult);
        return ajaxResult;
    }

    @Override
    public GoodsManagement getGoodsByGoodsId(String goodsId) {
        GoodsManagement goodsManagementById = goodsManagementMapper.getGoodsManagementById(goodsId);
        return goodsManagementById;
    }

    @Override
    public LayResult searchGoodsByGoods(GoodsManagement goodsManagement, LayRequest layRequest) {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.selectGoodsByGoodsManagement(goodsManagement,PageUtil.getPage(layRequest));
        Integer goodsNumber = goodsManagementMapper.countGoodsNumberByGoodsManagement(goodsManagement);
        List<JSONObject> goodsManagementJSONArray = mapperGoodsManagement(goodsManagements);
        LayResult layResult = AssembleResultUtil.assembleLayResult(goodsManagementJSONArray, goodsNumber);
        return layResult;
    }

    //出库日志实现
    @Override
    public LayResult getOutWarehouseList(LayRequest layRequest) {
        List<OutWarehouseManagement> outWarehouseManagements = outWarehouseManagementMapper.queryOutWarehouseList(PageUtil.getPage(layRequest));
        Integer countOutWarehouseNumber = outWarehouseManagementMapper.countOutWarehouseNumber();
        List<JSONObject> entryGoodsJsonArray = mapperOutGoods(outWarehouseManagements);
        LayResult layResult = AssembleResultUtil.assembleLayResult(entryGoodsJsonArray, countOutWarehouseNumber);
        return layResult;
    }

    @Override
    public AjaxResult addOutWarehouse(OutWarehouseManagement outWarehouseManagement) {
        GoodsManagement goodsManagementById = goodsManagementMapper.getGoodsManagementById(outWarehouseManagement.getOutGoodsId());
        //订单生成
        outWarehouseManagement.setOutOrderId(CreatOrderIdUtil.creatOrderId(Constant.OUT_ORDER_PREFIX, Constant.ORDER_LENGTH));
        //添加商品名
        outWarehouseManagement.setOutGoodsName(goodsManagementById.getGoodsName());
        //添加商品入库前数量
        outWarehouseManagement.setBeforeOutGoodsNumber(goodsManagementById.getGoodsNumber());
        //添加商品入库后数量
        Long afterOutGoodsNumber = outWarehouseManagement.getBeforeOutGoodsNumber() - outWarehouseManagement.getOutGoodsNumber();
        outWarehouseManagement.setAfterOutGoodsNumber(afterOutGoodsNumber);
        //操作员
        outWarehouseManagement.setOperatorId(authenticationFilter.getNowUserId());

        //更新库存数量
        Integer updateGoodsResult = updateGoodsNumber(goodsManagementById,afterOutGoodsNumber);
        AjaxResult ajaxResult;
        //只有库存数量更新成功才去插出库日志
        if(updateGoodsResult > 0) {
            Integer result = outWarehouseManagementMapper.addOutWarehouse(outWarehouseManagement);
            ajaxResult = AssembleResultUtil.assembleAjaxResult(result);
        }else {
            ajaxResult = AssembleResultUtil.assembleAjaxResult(SystemErrorCodeEnum.UPDATE_GOODS_ERROR);
        }
        return ajaxResult;
    }

    @Override
    public LayResult searchOutGoodsByOutGoods(OutWarehouseManagement outWarehouseManagement, LayRequest layRequest) {
        List<OutWarehouseManagement> outWarehouseManagements = outWarehouseManagementMapper.selectOutGoodsByOutGoods(outWarehouseManagement,PageUtil.getPage(layRequest));
        Integer countOutWarehouseNumber = outWarehouseManagementMapper.countOutWarehouseNumber();
        List<JSONObject> outWarehouseJSONArray = mapperOutGoods(outWarehouseManagements);
        LayResult layResult = AssembleResultUtil.assembleLayResult(outWarehouseJSONArray, countOutWarehouseNumber);
        return layResult;
    }

    @Override
    public WarehouseData selectWareHouseData() {
        WarehouseData warehouseData = new WarehouseData();
        List<GoodsStatusManagement> goodsStatusManagements = goodsStatusManagementMapper.queryGoodsStatusList();
        for (GoodsStatusManagement goodsStatusManagement : goodsStatusManagements) {
            Integer goodsStatusId = goodsStatusManagement.getGoodsStatusId();
            Integer number = goodsManagementMapper.countGoodsNumberByGoodsStatusId(goodsStatusId);

            if (GoodsStatusEnum.OVER_CEILING_STATUS.getGoodsStatusId() == goodsStatusId) {
                warehouseData.setOverCeilingNumber(number);
            }

            if (GoodsStatusEnum.NEAR_CEILING_STATUS.getGoodsStatusId() == goodsStatusId) {
                warehouseData.setNearCeilingNumber(number);
            }

            if (GoodsStatusEnum.NORMAL_STATUS.getGoodsStatusId() == goodsStatusId) {
                warehouseData.setNormalNumber(number);
            }

            if (GoodsStatusEnum.OVER_FLOOR_STATUS.getGoodsStatusId() == goodsStatusId) {
                warehouseData.setOverFloorNumber(number);
            }

            if (GoodsStatusEnum.NEAR_FLOOR_STATUS.getGoodsStatusId() == goodsStatusId) {
                warehouseData.setNearFloorNumber(number);
            }

        }
        warehouseData.setCountNumber(goodsManagementMapper.countGoodsNumber());

        return warehouseData;
    }


}
