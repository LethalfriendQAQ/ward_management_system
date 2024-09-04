package com.st.mapper;

import com.st.bean.Nurse;

import java.util.List;

public interface NurseMapper {
    //添加
    int insert (Nurse n);
    //根据ID删除
    int delete(Long nid);
    //修改
    int update(Nurse n);
    //查询所有护士
    List<Nurse> selectAll();
    //根据姓名查询
    List<Nurse> selectByNname(String nname);
    //根据Id查询
    Nurse selectByNid(Long nid);

    List<Nurse> selectByDid(Integer did);
    //Nurse selectByPno(String pno);
    Nurse selectByNno(String nno);
}
