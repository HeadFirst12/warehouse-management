package com.hy.warehousemanagement.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.mapper.PermissionManagementsMapper;
import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import com.hy.warehousemanagement.pojo.PermissionManagement;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import com.hy.warehousemanagement.service.PersonnelService;
import com.hy.warehousemanagement.utils.AssembleResultUtil;
import com.hy.warehousemanagement.utils.TimesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员页面服务层
 * @author hy
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Resource
    private PersonnelManagementMapper personnelManagementMapper;

    @Resource
    private PermissionManagementsMapper permissionManagementsMapper;

    @Override
    public LayResult getPersonnelList() {
        //人员列表
        List<PersonnelManagement> personnelManagements;
        //人员总数
        Integer personnelManagementNumber;
        //取出人员等级的集合
        List<PermissionManagement> permissionManagements;
        try {
            personnelManagements = personnelManagementMapper.queryPersonnelManagementList();
            personnelManagementNumber = personnelManagementMapper.countPersonnelManagement();
            permissionManagements = permissionManagementsMapper.queryPermissionManagementList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WarehouseException(SystemErrorCodeEnum.DATABASE_ERROR);
        }

        //定义好json对象集合 接收特殊处理之后的json集合
        List<JSONObject> personnelManagementJsonArray = new ArrayList<>();
        if (!personnelManagements.isEmpty()) {
            //先将整个对象转化和赋值过去
            personnelManagementJsonArray = JSON.parseArray(JSON.toJSONString(personnelManagements), JSONObject.class);

            for (JSONObject personnelManagementJson : personnelManagementJsonArray) {

                //时间格式转化
                String jonsTime = TimesUtil.dateToStringFormat(personnelManagementJson.getDate(Constant.JOIN_TIME), TimesUtil.SHORT_DATE_FORMAT);
                personnelManagementJson.put(Constant.JOIN_TIME, jonsTime);

                //人员级别映射
                Long permissionLevel = personnelManagementJson.getLong(Constant.PERMISSION_LEVEL);
                for (PermissionManagement permissionManagement : permissionManagements) {
                    if (permissionLevel.equals(permissionManagement.getPermissionId())) {
                        personnelManagementJson.put(Constant.PERMISSION_LEVEL, permissionManagement.getPermissionName());
                    }
                }

                //员工性别映射
                if (Boolean.TRUE.equals(personnelManagementJson.getBoolean(Constant.SEX))) {
                    personnelManagementJson.put(Constant.SEX, Constant.SEX_MAN);
                } else {
                    personnelManagementJson.put(Constant.SEX, Constant.SEX_WOMAN);
                }

            }
        }
        return AssembleResultUtil.assembleLayResult(personnelManagementJsonArray, personnelManagementNumber);
    }

    @Override
    public LayResult getPermissionList() {
        //级别列表
        List<PermissionManagement> permissionManagements;
        //级别总数
        Integer personnelManagementNumber;
        try {
            permissionManagements = permissionManagementsMapper.queryPermissionManagementList();
            personnelManagementNumber = permissionManagementsMapper.countPermissionManagementNumber();
        } catch (Exception e) {
            throw new WarehouseException(SystemErrorCodeEnum.DATABASE_ERROR);
        }
        return AssembleResultUtil.assembleLayResult(permissionManagements, personnelManagementNumber);
    }

    @Override
    public PersonnelManagement checkPersonnelByPersonnel(PersonnelManagement personnelManagement) {
        return personnelManagementMapper.getPersonnelManagementByPersonnel(personnelManagement);
    }
}
