package com.st.service;

import com.st.bean.Admin;
import com.st.exception.SteduException;

public interface AdminService {
    Admin login(String username, String password) throws SteduException;

    Admin selectById(Integer id);

    Admin selectByUsername(String  username);
}
