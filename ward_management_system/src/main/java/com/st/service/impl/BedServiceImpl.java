package com.st.service.impl;

import com.st.bean.Bed;
import com.st.bean.DepartmentBedOccupancyDTO;
import com.st.bean.Ward;
import com.st.mapper.BedMapper;
import com.st.mapper.PatientMapper;
import com.st.mapper.WardMapper;
import com.st.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BedServiceImpl implements BedService {
    @Autowired
    private BedMapper bedMapper;
    @Autowired
    private WardMapper wardMapper;
    @Autowired
    private PatientMapper patientMapper;

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
        patientMapper.updateNnoByPno(b.getNno(), b.getPno());
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

    @Override
    public List<Bed> selectFreeBedsByWnumber(String wnumber) {
        return bedMapper.selectFreeBedsByWnumber(wnumber);
    }

    @Override
    public List<DepartmentBedOccupancyDTO> getBedOccupancyByDepartment() {
        // 获取所有病房数据
        List<Ward> wards = wardMapper.selectAll();

        // 获取所有病床数据
        List<Bed> beds = bedMapper.selectAll();

        // 统计每个科室的病床总数和已占用的病床数
        Map<String, Integer> bedCountMap = new HashMap<>();
        Map<String, Integer> occupiedBedCountMap = new HashMap<>();

        // 初始化病床计数器
        for (Ward ward : wards) {
            String departmentId = String.valueOf(ward.getDid());
            bedCountMap.put(departmentId, 0);
            occupiedBedCountMap.put(departmentId, 0);
        }

        // 统计每个科室的病床信息
        for (Bed bed : beds) {
            String wardNumber = bed.getWnumber();
            Ward ward = wards.stream().filter(w -> w.getWnumber().equals(wardNumber)).findFirst().orElse(null);

            if (ward != null) {
                String departmentId = String.valueOf(ward.getDid());
                bedCountMap.put(departmentId, bedCountMap.get(departmentId) + 1);
                if (bed.getPno() != null) { // 病床已被占用
                    occupiedBedCountMap.put(departmentId, occupiedBedCountMap.get(departmentId) + 1);
                }
            }
        }

        // 计算病床占有率并保留两位小数
        List<DepartmentBedOccupancyDTO> departmentBedOccupancyList = new ArrayList<>();
        for (String departmentId : bedCountMap.keySet()) {
            int totalBeds = bedCountMap.get(departmentId);
            int occupiedBeds = occupiedBedCountMap.get(departmentId);

            if (totalBeds > 0) {
                // 计算占有率，并保留两位小数
                double occupancyRate = (double) occupiedBeds / totalBeds * 100;
                occupancyRate = Math.round(occupancyRate * 100.0) / 100.0;  // 四舍五入保留两位小数

                departmentBedOccupancyList.add(new DepartmentBedOccupancyDTO(departmentId, occupancyRate));
            } else {
                // 处理 totalBeds 为 0 的情况
                departmentBedOccupancyList.add(new DepartmentBedOccupancyDTO(departmentId, 0.0));
            }
        }


        return departmentBedOccupancyList;
    }
}
