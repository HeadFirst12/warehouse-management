package com.hy.warehousemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * layUI的请求对象
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LayRequest<T> {

    private String code;
    private String msg;
    private String count;
    private List<T> data;
}
