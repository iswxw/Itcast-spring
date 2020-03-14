package com.wxw.service.impl;

import com.wxw.domain.Student;
import com.wxw.mapper.StudentMapper;
import com.wxw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wxw
 * @create: 2020-03-15-1:22
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findByStuId(Integer stuId) {
        return studentMapper.selectByPrimaryKey(stuId);
    }
}
