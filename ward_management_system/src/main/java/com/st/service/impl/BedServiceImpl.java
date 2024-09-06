package com.st.service.impl;

import com.st.bean.Bed;
import com.st.mapper.BedMapper;
import com.st.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl implements BedService {
    @Autowired
    private BedMapper bedMapper;

    @Override
    public boolean insert(Bed b) {
        return bedMapper.insert(b)  == 1;
    }

    @Override
    public boolean delete(Long bid) {
        return bedMapper.delete(bid) == 1;
    }

    @Override
    public boolean update(Bed b) {
        return bedMapper.update(b) == 1;
    }

    @Override
    public List<Bed> selectAll() {
        return bedMapper.selectAll();
    }

    @Override
    public Bed selectByBnumber(String bnumber) {
        return bedMapper.selectByBnumber(bnumber);
    }

    @Override
    public List<Bed> selectByWnumber(String wnumber) {
        return bedMapper.selectByWnumber(wnumber);
    }

    @Override
    public Bed selectByBid(Long bid) {
        return bedMapper.selectByBid(bid);
    }
}
