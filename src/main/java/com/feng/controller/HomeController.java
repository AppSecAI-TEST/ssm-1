package com.feng.controller;

import com.feng.model.PageBean;
import com.feng.model.Student;
import com.feng.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;


@Controller
public class HomeController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value ="/main", method = {RequestMethod.GET})
    public String main(Model model,@RequestParam(value = "page",defaultValue = "1") Integer page) {
        PageBean<Student> pageBean = studentService.listStudent(page);
        model.addAttribute("pageBean", pageBean);
        return "main";
    }

    @RequestMapping(value ="/student/{studentId}", method = {RequestMethod.GET})
    public String detail(Model model, @PathVariable("studentId") Integer studentId) {
        Map<String, Object> map = studentService.selectStudentById(studentId);
        if (map.containsKey("msg")){
            model.addAttribute("msg", map.get("msg"));
            return "error";
        }
        model.addAttribute("student", map.get("student"));
        return "studentDetail";
    }

    @RequestMapping(value = "/image", method = {RequestMethod.GET})
    @ResponseBody
    public void getImage(@RequestParam("name") String imageName,
                         HttpServletResponse response) {
        String path = "E:/image/";
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        try {
            FileInputStream in = new FileInputStream(path + imageName);
            int i = in.available();
            byte[] data = new byte[i];
            in.read(data);
            in.close();

            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value ="/addStudent", method = {RequestMethod.GET})
    public String addStudent() {
        return "addStudent";
    }

    @RequestMapping(value ="/updatePage", method = {RequestMethod.GET})
    public String updatePage() {
        return "updatePage";
    }


    @RequestMapping(value ="/deletePage", method = {RequestMethod.GET})
    public String deletePage() {
        return "deletePage";
    }


    @RequestMapping(value ="/searchStudent", method = {RequestMethod.GET})
    public String searchStudent() {
        return "searchStudent";
    }
}
