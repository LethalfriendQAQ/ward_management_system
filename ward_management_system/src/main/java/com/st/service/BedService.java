package com.st.service;

import com.st.bean.Bed;
import com.st.bean.DepartmentBedOccupancyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BedService {
    boolean insert(Bed b);
    boolean delete(Long bid);
    boolean update(Bed b);
    //查询所有病床
    List<Bed> selectAll();
    //根据病床号查询
    Bed selectByBnumber(String bnumber);
    List<Bed> selectByWnumber(String wnumber);
    Bed selectByBid(Long bid);
    List<Bed> selectFreeBedsByWnumber(@Param("wnumber") String wnumber);
    List<DepartmentBedOccupancyDTO> getBedOccupancyByDepartment();
}
