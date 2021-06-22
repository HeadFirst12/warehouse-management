package com.hy.warehousemanagement.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author hy
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult {

    /** 请求通讯状态 */
    private String code;
    /** 请求业务状态 */
    private String status;
    /** 错误码 */
    private String errorCode;
    /** 错误信息 */
    private String errorDesc;
    /** 返回数据 */
    private JSONObject data;
    /** 备注 */
    private String remarks;
}
