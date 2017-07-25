package com.feng.dao;

import com.feng.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDAO {
    int selectStudentCount();
    List<Student> listStudent(@Param("offset") int offset, @Param("limit") int limit);
    Student selectStudentById(Integer id);


}
