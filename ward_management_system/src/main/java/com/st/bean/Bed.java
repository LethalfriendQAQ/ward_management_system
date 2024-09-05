package com.st.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bed {
    //主键
    private Long bid;
    //病床号
    private String bnumber;
    //患者编号
    private String pno;
    //负责护士编号
    private String nno;
    //病房号
    private String wnumber;
}
