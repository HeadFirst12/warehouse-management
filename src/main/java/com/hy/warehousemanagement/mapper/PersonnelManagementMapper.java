package com.hy.warehousemanagement.mapper;

import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hy
 */
@Mapper
@Repository
public interface PersonnelManagementMapper {

    /**
     * 查询管理人员列表
     * @return
     */
    List<PersonnelManagement> queryPersonnelManagementList();

    /**
     * 查询管理人员信息通过Id
     * @param id
     * @return
     */
    String queryPersonnelManagementNameById(Long id);

    /**
     * 统计人员总数
     * @return
     */
    Integer countPersonnelManagement();

    /**
     * 通过Id获取人员信息
     * @param id
     * @return
     */
    PersonnelManagement getPersonnelManagementById(Long id);

    /**
     * 通过personnelManagement中有些信息获取人员信息
     * @param personnelManagement
     * @return
     */
    PersonnelManagement getPersonnelManagementByPersonnel(PersonnelManagement personnelManagement);

    /**
     * 通过userName查找用户对象
     * @param userName 用户姓名
     * @return 用户对象
     */
    PersonnelManagement getPersonnelManagementByUserName(String userName);
}
