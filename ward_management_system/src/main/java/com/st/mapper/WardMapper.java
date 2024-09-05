package com.st.mapper;

import com.st.bean.Ward;

import java.util.List;

public interface WardMapper {
    //添加
    int insert (Ward w);
    //根据ID删除
    int delete(Long wid);
    //修改
    int update(Ward w);
    //查询所有护士
    List<Ward> selectAll();
    List<Ward> selectByDid(Integer did);
    //根据病房号查询
    Ward selectByWnumber(String wnumber);
    Ward selectByWid(Long wid);
}
