package com.hy.warehousemanagement.controller;

import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.service.PersonnelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class PersonnelController {

    @Resource
    private PersonnelService personnelService;

    @RequestMapping("/user/administrators/list/get-personnel-list")
    @ResponseBody
    public LayResult getPersonnelList() {
        LayResult layResult = personnelService.getPersonnelList();
        return layResult;
    }

    @RequestMapping("/user/administrators/list/get-permission-list")
    @ResponseBody
    public LayResult getPermissionList() {
        LayResult layResult = personnelService.getPermissionList();
        return layResult;
    }

}
