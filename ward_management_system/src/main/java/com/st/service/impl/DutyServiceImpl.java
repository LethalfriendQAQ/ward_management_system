package com.st.service.impl;

import com.st.bean.Duty;
import com.st.mapper.DutyMapper;
import com.st.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyServiceImpl implements DutyService {
    @Autowired
    private DutyMapper dutyMapper;

    @Override
    public boolean insert(Duty duty) {
        return dutyMapper.insert(duty) == 1;
    }

    @Override
    public boolean delete(Long dutyId) {
        return dutyMapper.delete(dutyId) == 1;
    }

    @Override
    public boolean update(Duty duty) {
        return dutyMapper.update(duty) == 1;
    }

    @Override
    public List<Duty> selectAll() {
        return dutyMapper.selectAll();
    }

    @Override
    public List<Long> selectNidByDutyId(Integer dutyId) {
        return dutyMapper.selectNidByDutyId(dutyId);
    }

    @Override
    public void insertDutyIdAndNid(Integer dutyId, Long[] nids) {
        //删除值班和护士的关系
        dutyMapper.deleteDutyIdAndNidByDutyId(dutyId);

        //添加值班和护士的关系
        dutyMapper.insertDutyIdAndNid(dutyId, nids);
    }
}
