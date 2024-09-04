package com.st;

import com.st.bean.Department;
import com.st.mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WardManagementSystemApplicationTests {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void test01() {
        Department d = new Department(null,"111", "1111");
        int result = departmentMapper.insert(d);
        System.out.println(result);
    }

    @Test
    public void test02() {
        System.out.println(departmentMapper.delete(8));
    }

    @Test
    public void test03() {
        Department d = new Department(10, "222", "222");
        int result = departmentMapper.update(d);
        System.out.println(result);
    }

    @Test
    public void test04() {
        departmentMapper.selectAll()
                .stream()
                .forEach(System.out::println);
    }
    @Test
    public void test05() {
        Department d = departmentMapper.selectByDid(10);
        System.out.println(d);
    }
}
