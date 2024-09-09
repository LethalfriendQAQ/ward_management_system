package com.st.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.st.bean.Admin;
import com.st.exception.SteduException;
import com.st.mapper.AdminMapper;
import com.st.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) throws SteduException {
        Admin admin = adminMapper.selectByUsername(username);
        if (admin == null) {
            throw new SteduException("用户名错误，请确认后重新登录");
        }

        //对用户输入的密码进行加密 - 两次MD5加密
        String md5Pwd = SecureUtil.md5(SecureUtil.md5(password));
        if (!md5Pwd.equals(admin.getPassword())) {
            throw new SteduException("密码错误，请确认后重新登录");
        }
        return admin;
    }

    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    @Override
    public Admin selectByUsername(String username) {
        return adminMapper.selectByUsername(username);
    }
}
