package com.st.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Patient {
    //主键

    private Long pid;
    //患者编号

    private String pno;
    //头像

    private String pavatar;
    //姓名

    private String pname;
    //年龄

    private Integer page;
    //性别

    private String pgender;
    //入院时间

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date padmissiondate;
    //出院时间

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pleavedate;
    //住院状态

    private Integer pstatus;
    //电话

    private String ptelephone;
    //科室编号

    private Integer did;
    //负责护士编号

    private String nno;
    //病房编号

    private String wnumber;
    //病床编号

    private String bnumber;
    //所在科室信息
    //private Department department;
}
