package com.st.service;

import com.st.bean.Department;
import com.st.exception.SteduException;

import java.util.List;

public interface DepartmentService {
    boolean insert(Department d) throws SteduException;
    boolean delete(Integer did) throws SteduException;
    boolean update(Department d) throws SteduException;
    List<Department> selectAll();
    Department selectByDid(Integer did);

}
