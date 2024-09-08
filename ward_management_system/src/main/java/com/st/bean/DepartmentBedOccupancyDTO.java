package com.st.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentBedOccupancyDTO {
    private String departmentId;
    private double occupancyRate;
}
