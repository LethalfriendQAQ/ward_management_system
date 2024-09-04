package com.st.service;

import com.st.bean.Ward;
import com.st.exception.SteduException;

import java.util.List;


public interface WardService {
    boolean insert(Ward w) throws SteduException;
    boolean delete(Long wid) throws SteduException;
    boolean update(Ward w) throws SteduException;
    List<Ward> selectAll();
    Ward selectByWnumber(String wnumber);
    Ward selectByWid(Long wid);
}
