package com.wxw.controller;

import com.wxw.domain.Student;
import com.wxw.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Api(tags = "StudentController",description = "学生管理")
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("根据ID获取学生信息")
    @GetMapping("/findStudentById/{stuId}")
    @ResponseBody
    public Student findById(@PathVariable("stuId") Integer stuId){
        // Student student = new Student(1,"魏永杰","六班",100,new Date());
        return  studentService.findByStuId(stuId);
    }
}
