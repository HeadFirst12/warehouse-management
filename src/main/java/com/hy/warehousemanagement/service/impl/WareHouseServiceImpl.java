package com.hy.warehousemanagement.service.impl;

import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.mapper.EntryWarehouseManagementMapper;
import com.hy.warehousemanagement.mapper.GoodsManagementMapper;
import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.model.StatusEnum;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import com.hy.warehousemanagement.service.WareHouseService;
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
    public LayRequest getEntryWarehouseList() {
        List<EntryWarehouseManagement> entryWarehouseManagements = entryWarehouseManagementMapper.queryEntryWarehouseList();
        Integer countEntryWarehouseNumber = entryWarehouseManagementMapper.countEntryWarehouseNumber();
        LayRequest<EntryWarehouseManagement> entryWarehouseManagementLayRequest = new LayRequest<>();

        entryWarehouseManagementLayRequest.setMsg("");
        entryWarehouseManagementLayRequest.setCount(countEntryWarehouseNumber.toString());
        entryWarehouseManagementLayRequest.setCode(StatusEnum.CODE_SUCCESS.getCode());
        entryWarehouseManagementLayRequest.setData(entryWarehouseManagements);

        return entryWarehouseManagementLayRequest;
    }

    @Override
    public LayRequest countEntryWarehouseNumber() {
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
    public LayRequest getWarehouseGoodsList() {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.queryGoodsList();
        Integer goodsNumber = goodsManagementMapper.countGoodsNumber();
        LayRequest layRequest = assembleLayRequest(goodsManagements, goodsNumber);
        return layRequest;
    }

    @Override
    public void addGoods(GoodsManagement goodsManagement) {

        GoodsManagement goodsManagementById = goodsManagementMapper.getGoodsManagementById(goodsManagement.getGoodsId());
        if (goodsManagementById == null) {
            //库存中不存在该商品需要新增
            goodsManagement.setLastOperatorId(1L);
            Integer statusResult = GetResultUtil.getStatusResult(goodsManagement);
            if (statusResult <= 0) {
                throw new WarehouseException(SystemErrorCodeEnum.DATA_ERROR);
            }
            goodsManagement.setGoodsStatusId(statusResult);
            Integer insertResult = goodsManagementMapper.insertGoods(goodsManagement);
        } else {
           //库存中已经存在不能新增货物
            throw new WarehouseException(SystemErrorCodeEnum.GOODS_ALREADY_EXISTS);
        }

    }

    @Override
    public void delGoods(GoodsManagement goodsManagement) {
        Long goodsId = goodsManagement.getGoodsId();
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
    public LayRequest searchGoodsByGoods(GoodsManagement goodsManagement) {
        List<GoodsManagement> goodsManagements = goodsManagementMapper.selectGoodsByGoodsManagement(goodsManagement);
        Integer goodsNumber = goodsManagementMapper.countGoodsNumberByGoodsManagement(goodsManagement);
        LayRequest layRequest = assembleLayRequest(goodsManagements, goodsNumber);
        return layRequest;
    }

    /**
     * 组装LayRequest对象
     *
     * @param list
     * @return
     */
    private LayRequest assembleLayRequest(List list, Integer count) {
        LayRequest layRequest = new LayRequest();
        if (list.size() > 0) {
            layRequest.setCode(StatusEnum.CODE_SUCCESS.getCode());
            layRequest.setMsg("");
            layRequest.setCount(count.toString());
            layRequest.setData(list);
        }
        return layRequest;
    }
}
