package com.dyp.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyp.myzhxy.pojo.LoginForm;
import com.dyp.myzhxy.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {


    Teacher login(LoginForm loginForm);


    Teacher getByTeacherById(Long userId);

    IPage<Teacher> getTeachersByOpr(Page<Teacher> paraParam, Teacher teacher);
}
