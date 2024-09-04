package com.st.service.impl;

import com.st.bean.Ward;
import com.st.exception.SteduException;
import com.st.mapper.WardMapper;
import com.st.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardMapper wardMapper;
    @Override
    public boolean insert(Ward w) throws SteduException {
        if (wardMapper.selectByWnumber(w.getWnumber()) != null) {
            throw new SteduException("该病房已存在，无法重复添加");
        }
        return wardMapper.insert(w) == 1;
    }

    @Override
    public boolean delete(Long wid) throws SteduException {
        if (wardMapper.selectByWid(wid) == null) {
            throw new SteduException("该病房不存在，无法删除");
        }

        //判断该病房下是否有患者
        //List<Ward> list = wardMapper.selectByDid(did);
        //if (list != null && !list.isEmpty()) {
        //    throw new SteduException("该部门内有患者，无法删除");
        //}

        return wardMapper.delete(wid) == 1;
    }

    @Override
    public boolean update(Ward w) throws SteduException {
        if (wardMapper.selectByWid(w.getWid()) == null) {
            throw new SteduException("该病房不存在，无法修改");
        }
        //根据修改之后的名字查询
        Ward w1 = wardMapper.selectByWnumber(w.getWnumber());
        if(w1 != null && w1.getWid() != w.getWid()) {
            throw new SteduException("修改之后的病房号和其他病房号重复，不允许修改");
        }

        return wardMapper.update(w) == 1;
    }

    @Override
    public List<Ward> selectAll() {
        return wardMapper.selectAll();
    }

    @Override
    public Ward selectByWnumber(String wnumber) {
        return wardMapper.selectByWnumber(wnumber);
    }

    @Override
    public Ward selectByWid(Long wid) {
        return wardMapper.selectByWid(wid);
    }
}
