package com.dyp.myzhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyp.myzhxy.pojo.LoginForm;
import com.dyp.myzhxy.pojo.Student;


public interface StudentService extends IService<Student> {


    Student login(LoginForm loginForm);


    Student getStudentById(Long userId);

    IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student);
}
