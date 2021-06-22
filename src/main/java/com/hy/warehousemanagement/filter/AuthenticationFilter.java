package com.hy.warehousemanagement.filter;

import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hy
 */
@Component
public class AuthenticationFilter {

    @Resource
    private PersonnelManagementMapper personnelManagementMapper;

    public Long getNowUserId() {
        Long id;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String userName = authentication.getName();
            PersonnelManagement personnelManagement = new PersonnelManagement();
            personnelManagement.setUserName(userName);
            PersonnelManagement personnelManagementByPersonnel = personnelManagementMapper.getPersonnelManagementByPersonnel(personnelManagement);
            id = personnelManagementByPersonnel.getId();
        } else {
            id = 1L;
        }
        return id;
    }
}
