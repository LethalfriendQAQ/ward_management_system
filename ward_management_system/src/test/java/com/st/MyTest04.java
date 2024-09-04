package com.st;

import com.st.bean.Ward;
import com.st.mapper.WardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest04 {
    @Autowired
    private WardMapper wardMapper;

    @Test
    public void test01() {
        Ward ward = new Ward(null, "101", 1);
        int result = wardMapper.insert(ward);
        System.out.println(result);
    }

}
