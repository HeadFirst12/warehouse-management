package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonnelManagementMapper {

    List<PersonnelManagement> queryPersonnelManagementList();

    String queryPersonnelManagementNameById(Long id);
}
