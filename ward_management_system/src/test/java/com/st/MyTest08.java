package com.st;

import com.st.utils.JwtUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MyTest08 {
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
}
