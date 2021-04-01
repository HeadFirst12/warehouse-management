package com.hy.warehousemanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PermissionManagement implements Serializable {

    private static final long serialVersionUID = 5071169590998718606L;

    //级别主键
    private Long permissionId;
    //级别名称
    private String permissionName;
    //具体权利描述
    private String permissionDescription;
    //职责
    private String responsibility;
}