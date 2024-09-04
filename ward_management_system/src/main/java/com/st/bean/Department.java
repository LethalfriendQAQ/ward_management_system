package com.st.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    /**
     * 主键
     */
    private Integer did;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 部门位置
     */
    private String dlocation;
}