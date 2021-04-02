package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.PermissionManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionManagementsMapper {
    /**
     * 查询员工级别列表
     * @return
     */
    List<PermissionManagement> queryPermissionManagementList();

    /**
     * 统计员工级别总数
     * @return
     */
    Integer countPermissionManagementNumber();
}
