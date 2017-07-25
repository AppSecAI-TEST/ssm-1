package com.feng.service;

import com.feng.dao.UpdateDAO;
import com.feng.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateService {
    @Autowired
    UpdateDAO updateDAO;
    
    public void addStudent(Student student){
        updateDAO.addStudent(student);

    }
    public void deleteStudent(Integer id){
        updateDAO.deleteStudent(id);
    }


    public void updateStudent(Student student){
        updateDAO.updateStudent(student);

    }
    public List<Student> findStudent(Student student){
      return updateDAO.findStudentByParameter(student);
    }
}
