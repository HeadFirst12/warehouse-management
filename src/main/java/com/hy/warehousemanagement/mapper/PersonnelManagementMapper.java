package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonnelManagementMapper {

    /**
     * 查询管理人员列表
     * @return
     */
    List<PersonnelManagement> queryPersonnelManagementList();

    String queryPersonnelManagementNameById(Long id);
}
