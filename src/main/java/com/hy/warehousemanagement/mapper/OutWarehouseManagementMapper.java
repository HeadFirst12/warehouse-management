package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.model.Page;
import com.hy.warehousemanagement.pojo.OutWarehouseManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OutWarehouseManagementMapper {

    /**
     * 获取出库日志列表
     * @param page
     * @return
     */
    List<OutWarehouseManagement> queryOutWarehouseList(Page page);

    /**
     * 获取出库日志总数
     * @return
     */
    Integer countOutWarehouseNumber();

    /**
     * 添加出库日志
     * @param outWarehouseManagement
     * @return
     */
    Integer addOutWarehouse(OutWarehouseManagement outWarehouseManagement);

    /**
     * 多条件搜索出库日志
     * @param outWarehouseManagement
     * @param page
     * @return
     */
    List<OutWarehouseManagement> selectOutGoodsByOutGoods(@Param("outWarehouse")OutWarehouseManagement outWarehouseManagement,@Param("page") Page page);
}
