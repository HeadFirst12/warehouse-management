package com.hy.warehousemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseData {
    
    private Integer overCeilingNumber;

    private Integer nearCeilingNumber;

    private Integer normalNumber;

    private Integer nearFloorNumber;

    private Integer overFloorNumber;

    private Integer countNumber;

}
