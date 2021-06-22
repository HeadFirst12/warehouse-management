package com.hy.warehousemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author hy
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LayRequest {

    private Integer page;

    private Integer limit;
}
