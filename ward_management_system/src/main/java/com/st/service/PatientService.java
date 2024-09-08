package com.st.service;

import com.st.bean.Patient;
import com.st.exception.SteduException;

import java.util.List;
import java.util.Map;

public interface PatientService {
    boolean insert(Patient p) throws SteduException;
    boolean delete(Long pid);
    boolean update(Patient p);
    List<Patient> selectAll();
    List<Patient> selectByPname(String pname);
    List<Patient> selectByDid(Integer did);
    Patient selectByPid(Long pid);
    List<Map<String, Object>> getPatientCountByDepartment();

    List<Patient> selectByConditions(String pname, Integer did, Integer pstatus);
}
