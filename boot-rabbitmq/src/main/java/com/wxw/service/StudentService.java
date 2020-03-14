package com.wxw.service;

import com.wxw.domain.Student;

public interface StudentService {

    /**
     *  根据学生编号 获取学生信息
     * @param stuId
     * @return
     */
    Student findByStuId(Integer stuId);
}
