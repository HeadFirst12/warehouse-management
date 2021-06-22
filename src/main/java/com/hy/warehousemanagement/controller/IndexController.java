package com.hy.warehousemanagement.controller;

import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.model.LayResult;
import com.hy.warehousemanagement.model.WarehouseData;
import com.hy.warehousemanagement.service.WareHouseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author hy
 */
@Controller
public class IndexController {

    @Resource
    private WareHouseService wareHouseService;

    /**
     * 跳转道祖也控制页面并且回显数据
     */
    @GetMapping("/home/console")
    public ModelAndView gotoEditGoodsFormView() {
        WarehouseData warehouseData = wareHouseService.selectWareHouseData();
        ModelAndView mad = new ModelAndView("/views/home/console");
        mad.addObject(Constant.WAREHOUSE_DATA,warehouseData);
        return mad;
    }

    /**
     * 非正常库存通知
     */
    @RequestMapping("/home/message")
    @ResponseBody
    public LayResult getGoodsMessage(LayRequest layRequest) {
        return wareHouseService.getGoodsStatusAbnormal(layRequest);
    }
}
