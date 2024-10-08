package com.st.mapper;

import com.st.bean.Duty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DutyMapper {
    int insert(Duty duty);
    int delete(Long dutyId);
    int update(Duty duty);
    Duty selectByDutyId(Long dutyId);
    //查询所有的值班
    List<Duty> selectAll();
    List<Duty> selectNurseByDutyId();
    //获取某个值班和所有的护士的nid
    List<Long> selectNidByDutyId(Integer dutyId);
    //删除某个值班和所有护士的管理关系
    void deleteDutyIdAndNidByDutyId(Integer dutyId);
    //添加某个值班和所有护士的管理关系
    void insertDutyIdAndNid(@Param("dutyId") Integer dutyId, @Param("nids") Long[] nids);


}
