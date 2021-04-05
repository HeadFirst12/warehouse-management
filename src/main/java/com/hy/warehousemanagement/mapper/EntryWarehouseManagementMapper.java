package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.EntryWarehouseManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EntryWarehouseManagementMapper {

    /**
     * 查询入库日志列表
     * @return
     */
    List<EntryWarehouseManagement> queryEntryWarehouseList();

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
}
