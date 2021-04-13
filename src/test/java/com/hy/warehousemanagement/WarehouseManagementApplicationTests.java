package com.hy.warehousemanagement;

import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class WarehouseManagementApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonnelManagementMapper personnelManagementMapper;

    @Autowired
    private PersonnelManagement personnelManagement;


}
