package com.dyp.myzhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @project: sms
 * @description: 学生信息
 */
@Data
@TableName("tb_student")
public class Student {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String sno;
    private String name;
    private char gender = '男';//default
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String introduction;
    private String portraitPath;//存储头像的项目路径portrait_path
    private String clazzName;//班级名称

}
