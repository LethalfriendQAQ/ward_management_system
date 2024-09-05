package com.st.service.impl;

import com.st.bean.Bed;
import com.st.bean.Ward;
import com.st.exception.SteduException;
import com.st.mapper.BedMapper;
import com.st.mapper.WardMapper;
import com.st.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardMapper wardMapper;
    @Autowired
    private BedMapper bedMapper;
    @Override
    public boolean insert(Ward w) throws SteduException {
        if (wardMapper.selectByWnumber(w.getWnumber()) != null) {
            throw new SteduException("该病房已存在，无法重复添加");
        }

        if (wardMapper.insert(w) == 1) {
            //获取新插入病房的ID
            Long wid = w.getWid();

            //插入三个病床
            for (int i = 1; i<= 3; i++) {
                Bed bed = new Bed();
                bed.setWnumber(w.getWnumber()); //使用病房号
                bed.setPno(null);
                bed.setNno(null);
                bed.setBnumber(w.getWnumber() + "-" + i); //为病床设置唯一编号
                bedMapper.insert(bed);

            }
            return true;
        }
        return true;
    }

    @Override
    public boolean delete(Long wid) throws SteduException {
        if (wardMapper.selectByWid(wid) == null) {
            throw new SteduException("该病房不存在，无法删除");
        }

        //TODO 判断该病房下是否有患者
        //List<Ward> list = wardMapper.selectByDid(did);
        //if (list != null && !list.isEmpty()) {
        //    throw new SteduException("该部门内有患者，无法删除");
        //}

        bedMapper.deleteByWnumber(wardMapper.selectByWid(wid).getWnumber());
        return wardMapper.delete(wid) == 1;
    }

    @Override
    public boolean update(Ward w) throws SteduException {
        Ward originalWard  = wardMapper.selectByWid(w.getWid());
        if (originalWard == null) {
            throw new SteduException("该病房不存在，无法修改");
        }
        //根据修改之后的病房号查询
        Ward w1 = wardMapper.selectByWnumber(w.getWnumber());
        if(w1 != null && w1.getWid() != w.getWid()) {
            throw new SteduException("修改之后的病房号和其他病房号重复，不允许修改");
        }
        bedMapper.updateWnumberByOldwnumber(originalWard.getWnumber(), w.getWnumber());

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
