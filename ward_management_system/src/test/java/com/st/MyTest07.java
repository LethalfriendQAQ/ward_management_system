package com.st;

import com.st.mapper.DutyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest07 {
    @Autowired
    private DutyMapper dutyMapper;


    @Test
    public void test01() {
        Long[] nids = {1L, 2L, 3L};
        dutyMapper.insertDutyIdAndNid(1, nids);
    }

}
