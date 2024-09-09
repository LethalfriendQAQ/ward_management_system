package com.st.controller;

import com.st.bean.Admin;
import com.st.bean.RespBean;
import com.st.service.AdminService;
import com.st.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
 * 获取已登录用户的信息并返回
 * */
@CrossOrigin
@RestController
@RequestMapping( "/user/info")
public class InfoController {
    @Autowired
    private AdminService adminService;


    @GetMapping("/userInfo")
    public RespBean getLoginInfo(HttpServletRequest request) {
        //获取jwt
        String token = request.getHeader("token");
        //解析jwt
        Map<String, Object> map = JwtUtil.parseJwtToMap(token);
        Integer id = (Integer) map.get("id");
        //根据ID查询用户信息
        Admin admin = adminService.selectById(id);

        //返回
        return RespBean.ok("", admin);
    }
    //@GetMapping("/userInfo")
    //public RespBean getLoginInfo(HttpServletRequest request) {
    //    //获取jwt
    //    String token = request.getHeader("token");
    //    //解析jwt
    //    Map<String, Object> map = JwtUtil.parseJwtToMap(token);
    //    String id = (String) map.get("username");
    //    //根据ID查询用户信息
    //    Admin admin = adminService.selectByUsername("username");
    //
    //    //返回
    //    return RespBean.ok("", admin);
    //}
}
