package com.wxw.boot;

import com.wxw.domain.Student;
import com.wxw.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    private StudentService studentService;
    @Test
    public void contextLoads() {
        Student student = studentService.findByStuId(2);
        System.out.println("student = " + student);
    }

}
