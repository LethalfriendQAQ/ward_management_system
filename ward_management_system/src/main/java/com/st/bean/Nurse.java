package com.st.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nurse {
    //主键
    private Long nid;
    //编号
    private String nno;
    //姓名
    private String nname;
    //科室
    private Integer did;
    //负责患者
    //private String pno;
    private Department department;
    private String dname;

}
