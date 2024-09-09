package com.st.mapper;

import com.st.bean.Admin;

public interface AdminMapper {
    //根据用户名和密码查询
    Admin selectByUsername(String username);

    Admin selectById(Integer id);
}
