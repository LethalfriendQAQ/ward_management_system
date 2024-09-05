package com.st;

import com.st.bean.Ward;
import com.st.exception.SteduException;
import com.st.mapper.WardMapper;
import com.st.service.WardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest04 {
    @Autowired
    private WardMapper wardMapper;
    @Autowired
    private WardService wardService;

    @Test
    public void test10() throws SteduException {
        Ward ward = new Ward(null, "203", 4);
        System.out.println(wardService.insert(ward));
    }

    @Test
    public void test11() throws SteduException {
        System.out.println(wardService.delete(8L));
    }

    @Test
    public void test12() throws SteduException {
        Ward ward = new Ward(9L, "302", 3);
        System.out.println(wardService.update(ward));
    }
    @Test
    public void test13() {
        wardService.selectAll()
                .stream()
                .forEach(System.out::println);
    }

    @Test
    public void test14() {
        Ward ward = wardService.selectByWid(4L);
        System.out.println(ward);
    }

    @Test
    public void test15() {
        Ward ward = wardService.selectByWnumber("101");
        System.out.println(ward);
    }

    @Test
    public void test01() {
        Ward ward = new Ward(null, "101", 1);
        int result = wardMapper.insert(ward);
        System.out.println(result);
    }

}
