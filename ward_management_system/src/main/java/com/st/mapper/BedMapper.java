package com.st.mapper;

import com.st.bean.Bed;

import java.util.List;

public interface BedMapper {
    //添加
    int insert (Bed b);
    //根据ID删除
    int delete(Long bid);
    int deleteByWnumber(String wnumber);
    //修改
    int update(Bed b);
    //查询所有护士
    List<Bed> selectAll();
    //根据病房号查询
    Bed selectByBnumber(String bnumber);
    Bed selectByWnumber(String wnumber);
    Bed selectByBid(Long bid);
}
