package com.hy.warehousemanagement;

import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class WarehouseManagementApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonnelManagementMapper personnelManagementMapper;

    @Autowired
    private PersonnelManagement personnelManagement;

    @Test
    void contextLoads() throws SQLException {
        //查看一下数据源
        System.out.println(dataSource.getClass());

        //获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        List<PersonnelManagement> personnelManagements = personnelManagementMapper.queryPersonnelManagementList();
        System.out.println(personnelManagements.toString());

        String s = personnelManagementMapper.queryPersonnelManagementNameById(1L);
        System.out.println(s);

    }

}
