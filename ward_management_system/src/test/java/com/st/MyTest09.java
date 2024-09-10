package com.st;

import com.st.mapper.DutyMapper;
import com.st.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest09 {
    @Autowired
    private AdminService adminService;
    @Autowired
    private DutyMapper dutyMapper;
    @Test
    public void test01() {
        System.out.println(adminService.selectById(1));
        
    }

    @Test
    public void test02() {
        System.out.println(dutyMapper.selectAll());

    }
}
