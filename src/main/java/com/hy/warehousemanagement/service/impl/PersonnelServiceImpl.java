package com.hy.warehousemanagement.service.impl;

import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import com.hy.warehousemanagement.service.PersonnelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Resource
    private PersonnelManagementMapper personnelManagementMapper;

    @Override
    public LayRequest getPersonnelList() {
        List<PersonnelManagement> personnelManagements = null;
        try {
            personnelManagements = personnelManagementMapper.queryPersonnelManagementList();
        }catch (Exception e) {
           e.printStackTrace();
        }
        LayRequest layRequest = new LayRequest();
        if(personnelManagements.size() > 0) {
            layRequest.setCode("0");
            layRequest.setMsg("");
            layRequest.setCount("1");
            layRequest.setData(personnelManagements);
        }
        return layRequest;
    }
}
