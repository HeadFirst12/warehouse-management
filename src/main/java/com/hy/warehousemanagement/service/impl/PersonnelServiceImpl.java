package com.hy.warehousemanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.mapper.PermissionManagementsMapper;
import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.model.StatusEnum;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import com.hy.warehousemanagement.pojo.PermissionManagement;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import com.hy.warehousemanagement.service.PersonnelService;
import com.hy.warehousemanagement.utils.TimesUtil;
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
        List<JSONObject> personnelManagementJSONArray = null;
        if (personnelManagements.size() > 0) {
            //先将整个对象转化和赋值过去
            personnelManagementJSONArray = JSONArray.parseArray(JSONArray.toJSONString(personnelManagements), JSONObject.class);

            for (JSONObject personnelManagementJSON : personnelManagementJSONArray) {

                //时间格式转化
                String jonsTime = TimesUtil.DateToStringFormat(personnelManagementJSON.getDate(Constant.JOIN_TIME), TimesUtil.SHORT_DATE_FORMAT);
                personnelManagementJSON.put(Constant.JOIN_TIME, jonsTime);

                //人员级别映射
                Long permissionLevel = personnelManagementJSON.getLong(Constant.PERMISSION_LEVEL);
                for (PermissionManagement permissionManagement : permissionManagements) {
                    if (permissionLevel == permissionManagement.getPermissionId()) {
                        personnelManagementJSON.put(Constant.PERMISSION_LEVEL, permissionManagement.getPermissionName());
                    }
                }

                //员工性别映射
                if (personnelManagementJSON.getBoolean(Constant.SEX)) {
                    personnelManagementJSON.put(Constant.SEX, Constant.SEX_MAN);
                } else {
                    personnelManagementJSON.put(Constant.SEX, Constant.SEX_WOMAN);
                }

            }
        }
        LayRequest layRequest = assembleLayRequest(personnelManagementJSONArray, personnelManagementNumber);
        return layRequest;
    }

    @Override
    public LayRequest getPermissionList() {
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
        LayRequest layRequest = assembleLayRequest(permissionManagements, personnelManagementNumber);
        return layRequest;
    }

    /**
     * 组装LayRequest对象
     *
     * @param list
     * @return
     */
    private LayRequest assembleLayRequest(List list, Integer count) {
        LayRequest layRequest = new LayRequest();
        if (list.size() > 0) {
            layRequest.setCode(StatusEnum.CODE_SUCCESS.getCode());
            layRequest.setMsg("");
            layRequest.setCount(count.toString());
            layRequest.setData(list);
        }
        return layRequest;
    }
}
