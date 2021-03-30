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
public class PersonnelManagement implements Serializable {

    private static final long serialVersionUID = -7903196207690734129L;

    private Long id;
    private String name;
    private Integer age;
    private String tel;
    private String level;

}
