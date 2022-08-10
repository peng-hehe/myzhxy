package com.dyp.myzhxy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyp.myzhxy.pojo.Grade;
import com.dyp.myzhxy.service.GradeService;
import com.dyp.myzhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @ApiOperation("获取全部年级")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades =gradeService.getGrades();
        return Result.ok(grades);
    }

    //sms/gradeController/deleteGrade
    @ApiOperation("删除Grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@ApiParam("要删除的所有的grade的id的JSON集合") @RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);
        return Result.ok();

    }

    @ApiOperation("新增或修改grade,有id属性是修改,没有则是增加")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("JSON的Grade对象")@RequestBody Grade grade){
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }


    //sms/gradeController/getGrades/1/3?gradeName=%E4%B8%89
    @ApiOperation("根据年级名称模糊查询,带分页")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(@ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
                            @ApiParam("分页查询的页大小") @PathVariable("pageSize") Integer pageSize,
                            @ApiParam("分页查询模糊匹配的名称") String gradeName){
        Page<Grade> gradePage = new Page<>(pageNo,pageSize);
        IPage<Grade> pageRs = gradeService.getGradeByOpr(gradePage, gradeName);

        return Result.ok(pageRs);
    }



}
