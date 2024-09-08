package com.st.service.impl;

import com.st.bean.Duty;
import com.st.mapper.DutyMapper;
import com.st.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class DutyServiceImpl implements DutyService {
    @Autowired
    private DutyMapper dutyMapper;
    private LocalDateTime convertToLocalDateTime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDateTime();
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public boolean insert(Duty duty) {

        ////获取原始的时间字段
        //Date dutyWorkTime = duty.getDutyWorkTime();
        //Date dutyClosingTime = duty.getDutyClosingTime();
        //
        //if (dutyWorkTime != null) {
        //    LocalDateTime localDateTime = convertToLocalDateTime(dutyWorkTime);
        //    localDateTime = localDateTime.plusHours(8);
        //    duty.setDutyWorkTime(convertToDate(localDateTime));
        //}
        //if (dutyClosingTime != null) {
        //    LocalDateTime localDateTime = convertToLocalDateTime(dutyClosingTime);
        //    localDateTime = localDateTime.plusHours(8);
        //    duty.setDutyClosingTime(convertToDate(localDateTime));
        //}
        return dutyMapper.insert(duty) == 1;
    }

    @Override
    public boolean delete(Long dutyId) {

        int intDutyId = dutyId.intValue(); // 转换为基本类型 int
        Integer integerDutyId = Integer.valueOf(intDutyId); // 转换为 Integer 对象
        //删除值班和护士的关系
        dutyMapper.deleteDutyIdAndNidByDutyId(integerDutyId);
        return dutyMapper.delete(dutyId) == 1;
    }

    @Override
    public boolean update(Duty duty) {
        //获取原始的时间字段
        Date dutyWorkTime = duty.getDutyWorkTime();
        Date dutyClosingTime = duty.getDutyClosingTime();

        if (dutyWorkTime != null) {
            LocalDateTime localDateTime = convertToLocalDateTime(dutyWorkTime);
            localDateTime = localDateTime.plusHours(8);
            duty.setDutyWorkTime(convertToDate(localDateTime));
        }
        if (dutyClosingTime != null) {
            LocalDateTime localDateTime = convertToLocalDateTime(dutyClosingTime);
            localDateTime = localDateTime.plusHours(8);
            duty.setDutyClosingTime(convertToDate(localDateTime));
        }
        return dutyMapper.update(duty) == 1;
    }

    @Override
    public Duty selectByDutyId(Long dutyId) {
        return dutyMapper.selectByDutyId(dutyId);
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
