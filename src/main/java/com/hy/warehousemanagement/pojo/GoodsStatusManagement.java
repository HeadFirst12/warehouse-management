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
public class GoodsStatusManagement implements Serializable {

    private static final long serialVersionUID = -125058272378327598L;

    //货物状态主键
    private Integer goods_status_id;
    //货物状态
    private String goods_status;
    //货物状态描述
    private String goods_status_desc;
}