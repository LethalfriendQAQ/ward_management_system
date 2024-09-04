package com.st.service.impl;

import com.st.bean.Department;
import com.st.bean.Patient;
import com.st.exception.SteduException;
import com.st.mapper.DepartmentMapper;
import com.st.mapper.PatientMapper;
import com.st.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Override
    public boolean insert(Department d) throws SteduException {
        if (departmentMapper.selectByDname(d.getDname()) != null) {
            throw new SteduException("该科室已存在，无法重复添加");
        }
        return departmentMapper.insert(d) == 1;
    }

    @Override
    public boolean delete(Integer did) throws SteduException {
        if (departmentMapper.selectByDid(did) == null) {
            throw new SteduException("该科室不存在，无法删除");
        }

        //判断该科室下是否有患者
        List<Patient> list = patientMapper.selectByDid(did);
        if (list != null && !list.isEmpty()) {
            throw new SteduException("该部门内有患者，无法删除");
        }

        return departmentMapper.delete(did) == 1;
    }

    @Override
    public boolean update(Department d) throws SteduException {
        if (departmentMapper.selectByDid(d.getDid()) == null) {
            throw new SteduException("该科室不存在，无法修改");
        }
        //根据修改之后的名字（d.getDname()）查询
        Department d1 = departmentMapper.selectByDname(d.getDname());
        if(d1 != null && d1.getDid() != d.getDid()) {
            throw new SteduException("修改之后的名字和其他部门的名字重复，不允许修改");
        }

        return departmentMapper.update(d) == 1;
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public Department selectByDid(Integer did) {
        return departmentMapper.selectByDid(did);
    }
}
