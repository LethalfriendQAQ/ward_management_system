package com.st.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class duty {
    //值班id
    private Long dutyId;
    //值班日期
    private String dutyDate;
    //上班时间
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date dutyWorkTime;
    //下班时间
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date dutyClosingTime;
    //任务进度
    private Integer dutyTaskProgress;
    //任务详情
    private String dutyDetails;
}
