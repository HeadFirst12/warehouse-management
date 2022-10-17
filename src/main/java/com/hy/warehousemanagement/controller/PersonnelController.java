package com.hy.warehousemanagement.controller;

import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.service.PersonnelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author hy
 */
@Controller
public class PersonnelController {

    @Resource
    private PersonnelService personnelService;

    @RequestMapping("/user/administrators/list/get-personnel-list")
    @ResponseBody
    public LayResult getPersonnelList() {
        return personnelService.getPersonnelList();
    }

    @RequestMapping("/user/administrators/list/get-permission-list")
    @ResponseBody
    public LayResult getPermissionList() {
        return personnelService.getPermissionList();
    }
}
