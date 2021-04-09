package com.hy.warehousemanagement;

import com.hy.warehousemanagement.exception.WarehouseException;
import com.hy.warehousemanagement.model.SystemErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MyTest {

    @Test
    public void test1() {
        if(true) {
            throw new WarehouseException(SystemErrorCodeEnum.DATABASE_ERROR);
        }else {
            System.out.println(SystemErrorCodeEnum.DATABASE_ERROR);
        }
    }

    @Test
    public void test2() {

    }
}
