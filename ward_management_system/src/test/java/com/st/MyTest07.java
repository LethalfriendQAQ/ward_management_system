package com.st;

import com.st.mapper.DutyMapper;
import com.st.service.DutyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest07 {
    @Autowired
    private DutyMapper dutyMapper;
    @Autowired
    private DutyService dutyService;


    @Test
    public void test11() {
        Long[] nids = {2L, 3L, 4L};
        dutyService.insertDutyIdAndNid(2, nids);
    }
    @Test
    public void test12() {
        dutyService.selectAll()
                .stream()
                .forEach(System.out::println);
    }
    @Test
    public void test01() {
        Long[] nids = {1L, 2L, 3L};
        dutyMapper.insertDutyIdAndNid(1, nids);
    }
    @Test
    public void test02() {
        dutyMapper.selectAll()
                .stream()
                .forEach(System.out::println);
    }

}
