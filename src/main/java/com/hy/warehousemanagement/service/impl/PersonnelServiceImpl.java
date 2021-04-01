package com.hy.warehousemanagement.service.impl;

import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.mapper.PermissionManagementsMapper;
import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.model.StatusEnum;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import com.hy.warehousemanagement.pojo.PermissionManagement;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import com.hy.warehousemanagement.service.PersonnelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 人员页面服务层
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Resource
    private PersonnelManagementMapper personnelManagementMapper;

    @Resource
    private PermissionManagementsMapper permissionManagementsMapper;

    @Override
    public LayRequest getPersonnelList() {
        //人员列表
        List<PersonnelManagement> personnelManagements;
        //人员总数
        Integer personnelManagementNumber;
        try {
            personnelManagements = personnelManagementMapper.queryPersonnelManagementList();
            personnelManagementNumber = personnelManagementMapper.countPersonnelManagement();
        }catch (Exception e) {
           throw new WarehouseException(SystemErrorCodeEnum.DATABASE_ERROR);
        }
        LayRequest layRequest = assembleLayRequest(personnelManagements,personnelManagementNumber);
        return layRequest;
    }

    @Override
    public LayRequest getPermissionList() {
        //级别列表
        List<PermissionManagement> permissionManagements;
        //级别总数
        Integer personnelManagementNumber;
        try {
            permissionManagements = permissionManagementsMapper.getPermissionManagementList();
            personnelManagementNumber = permissionManagementsMapper.countPermissionManagementNumber();
        }catch (Exception e) {
            throw new WarehouseException(SystemErrorCodeEnum.DATABASE_ERROR);
        }
        LayRequest layRequest = assembleLayRequest(permissionManagements,personnelManagementNumber);
        return layRequest;
    }

    /**
     * 组装LayRequest对象
     * @param list
     * @return
     */
    private LayRequest assembleLayRequest(List list,Integer count) {
        LayRequest layRequest =new LayRequest();
        if(list.size() > 0) {
            layRequest.setCode(StatusEnum.CODE_SUCCESS.getCode());
            layRequest.setMsg("");
            layRequest.setCount(count.toString());
            layRequest.setData(list);
        }
        return layRequest;
    }
}
