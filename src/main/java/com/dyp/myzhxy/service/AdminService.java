package com.dyp.myzhxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dyp.myzhxy.pojo.Admin;
import com.dyp.myzhxy.pojo.LoginForm;

/**
 * @param
 * @Description: (描述此类的功能)
 * @return
 * @throws
 * @author: duyapeng
 * @date:
 */
public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin getAdminById(Long userId);
}
