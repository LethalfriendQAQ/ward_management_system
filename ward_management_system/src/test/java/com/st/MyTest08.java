package com.st;

import com.st.exception.SteduException;
import com.st.service.AdminService;
import com.st.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MyTest08 {
    @Autowired
    private AdminService adminService;
    @Test
    public void test01() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("age", 20);

        //使用JWT工具类生成JWT  String
        String jwtStr = JwtUtil.generateJwt(map);
        System.out.println(jwtStr);
    }

    @Test
    public void test02() {
        //解析JWT
        Map<String, Object> map= JwtUtil.parseJwtToMap("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXlsb2FkIjp7ImFnZSI6MjAsInVzZXJuYW1lIjoiYWRtaW4ifSwiZXhwIjoxNzI1ODUzNjI5fQ.urc3fNxkWtLpfrOeLKqwSV6dsNYwaxPXFilG6hc4kW0");
        System.out.println(map.get("username"));
        System.out.println(map.get("age"));
    }

    @Test
    public void test03() throws SteduException {
        System.out.println(adminService.login("admin", "admin"));

    }
}
