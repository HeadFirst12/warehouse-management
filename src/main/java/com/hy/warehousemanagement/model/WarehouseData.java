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
    private String overCeilingRate;

    private Integer nearCeilingNumber;
    private String nearCeilingRate;

    private Integer normalNumber;
    private String normalRate;

    private Integer nearFloorNumber;
    private String nearFloorRate;

    private Integer overFloorNumber;
    private String overFloorRate;

    private Integer countNumber;

}
