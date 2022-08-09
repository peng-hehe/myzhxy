package com.dyp.myzhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyp.myzhxy.mapper.AdminMapper;
import com.dyp.myzhxy.pojo.Admin;
import com.dyp.myzhxy.pojo.LoginForm;
import com.dyp.myzhxy.service.AdminService;
import com.dyp.myzhxy.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @param
 * @Description: (描述此类的功能)
 * @return
 * @throws
 * @author: duyapeng
 * @date:
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin>  queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getUsername()));
        Admin admin = baseMapper.selectOne(queryWrapper);

        return admin;
    }

    @Override
    public Admin getAdminById(Long userId) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<Admin>();
        queryWrapper.eq("id",userId);
        return baseMapper.selectOne(queryWrapper);
    }
}
