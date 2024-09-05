package com.st.service.impl;

import com.st.bean.Patient;
import com.st.mapper.BedMapper;
import com.st.mapper.PatientMapper;
import com.st.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private BedMapper bedMapper;
    @Override
    public boolean insert(Patient p) {
        //p.setPstatus(1);
        //return patientMapper.insert(p) == 1;
        if (patientMapper.insert(p) == 1) {
            bedMapper.updatePnoAndNnoByBnumber(p.getPno(), p.getNno(), p.getBnumber());
        }
        return true;
    }

    @Override
    public boolean delete(Long pid) {

        if (patientMapper.setPstatus(pid, 2) == 1) {
            bedMapper.updatePnoAndNnoByBnumber(null, null, patientMapper.selectByPid(pid).getBnumber());
        }
        return true;
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
