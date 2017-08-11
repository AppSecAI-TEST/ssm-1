package com.feng.controller;

import com.feng.model.Student;
import com.feng.service.StudentService;
import com.feng.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UpdateController {
    @Autowired
    UpdateService updateService;
    @Autowired
    StudentService studentService;



    @RequestMapping(value ="/add", method = {RequestMethod.POST})
    public String add(String name, String gender, Integer age, String number, String tel, MultipartFile file) {
        try {
            String image = updateService.saveImage(file);
            updateService.addStudent(name,gender,age,number,tel,image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/main";
    }

    @RequestMapping(value ="/updateStudentById", method = {RequestMethod.POST})
    public String updateStudentById(Model model,Integer id) { //如果表单中id为"",那么Controller方法参数中的id值为null
        if (id != null){
            model.addAttribute("id", id);
            return "redirect:/updateStudent/{id}";
        }else return "updatePage";
    }

    @RequestMapping(value ="/updateStudent/{studentId}", method = {RequestMethod.GET})
    public String updateStudent(Model model,@PathVariable("studentId") Integer studentId) {
        Map<String, Object> map  = studentService.selectStudentById(studentId);
        if (map.containsKey("msg")){
            model.addAttribute("msg",map.get("msg"));
            return "updatePage";
        }
        model.addAttribute("student", map.get("student"));
        return "updateStudent";
    }

    @RequestMapping(value ="/update", method = {RequestMethod.POST})
    public String update(Integer id, String name, String gender, Integer age, String number, String tel, MultipartFile file) {
        try {
            String image = updateService.saveImage(file);
            updateService.updateStudent(id,name,gender,age,number,tel,image);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/main";
    }

    @RequestMapping(value ="/deleteStudent", method = {RequestMethod.GET,RequestMethod.POST})
    public String deleteStudent(Model model, Integer id) {
        Map<String, Object> map = studentService.selectStudentById(id);
        if (map.containsKey("msg")){
            model.addAttribute("msg",map.get("msg"));
            return "deletePage";
        }
        updateService.deleteStudent(id);
        return "redirect:/main";
    }

    @RequestMapping(value ="/search", method = {RequestMethod.POST})
    public String search(Model model,Student student) { //接收Student对象作为参数，对象中的属性将会使用请求中同名的参数进行填充
        List<Student> searchList = updateService.findStudent(student);
        model.addAttribute("searchList",searchList);
        return "searchStudent";
    }
}
