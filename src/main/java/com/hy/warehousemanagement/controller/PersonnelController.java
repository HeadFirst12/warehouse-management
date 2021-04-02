package com.hy.warehousemanagement.controller;

import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.service.PersonnelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PersonnelController {

    @Resource
    private PersonnelService personnelService;

    @RequestMapping("/user/administrators/list/get-personnel-list")
    @ResponseBody
    public LayRequest getPersonnelList() {
        LayRequest layRequest = personnelService.getPersonnelList();
        return layRequest;
    }

    @RequestMapping("/user/administrators/list/get-permission-list")
    @ResponseBody
    public LayRequest getPermissionList() {
        LayRequest layRequest = personnelService.getPermissionList();
        return layRequest;
    }

}
