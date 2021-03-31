package com.hy.warehousemanagement.service;

import com.hy.warehousemanagement.model.LayRequest;

public interface PersonnelService {
    /**
     * 获取管理人员列表
     * @return
     */
    LayRequest getPersonnelList();
}
