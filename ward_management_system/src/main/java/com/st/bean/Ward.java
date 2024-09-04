package com.st.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
    //主键
    private Long wid;
    //病房号
    private String wnumber;
    //科室
    private Integer did;
}
