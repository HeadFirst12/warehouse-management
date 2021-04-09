package com.hy.warehousemanagement.service;

import com.hy.warehousemanagement.model.LayResult;

public interface PersonnelService {
    /**
     * 获取管理人员列表
     * @return
     */
    LayResult getPersonnelList();

    /**
     * 获取管理人员级别列表
     */
    LayResult getPermissionList();
}
