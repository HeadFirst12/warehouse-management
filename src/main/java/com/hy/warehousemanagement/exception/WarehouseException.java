package com.hy.warehousemanagement.exception;

import com.hy.warehousemanagement.model.Constant;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;

/**
 * 系统异常类
 * @author hy
 */
public class WarehouseException extends RuntimeException{

    public WarehouseException(SystemErrorCodeEnum systemErrorCodeEnum) {
        super(systemErrorCodeEnum.getErrorCode()+ Constant.SEPARATOR + systemErrorCodeEnum.getErrorDesc());
    }
}
