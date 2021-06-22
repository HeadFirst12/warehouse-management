package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.model.Page;
import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hy
 */
@Mapper
@Repository
public interface EntryWarehouseManagementMapper {

    /**
     * 查询入库日志列表
     * @return
     * @param page
     */
    List<EntryWarehouseManagement> queryEntryWarehouseList(Page page);

    /**
     * 统计入库日志数量
     * @return
     */
    Integer countEntryWarehouseNumber();

    /**
     * 添加入库日志
     * @param entryWarehouseManagement
     */
    Integer addEntryWarehouse(EntryWarehouseManagement entryWarehouseManagement);

    /**
     * 多条件查询entryWarehouseManagement
     * @param entryWarehouseManagement
     * @param page
     * @return
     */
    List<EntryWarehouseManagement> selectEntryGoodsByEntryGoods(@Param("entryWarehouse") EntryWarehouseManagement entryWarehouseManagement,@Param("page")  Page page);
}
