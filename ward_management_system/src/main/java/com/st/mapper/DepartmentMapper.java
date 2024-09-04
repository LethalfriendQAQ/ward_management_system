package com.st.mapper;

import com.st.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    //添加
    int insert(Department d);

    //根据ID删除
    int delete(Integer did);

    //根据ID修改
    int update(Department d);
    //查询所有科室
    List<Department> selectAll();

    //根据科室名称查询
    Department selectByDname(String dname);

    //根据ID查询
    Department selectByDid(Integer did);
}
