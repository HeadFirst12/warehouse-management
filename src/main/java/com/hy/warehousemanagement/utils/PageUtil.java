package com.hy.warehousemanagement.utils;

import com.hy.warehousemanagement.model.LayRequest;
import com.hy.warehousemanagement.model.Page;

public class PageUtil {

    /**
     * 获取开始索引位置
     * @param layRequest
     * @return
     */
    public static Page getPage(LayRequest layRequest) {
        Page page = new Page();
        page.setBeginIndex((layRequest.getPage() -1) * layRequest.getLimit());
        page.setShowNumber(layRequest.getLimit());
        return page;
    }
}
