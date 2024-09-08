package com.st.service.impl;

import com.st.bean.Patient;
import com.st.exception.SteduException;
import com.st.mapper.BedMapper;
import com.st.mapper.PatientMapper;
import com.st.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private BedMapper bedMapper;
    @Override
    public boolean insert(Patient p) throws SteduException {
        //p.setPstatus(1);
        //return patientMapper.insert(p) == 1;

        if (bedMapper.selectByBnumber(p.getBnumber()).getPno() == null){
            bedMapper.updatePnoAndNnoByBnumber(p.getPno(), p.getNno(), p.getBnumber());
        } else {
            throw new SteduException("该病床下已有病人");
        }


        return patientMapper.insert(p) == 1;
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
        Patient originalPatient = patientMapper.selectByPid(p.getPid());
        if (patientMapper.update(p) == 1) {
            if (!(originalPatient.getBnumber().equals(p.getBnumber()))) {
                bedMapper.updatePnoAndNnoByBnumber(null, null, originalPatient.getBnumber());
            }
            bedMapper.updatePnoAndNnoByBnumber(p.getPno(), p.getNno(), p.getBnumber());
        }
        return true;
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
    public List<Patient> selectByDid(Integer did) {
        return patientMapper.selectByDid(did);
    }

    @Override
    public Patient selectByPid(Long pid) {
        return patientMapper.selectByPid(pid);
    }



    public List<Map<String, Object>> getPatientCountByDepartment() {
        return patientMapper.getPatientCountByDepartment();
    }

    @Override
    public List<Patient> selectByConditions(String pname, Integer did, Integer pstatus) {
        return patientMapper.selectByConditions(pname, did, pstatus);
    }
}
