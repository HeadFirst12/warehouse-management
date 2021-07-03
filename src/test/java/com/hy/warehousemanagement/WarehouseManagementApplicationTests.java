package com.hy.warehousemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

@SpringBootTest
class WarehouseManagementApplicationTests {

    @Test
    public void test() {
        String md5Str = DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println(md5Str);
    }

}
