package com.hy.warehousemanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.filter.BaseWarehouseFilter;
import com.hy.warehousemanagement.model.*;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.pojo.GoodsStatusManagement;
import com.hy.warehousemanagement.pojo.OutWarehouseManagement;
import com.hy.warehousemanagement.service.WareHouseService;
import com.hy.warehousemanagement.utils.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 仓库管理服务层
 * @author hy
 */
@Service
public class WareHouseServiceImpl extends BaseWarehouseFilter implements WareHouseService {

    /** 入库日志实现 */
    @Override
    public LayResult getEntryWarehouseList(LayRequest layRequest) {
        List<EntryWarehouseManagement> entryWarehouseManagements = entryWarehouseManagementMapper.queryEntryWarehouseList(PageUtil.getPage(layRequest));
        Integer countEntryWarehouseNumber = entryWarehouseManagementMapper.countEntryWarehouseNumber();
        List<JSONObject> entryGoodsJsonArray = mapperEntryGoods(entryWarehouseManagements);
        return AssembleResultUtil.assembleLayResult(entryGoodsJsonArray, countEntryWarehouseNumber);
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
        Integer updateGoodsResult;
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
        List<EntryWarehouseManagement> entryWarehouseManagements = entryWarehouseManagementMapper.selectEntryGoodsByEntryGoods(entryWarehouseManagement,PageUtil.getPage(layRequest));
        Integer countEntryWarehouseNumber = entryWarehouseManagementMapper.countEntryWarehouseNumber();
        List<JSONObject> entryWarehouseJsonArray = mapperEntryGoods(entryWarehouseManagements);
        return AssembleResultUtil.assembleLayResult(entryWarehouseJsonArray, countEntryWarehouseNumber);
    }

    /** 库存管理实现 */
    @Override
    public LayResult getWarehouseGoodsList(LayRequest layRequest) {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.queryGoodsList(PageUtil.getPage(layRequest));
        Integer goodsNumber = goodsManagementMapper.countGoodsNumber();
        List<JSONObject> goodsManagementJsonArray = mapperGoodsManagement(goodsManagements);
        return AssembleResultUtil.assembleLayResult(goodsManagementJsonArray, goodsNumber);
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
                goodsManagement.setLastOperatorId(authenticationFilter.getNowUserId());
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
    public AjaxResult delGoods(JSONObject goodsIdListJson) {
        //处理前段传输过来的格式
        JSONArray goodsIdJsonList = goodsIdListJson.getJSONArray(Constant.GOODS_ID_LIST_JSON);
        //记录删除成功数量
        int delCountNum = 0;
        for (int i = 0; i < goodsIdJsonList.size(); i++) {
            JSONObject goodsIdJson = goodsIdJsonList.getJSONObject(i);
            String goodsId = goodsIdJson.getString(Constant.GOODS_ID);
            Integer delResult = goodsManagementMapper.delGoodsManagementById(goodsId);
            //每次删除成功，计数器+1
            if (delResult > 0) {
                delCountNum++;
            }
        }
        //最后如果计数器的数量等于集合的长度，则删除成功
        Integer result = delCountNum == goodsIdJsonList.size() ? 1 : 0;
        //组装返回报文
        return AssembleResultUtil.assembleAjaxResult(result);
    }

    @Override
    public AjaxResult editGoods(GoodsManagement goodsManagement) {
        Long nowUserId = authenticationFilter.getNowUserId();
        goodsManagement.setLastOperatorId(nowUserId);
        goodsManagement.setUpdateTime(new Date());
        Integer updateResult =  goodsManagementMapper.updateGoodsManagement(goodsManagement);
        return AssembleResultUtil.assembleAjaxResult(updateResult);
    }

    @Override
    public GoodsManagement getGoodsByGoodsId(String goodsId) {
        return goodsManagementMapper.getGoodsManagementById(goodsId);
    }

    @Override
    public LayResult searchGoodsByGoods(GoodsManagement goodsManagement, LayRequest layRequest) {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.selectGoodsByGoodsManagement(goodsManagement,PageUtil.getPage(layRequest));
        Integer goodsNumber = goodsManagementMapper.countGoodsNumberByGoodsManagement(goodsManagement);
        List<JSONObject> goodsManagementJsonArray = mapperGoodsManagement(goodsManagements);
        return AssembleResultUtil.assembleLayResult(goodsManagementJsonArray, goodsNumber);
    }

    //出库日志实现
    @Override
    public LayResult getOutWarehouseList(LayRequest layRequest) {
        List<OutWarehouseManagement> outWarehouseManagements = outWarehouseManagementMapper.queryOutWarehouseList(PageUtil.getPage(layRequest));
        Integer countOutWarehouseNumber = outWarehouseManagementMapper.countOutWarehouseNumber();
        List<JSONObject> entryGoodsJsonArray = mapperOutGoods(outWarehouseManagements);
        return AssembleResultUtil.assembleLayResult(entryGoodsJsonArray, countOutWarehouseNumber);
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
        List<JSONObject> outWarehouseJsonArray = mapperOutGoods(outWarehouseManagements);
        return AssembleResultUtil.assembleLayResult(outWarehouseJsonArray, countOutWarehouseNumber);
    }

    @Override
    public WarehouseData selectWareHouseData() {
        WarehouseData warehouseData = new WarehouseData();
        Integer countNumber = goodsManagementMapper.countGoodsNumber();
        String rates;
        List<GoodsStatusManagement> goodsStatusManagements = goodsStatusManagementMapper.queryGoodsStatusList();
        for (GoodsStatusManagement goodsStatusManagement : goodsStatusManagements) {
            Integer goodsStatusId = goodsStatusManagement.getGoodsStatusId();
            Integer number = goodsManagementMapper.countGoodsNumberByGoodsStatusId(goodsStatusId);
            rates = TimesUtil.doubleToStringFormat(number, countNumber);
            if (GoodsStatusEnum.OVER_CEILING_STATUS.getGoodsStatusId().equals(goodsStatusId)) {
                warehouseData.setOverCeilingNumber(number);
                warehouseData.setOverCeilingRate(rates);
            }

            if (GoodsStatusEnum.NEAR_CEILING_STATUS.getGoodsStatusId().equals(goodsStatusId)) {
                warehouseData.setNearCeilingNumber(number);
                warehouseData.setNearCeilingRate(rates);
            }

            if (GoodsStatusEnum.NORMAL_STATUS.getGoodsStatusId().equals(goodsStatusId)) {
                warehouseData.setNormalNumber(number);
                warehouseData.setNormalRate(rates);
            }

            if (GoodsStatusEnum.OVER_FLOOR_STATUS.getGoodsStatusId().equals(goodsStatusId)) {
                warehouseData.setOverFloorNumber(number);
                warehouseData.setOverFloorRate(rates);
            }

            if (GoodsStatusEnum.NEAR_FLOOR_STATUS.getGoodsStatusId().equals(goodsStatusId)) {
                warehouseData.setNearFloorNumber(number);
                warehouseData.setNearFloorRate(rates);
            }

        }
        warehouseData.setCountNumber(countNumber);

        return warehouseData;
    }

    //消息通知实现
    @Override
    public LayResult getGoodsStatusAbnormal(LayRequest layRequest) {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.selectGoodsListByStatusAbnormal(PageUtil.getPage(layRequest));
        Integer goodsNumber = goodsManagementMapper.countGoodsNumberByStatusAbnormal();
        List<JSONObject> goodsManagementJsonArray = mapperGoodsManagement(goodsManagements);
        return AssembleResultUtil.assembleLayResult(goodsManagementJsonArray, goodsNumber);
    }


}
