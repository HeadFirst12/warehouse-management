package com.hy.warehousemanagement.utils;

import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 组装结果返回工具类
 */
public class AssembleResultUtil {

    public static AjaxResult assembleAjaxResult(Integer modification) {
        AjaxResult ajaxResult;
        if (modification > 0) {
            ajaxResult = new AjaxResult();
            ajaxResult.setCode(StatusEnum.AJAX_CODE_SUCCESS.getCode());
            ajaxResult.setStatus(StatusEnum.AJAX_STATUS_SUCCESS.getCode());
        } else {
            ajaxResult = assembleAjaxResult(SystemErrorCodeEnum.DATABASE_ERROR);
        }
        return ajaxResult;
    }

    public static AjaxResult assembleAjaxResult(SystemErrorCodeEnum systemErrorCodeEnum) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(StatusEnum.AJAX_CODE_SUCCESS.getCode());
        ajaxResult.setStatus(StatusEnum.AJAX_STATUS_FAIL.getCode());
        ajaxResult.setErrCode(systemErrorCodeEnum.getErrorCode());
        ajaxResult.setErrDesc(systemErrorCodeEnum.getErrorDesc());
        return ajaxResult;
    }

    public static AjaxResult assembleAjaxResult(JSONObject jsonObject) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(StatusEnum.AJAX_CODE_SUCCESS.getCode());
        ajaxResult.setStatus(StatusEnum.AJAX_STATUS_FAIL.getCode());
        ajaxResult.setErrCode(jsonObject.getString(Constant.ERR_CODE));
        ajaxResult.setErrDesc(jsonObject.getString(Constant.ERR_DESC));
        return ajaxResult;
    }

    /**
     * 组装LayRequest对象
     *
     * @param list
     * @return
     */
    public static LayResult assembleLayResult(List list, Integer count) {
        LayResult layResult = new LayResult();
        if (list.size() > 0) {
            layResult.setCode(StatusEnum.CODE_SUCCESS.getCode());
            layResult.setMsg(StringUtils.EMPTY);
            layResult.setCount(count.toString());
            layResult.setData(list);
        }
        return layResult;
    }

    public static JSONObject getExceptionInfo(String message) {
        JSONObject result = new JSONObject();
        String[] split = StringUtils.split(message,Constant.Separator);
        if(split.length >= 2) {
            result.put(Constant.ERR_CODE,split[0]);
            result.put(Constant.ERR_DESC,split[1]);
        }else {
            result.put(Constant.ERR_CODE,SystemErrorCodeEnum.SYSTEM_ERROR.getErrorCode());
            result.put(Constant.ERR_DESC,SystemErrorCodeEnum.SYSTEM_ERROR.getErrorDesc());

        }
        return result;
    }
}
