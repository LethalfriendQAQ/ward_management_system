package com.st.service.impl;

import com.st.bean.Nurse;
import com.st.mapper.NurseMapper;
import com.st.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {
    @Autowired
    private NurseMapper nurseMapper;
    @Override
    public boolean insert(Nurse n) {
        return nurseMapper.insert(n) == 1;
    }

    @Override
    public boolean delete(Long nid) {
        return nurseMapper.delete(nid) == 1;
    }

    @Override
    public boolean update(Nurse n) {
        return nurseMapper.update(n) == 1;
    }

    @Override
    public List<Nurse> selectAll() {
        return nurseMapper.selectAll();
    }

    @Override
    public List<Nurse> selectByNname(String nname) {
        return nurseMapper.selectByNname(nname);
    }

    @Override
    public Nurse selectByNid(Long nid) {
        return nurseMapper.selectByNid(nid);
    }

    @Override
    public List<Nurse> selectByDid(Integer did) {
        return nurseMapper.selectByDid(did);
    }

    @Override
    public Nurse selectByNno(String nno) {
        return nurseMapper.selectByNno(nno);
    }
}
