package com.st.mapper;

import com.st.bean.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PatientMapper {
    //添加
    int insert(Patient p);
    //根据ID删除
    int delete(Long pid);
    //修改患者住院状态
    int setPstatus(@Param("pid") Long pid, @Param("pstatus") Integer pstatus);
    //修改
    int update(Patient p);

    int updateNnoByPno(@Param("oldNno") String oldNno, @Param("pno") String pno);

    //查询所有患者
    List<Patient> selectAll();
    //根据姓名查询
    List<Patient> selectByPname(String pname);
    //根据ID查询
    Patient selectByPid(Long pid);

    List<Patient> selectByDid(Integer did);

    List<Map<String, Object>> getPatientCountByDepartment();
    List<Patient> selectByConditions(@Param("pname") String pname,@Param("did") Integer did, @Param("pstatus") Integer pstatus);

}
