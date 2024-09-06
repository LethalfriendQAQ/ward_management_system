package com.st;

import com.st.bean.Bed;
import com.st.service.BedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest06 {
    @Autowired
    private BedService bedService;


    @Test
    public void test01() {
        Bed bed = new Bed(null, "101-2", null, null, "101");
        System.out.println(bedService.insert(bed));
    }

}
