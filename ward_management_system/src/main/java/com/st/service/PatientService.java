package com.st.service;

import com.st.bean.Patient;
import com.st.exception.SteduException;

import java.util.List;

public interface PatientService {
    boolean insert(Patient p) throws SteduException;
    boolean delete(Long pid);
    boolean update(Patient p);
    List<Patient> selectAll();
    List<Patient> selectByPname(String pname);
    Patient selectByPid(Long pid);

}
