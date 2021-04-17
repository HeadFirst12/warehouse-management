package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.model.Page;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsManagementMapper {

    /**
     * 查询库存商品列表
     * @return
     * @param page
     */
    List<GoodsManagement> queryGoodsList(Page page);

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
    GoodsManagement getGoodsManagementById(String goodsId);

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
    Integer delGoodsManagementById(String goodsId);

    /**
     * 编辑货物
     * @param goodsManagement
     * @return
     */
    Integer updateGoodsManagement(GoodsManagement goodsManagement);

    /**
     * 多条件查询货物库存
     * @param goodsManagement
     * @param page
     * @return
     */
    List<GoodsManagement> selectGoodsByGoodsManagement(@Param("goodsManagement")GoodsManagement goodsManagement, @Param("page") Page page);

    /**
     * 统计多条件查询出来的库存
     * @param goodsManagement
     * @return
     */
    Integer countGoodsNumberByGoodsManagement(GoodsManagement goodsManagement);

    /**
     * 通过goodsName查找库存
     * @param goodsName
     * @return
     */
    GoodsManagement getGoodsManagementByGoodsName(String goodsName);

    /**
     * 通过库存状态获取库存数量
     * @return
     */
    Integer countGoodsNumberByGoodsStatusId(Integer goodsStatusId);

    /**
     * 取出库存中状态非正常的库存列表
     * @param page
     * @return
     */
    List<GoodsManagement> selectGoodsListByStatusAbnormal(Page page);

    /**
     * 取出库存中状态非正常数量
     * @return
     */
    Integer countGoodsNumberByStatusAbnormal();
}
