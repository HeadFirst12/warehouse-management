package com.hy.warehousemanagement.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.mapper.*;
import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.pojo.*;
import com.hy.warehousemanagement.utils.GetResultUtil;
import com.hy.warehousemanagement.utils.TimesUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hy
 */
public class BaseWarehouseFilter {

    @Resource
    protected EntryWarehouseManagementMapper entryWarehouseManagementMapper;

    @Resource
    protected OutWarehouseManagementMapper outWarehouseManagementMapper;

    @Resource
    protected GoodsManagementMapper goodsManagementMapper;

    @Resource
    protected PersonnelManagementMapper personnelManagementMapper;

    @Resource
    protected GoodsStatusManagementMapper goodsStatusManagementMapper;

    @Resource
    protected AuthenticationFilter authenticationFilter;

    /**
     * 映射关系 公共方法提取
     */
    protected List<JSONObject> mapperGoodsManagement(List<GoodsManagement> goodsManagements) {
        List<JSONObject> goodsManagementJsonArray = new ArrayList<>();
        if (!goodsManagements.isEmpty()) {
            //先将整个对象转化和赋值过去
            goodsManagementJsonArray = JSON.parseArray(JSON.toJSONString(goodsManagements), JSONObject.class);

            for (JSONObject goodsManagementJson : goodsManagementJsonArray) {
                //时间格式转化
                mapperDate(goodsManagementJson,Constant.CREATE_TIME,TimesUtil.NORMAL_DATE_24_HOUR_FORMAT);
                mapperDate(goodsManagementJson,Constant.UPDATE_TIME,TimesUtil.NORMAL_DATE_24_HOUR_FORMAT);

                //操作员映射
                mapperOperator(goodsManagementJson,Constant.LAST_OPERATOR_ID);

                //库存状态映射
                mapperGoodsStatus(goodsManagementJson,Constant.GOODS_STATUS_ID);
            }
        }
        return goodsManagementJsonArray;
    }

    protected List<JSONObject> mapperEntryGoods (List<EntryWarehouseManagement> entryWarehouseManagements) {
        List<JSONObject> entryWarehouseManagementJsonArray = null;
        if(!entryWarehouseManagements.isEmpty()) {
            //先将整个对象转化和赋值过去
            entryWarehouseManagementJsonArray = JSON.parseArray(JSON.toJSONString(entryWarehouseManagements), JSONObject.class);

            for (JSONObject entryWarehouseManagementJson : entryWarehouseManagementJsonArray) {
                //时间格式转化
                mapperDate(entryWarehouseManagementJson,Constant.CREATE_TIME,TimesUtil.NORMAL_DATE_24_HOUR_FORMAT);
                mapperDate(entryWarehouseManagementJson,Constant.ENTRY_GOODS_TIME,TimesUtil.NORMAL_DATE_24_HOUR_FORMAT);

                //操作员映射
                mapperOperator(entryWarehouseManagementJson,Constant.OPERATOR_ID);
            }

        }
        return entryWarehouseManagementJsonArray;
    }

    protected List<JSONObject> mapperOutGoods (List<OutWarehouseManagement> outWarehouseManagements) {
        List<JSONObject> outWarehouseManagementJsonArray = null;
        if(!outWarehouseManagements.isEmpty()) {
            //先将整个对象转化和赋值过去
            outWarehouseManagementJsonArray = JSON.parseArray(JSON.toJSONString(outWarehouseManagements), JSONObject.class);

            for (JSONObject outWarehouseManagementJson : outWarehouseManagementJsonArray) {
                //时间格式转化
                mapperDate(outWarehouseManagementJson,Constant.CREATE_TIME,TimesUtil.NORMAL_DATE_24_HOUR_FORMAT);
                mapperDate(outWarehouseManagementJson,Constant.OUT_GOODS_TIME,TimesUtil.NORMAL_DATE_24_HOUR_FORMAT);

                //操作员映射
                mapperOperator(outWarehouseManagementJson,Constant.OPERATOR_ID);
            }

        }
        return outWarehouseManagementJsonArray;
    }

    /**
     * 操作员映射
     */
    private void mapperOperator(JSONObject sources,String key) {
        Long operatorId = sources.getLong(key);
        List<PersonnelManagement> personnelManagements = personnelManagementMapper.queryPersonnelManagementList();
        for (PersonnelManagement personnelManagement : personnelManagements) {
            if (personnelManagement.getId().equals(operatorId)) {
                sources.put(key, personnelManagement.getName());
                break;
            }
        }
    }

    /**
     * 时间格式映射
     */
    private void mapperDate(JSONObject sources,String key,String format) {
        //时间格式转化
        String date = TimesUtil.dateToStringFormat(sources.getDate(key),format);
        sources.put(key, date);
    }

    /**
     * 库存状态映射
     */
    private void mapperGoodsStatus(JSONObject sources,String key) {
        Integer goodsStatusId = sources.getInteger(Constant.GOODS_STATUS_ID);
        List<GoodsStatusManagement> goodsStatusManagements = goodsStatusManagementMapper.queryGoodsStatusList();
        for (GoodsStatusManagement goodsStatusManagement : goodsStatusManagements) {
            if(goodsStatusManagement.getGoodsStatusId().equals(goodsStatusId)) {
                sources.put(key,goodsStatusManagement.getGoodsStatusDesc());
                break;
            }
        }
    }

    /**
     * 出库或者入库需要更新库存的数量
     */
    protected Integer updateGoodsNumber(GoodsManagement oldGoodsManagement,Long newGoodsNumber) {
        GoodsManagement newGoodsManagement = new GoodsManagement();
        newGoodsManagement.setGoodsNumber(newGoodsNumber);
        newGoodsManagement.setQuantityCeiling(oldGoodsManagement.getQuantityCeiling());
        newGoodsManagement.setQuantityFloor(oldGoodsManagement.getQuantityFloor());
        newGoodsManagement.setGoodsStatusId(GetResultUtil.getStatusResult(newGoodsManagement));
        newGoodsManagement.setLastOperatorId(authenticationFilter.getNowUserId());
        newGoodsManagement.setGoodsId(oldGoodsManagement.getGoodsId());
        newGoodsManagement.setUpdateTime(new Date());
        return goodsManagementMapper.updateGoodsManagement(newGoodsManagement);
    }
}
