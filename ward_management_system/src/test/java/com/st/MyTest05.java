package com.st;

import com.st.bean.Bed;
import com.st.mapper.BedMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest05 {
    @Autowired
    private BedMapper bedMapper;


    @Test
    public void test01() {
        Bed bed = new Bed(null, "1", "1111", "111", "101");
        int result = bedMapper.insert(bed);
        System.out.println(result);
    }

}
