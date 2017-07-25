package com.feng.dao;

import com.feng.model.Student;

import java.util.List;


public interface UpdateDAO {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Integer id);
    List<Student> findStudentByParameter(Student student);
}
