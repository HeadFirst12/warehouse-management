package com.hy.warehousemanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hy
 */
@Component
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelManagement implements Serializable {

    private static final long serialVersionUID = -7903196207690734129L;

    /** 人员主键 */
    private Long id;
    /** 用户名 */
    private String userName;
    /** 密码 */
    private String userPassword;
    /** 人员级别,0超级管理员，1管理员，2普通员工 */
    private Integer permissionLevel;
    /** 人员姓名 */
    private String name;
    /** 人员年龄 */
    private Integer age;
    /** 人员电话 */
    private String tel;
    /** 性别 */
    private Boolean sex;
    /** 加入时间 */
    private Date joinTime;
    /** 是否授权 */
    private Boolean authorize;
}
