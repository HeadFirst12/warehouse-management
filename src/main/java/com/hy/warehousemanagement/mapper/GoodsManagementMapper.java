package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.GoodsManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsManagementMapper {

    /**
     * 查询库存商品列表
     * @return
     */
    List<GoodsManagement> queryGoodsList();

    /**
     * 统计库存商品总数
     * @return
     */
    Integer countGoodsNumber();

    /**
     * 通过goodsId获取到库存对象
     * @param goodsId
     * @return
     */
    GoodsManagement getGoodsManagementById(Long goodsId);

    /**
     * 插入货物
     * @param goodsManagement
     * @return
     */
    Integer insertGoods(GoodsManagement goodsManagement);

    /**
     * 删除货物
     * @param goodsId
     * @return
     */
    Integer delGoodsManagementById(Long goodsId);

    /**
     * 编辑货物
     * @param goodsManagement
     * @return
     */
    Integer updateGoodsManagement(GoodsManagement goodsManagement);
}
