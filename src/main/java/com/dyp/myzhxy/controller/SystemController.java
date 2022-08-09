package com.dyp.myzhxy.controller;


import com.dyp.myzhxy.pojo.Admin;
import com.dyp.myzhxy.pojo.LoginForm;
import com.dyp.myzhxy.pojo.Student;
import com.dyp.myzhxy.pojo.Teacher;
import com.dyp.myzhxy.service.AdminService;
import com.dyp.myzhxy.service.StudentService;
import com.dyp.myzhxy.service.TeacherService;
import com.dyp.myzhxy.util.CreateVerifiCodeImage;
import com.dyp.myzhxy.util.JwtHelper;
import com.dyp.myzhxy.util.Result;
import com.dyp.myzhxy.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;



    @GetMapping("/getInfo")
    public Result getInfoByToken(@RequestHeader("token") String token){

        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }

        //从token中解析出 用户id 和用户的类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);

        Map<String,Object> map =new LinkedHashMap<>();
        switch (userType){
            case 1:
                Admin admin =adminService.getAdminById(userId);
                map.put("userType",1);
                map.put("user",admin);
                break;
            case 2:
                Student student =studentService.getStudentById(userId);
                map.put("userType",2);
                map.put("user",student);
                break;
            case 3:
                Teacher teacher= teacherService.getByTeacherById(userId);
                map.put("userType",3);
                map.put("user",teacher);
                break;
        }
        return Result.ok(map);


    }




    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm,HttpServletRequest request){

        HttpSession session = request.getSession();
        String sessionVerifiCode = (String) session.getAttribute("verifiCode");
        String loginVerifiCode = loginForm.getVerifiCode();

        if ("".equals(sessionVerifiCode) || null == sessionVerifiCode ){
            return Result.fail().message("验证码失效重新刷新");
        }
        if (!sessionVerifiCode.equalsIgnoreCase(loginVerifiCode)){
            return Result.fail().message("验证码有误,请小心输入后重试");
        }

        session.removeAttribute("verifiCode");

        // 准备一个map用户存放响应的数据
        Map<String,Object> map=new LinkedHashMap<>();
        // 分用户类型进行校验
        switch (loginForm.getUserType()) {
            case 1:
                try {
                    Admin admin=adminService.login(loginForm);
                    if (null != admin) {
                        // 用户的类型和用户id转换成一个密文,以token的名称向客户端反馈
                        map.put("token", JwtHelper.createToken(admin.getId().longValue(), 1));
                    }else{
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Student student= studentService.login(loginForm);
                    if (null != student) {
                        // 用户的类型和用户id转换成一个密文,以token的名称向客户端反馈
                        map.put("token", JwtHelper.createToken(student.getId().longValue(), 1));
                    }else{
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    Teacher teacher= teacherService.login(loginForm);
                    if (null != teacher) {
                        // 用户的类型和用户id转换成一个密文,以token的名称向客户端反馈
                        map.put("token", JwtHelper.createToken(teacher.getId().longValue(), 1));
                    }else{
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        return Result.fail().message("查无此用户");
    }

    @RequestMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage (HttpServletRequest request, HttpServletResponse response){
        //获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();

        //获取code
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode",verifiCode);
        try {
            ImageIO.write(verifiCodeImage,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
