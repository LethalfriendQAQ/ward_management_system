package com.st.service;

import com.st.bean.Duty;

import java.util.List;

public interface DutyService {
    boolean insert(Duty duty);
    boolean delete(Long dutyId);
    boolean update(Duty duty);
    Duty selectByDutyId(Long dutyId);
    //查询所有的值班
    List<Duty> selectAll();
    //获取某个值班和所有的护士的nid
    List<Long> selectNidByDutyId(Integer dutyId);
    //添加某个值班和所有护士的管理关系
    void insertDutyIdAndNid(Integer dutyId, Long[] nids);

    List<Duty> selectByDutyDate(String dutyDate);
}
