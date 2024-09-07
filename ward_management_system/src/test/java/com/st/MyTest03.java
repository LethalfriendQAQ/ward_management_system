package com.st;

import com.st.service.NurseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest03 {
    @Autowired
    private NurseService nurseService;

    @Test
    public void test01() {
        //Nurse nurse = new Nurse(null, "3434", "11", 1);
        //System.out.println(nurseService.insert(nurse));
    }

}
