package com.st.controller;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.st.bean.Admin;
import com.st.bean.RespBean;
import com.st.exception.SteduException;
import com.st.service.AdminService;
import com.st.utils.JwtUtil;
import com.st.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisUtil redisUtil;

    @ResponseBody
    @PostMapping("/login")
    public RespBean login(String username, String password, String captchaId, String captcha) throws SteduException {

        //判断验证码 - 和Redis中保存的验证码文本对比
        String captchaRedis = (String) redisUtil.get(captchaId);
        if (captchaRedis != null || (captchaRedis != null && !captchaRedis.equalsIgnoreCase(captcha))) {
            return RespBean.error("验证码错误，请刷新后重新输入");
        }


        //判断用户名和密码是否正确
        Admin admin = adminService.login(username, password);

        //登录成功之后JWT发送给前端
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", admin.getId());
        map.put("username", admin.getUsername());

        String jwt = JwtUtil.generateJwt(map);
        return RespBean.ok("登录成功",jwt);
    }

    @GetMapping("/captcha")
    public RespBean captcha() throws IOException {
        //生成验证码对象
        LineCaptcha captcha = new LineCaptcha(120, 38, 4, 20);
        //获取验证码文本
        String code = captcha.getCode();
        //生成唯一的ID
        String captchaId = IdUtil.getSnowflakeNextIdStr();
        //获取验证码文本保存在Redis中
        redisUtil.set(captchaId, code, 60);
        //Base64 图片 -- 字符串
        String captchaImageBase64Data = captcha.getImageBase64Data();
        //将capchaId和验证码图片发送给浏览器
        HashMap<String, Object> map = new HashMap<>();
        map.put("capchaId", captchaId);
        map.put("captchaImageBase64Data", captchaImageBase64Data);
        return RespBean.ok("", map);

    }


}
