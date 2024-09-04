package com.st;

import com.st.bean.Nurse;
import com.st.mapper.NurseMapper;
import com.st.service.NurseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest02 {
    @Autowired
    private NurseMapper nurseMapper;
    @Autowired
    private NurseService nurseService;

    @Test
    public void test01(){
        Nurse nurse = new Nurse(null, "2222", "11", 1);
        int result = nurseMapper.insert(nurse);
        System.out.println(result);
    }

    @Test
    public void test02() {
        System.out.println(nurseMapper.delete(2L));
    }

    @Test
    public void test03(){
        Nurse nurse = new Nurse(3L, "33333", "11", 1);
        int result = nurseMapper.update(nurse);
        System.out.println(result);
    }

    @Test
    public void test04() {
        nurseMapper.selectAll()
                .stream()
                .forEach(System.out::println);
    }

    @Test
    public void test05() {
        nurseMapper.selectByNname("马")
                .stream()
                .forEach(System.out::println);

    }

    @Test
    public void test06() {
        Nurse nurse = nurseMapper.selectByNid(3L);
        System.out.println(nurse);
    }

    @Test
    public void test07() {
        nurseMapper.selectByDid(2)
                .stream()
                .forEach(System.out::println);
    }
    @Test
    public void test08() {
        Nurse nurse = nurseMapper.selectByNno("1111");
        System.out.println(nurse);
    }
}
