package com.feng.service;

import com.feng.dao.StudentDAO;
import com.feng.model.PageBean;
import com.feng.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class StudentService {
    @Autowired
    StudentDAO studentDAO;


    public Map<String,Object> selectStudentById(Integer id) {
        Map<String,Object> map = new HashMap<String,Object>();

        Student student = studentDAO.selectStudentById(id);
        if (student==null){
            map.put("msg","该学生不存在");
            return map;
        }else{
            map.put("student",student);
            return map;
        }
    }


    public PageBean<Student> listStudent (Integer curPage) {
        //每页记录数，从哪开始
        int limit = 8;
        int offset = (curPage-1) * limit;
        //获得总记录数，总页数
        int allCount = studentDAO.selectStudentCount();
        int allPage = 0;
        if (allCount <= limit) {
            allPage = 1;
        } else if (allCount / limit == 0) {
            allPage = allCount / limit;
        } else {
            allPage = allCount / limit + 1;
        }
        List<Student> postList = studentDAO.listStudent(offset,limit);
        //构造PageBean
        PageBean<Student> pageBean = new PageBean<Student>(allPage,curPage);
        pageBean.setList(postList);
        return pageBean;
    }

}
