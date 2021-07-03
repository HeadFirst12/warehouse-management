package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.PermissionManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hy
 */
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

    /**
     * 通过权限Id查找权限对象
     * @param permissionId 权限Id
     * @return 权限对象
     */
    PermissionManagement queryPermissionManagementByPermissionId(Integer permissionId);
}
