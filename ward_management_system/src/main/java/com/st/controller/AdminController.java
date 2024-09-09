package com.st.controller;

import com.st.bean.Admin;
import com.st.bean.RespBean;
import com.st.exception.SteduException;
import com.st.service.AdminService;
import com.st.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@CrossOrigin
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @PostMapping("/login")
    public RespBean login(String username, String password) throws SteduException {
        //判断用户名和密码是否正确
        Admin admin = adminService.login(username, password);

        //登录成功之后JWT发送给前端
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", admin.getId());
        map.put("username", admin.getUsername());

        String jwt = JwtUtil.generateJwt(map);
        return RespBean.ok("登录成功",jwt);
    }



}
