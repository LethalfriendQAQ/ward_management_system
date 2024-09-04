package com.st.service.impl;

import com.st.bean.Patient;
import com.st.mapper.PatientMapper;
import com.st.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Override
    public boolean insert(Patient p) {
        //p.setPstatus(1);
        return patientMapper.insert(p) == 1;
    }

    @Override
    public boolean delete(Long pid) {
        return patientMapper.setPstatus(pid, 2) == 1;
    }

    @Override
    public boolean update(Patient p) {
        return patientMapper.update(p) == 1;
    }

    @Override
    public List<Patient> selectAll() {
        return patientMapper.selectAll();
    }

    @Override
    public List<Patient> selectByPname(String pname) {
        return patientMapper.selectByPname(pname);
    }

    @Override
    public Patient selectByPid(Long pid) {
        return patientMapper.selectByPid(pid);
    }
}
