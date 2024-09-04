package com.st.service;

import com.st.bean.Nurse;

import java.util.List;

public interface NurseService {
    boolean insert(Nurse n);
    boolean delete(Long nid);
    boolean update(Nurse n);
    List<Nurse> selectAll();
    List<Nurse> selectByNname(String nname);
    Nurse selectByNid(Long nid);
    List<Nurse> selectByDid(Integer did);
    Nurse selectByNno(String nno);


}
