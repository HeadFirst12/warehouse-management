package com.hy.warehousemanagement.service;

import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.pojo.PersonnelManagement;

/**
 * @author hy
 */
public interface PersonnelService {
    /**
     * 获取管理人员列表
     * @return
     */
    LayResult getPersonnelList();

    /**
     * 获取管理人员级别列表
     * @return
     */
    LayResult getPermissionList();

    /**
     * 通过personnelManagement检查账户密码
     * @param personnelManagement
     * @return
     */
    PersonnelManagement checkPersonnelByPersonnel(PersonnelManagement personnelManagement);
}
