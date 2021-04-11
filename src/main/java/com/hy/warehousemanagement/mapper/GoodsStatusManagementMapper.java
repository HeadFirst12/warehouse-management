package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.GoodsStatusManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsStatusManagementMapper {

    /**
     * 查询出库存状态列表
     * @return
     */
    List<GoodsStatusManagement> queryGoodsStatusList();
}
