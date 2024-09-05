package com.st;

import com.st.bean.Patient;
import com.st.mapper.PatientMapper;
import com.st.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class MyTest01 {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private PatientService patientService;


    @Test
    public void test11() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Patient p = new Patient(null, "23232", "11", 22, "女", sdf.parse("2021-06-06"), sdf.parse("2021-07-06"), 1, "213", 2, "1002", "101", "101-1");
        System.out.println(patientService.insert(p));
    }
    @Test
    public void test12() {
        System.out.println(patientService.delete(22L));
    }

    @Test
    public void test13() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Patient p = new Patient(22L, "23232", "11", 23, "女", sdf.parse("2021-06-06"), sdf.parse("2021-07-06"), 1, "213", 4, "1004", "405", "405-1");
        System.out.println(patientService.update(p));
    }




    @Test
    public void test01() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Patient p = new Patient(null, "112", "11", 22, "女", sdf.parse("2021-06-06"), sdf.parse("2021-07-06"), 1, "213", 2, "1001", "302", "1");
        int result = patientMapper.insert(p);
        System.out.println(result);
    }

    @Test
    public void test02() {
        System.out.println(patientMapper.delete(2L));
    }

    @Test
    public void test03() throws ParseException {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Patient p = new Patient(3L, "112", "11", 22, "male", sdf.parse("2021-04-06"), sdf.parse("2021-07-06"), 1, "213", 2);
        //int result = patientMapper.update(p);
        //System.out.println(result);
    }

    @Test
    public void test04() {
        patientMapper.selectAll()
                .stream()
                .forEach(System.out::println);
    }

    @Test
    public void test05() {
        patientMapper.selectByPname("张")
                .stream()
                .forEach(System.out::println);

    }
    @Test
    public void test06() {
        Patient patient = patientMapper.selectByPid(3L);
        System.out.println(patient);
    }
}
