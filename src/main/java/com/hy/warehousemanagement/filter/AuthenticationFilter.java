package com.hy.warehousemanagement.filter;

import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthenticationFilter {

    @Resource
    private PersonnelManagementMapper personnelManagementMapper;

    public Long getNowUserId() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        PersonnelManagement personnelManagement = new PersonnelManagement();
        personnelManagement.setUserName(userName);
        PersonnelManagement personnelManagementByPersonnel = personnelManagementMapper.getPersonnelManagementByPersonnel(personnelManagement);
        Long id = personnelManagementByPersonnel.getId();
        return id;
    }
}
