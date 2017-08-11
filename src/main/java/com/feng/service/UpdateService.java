package com.feng.service;

import com.feng.dao.UpdateDAO;
import com.feng.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateService {
    @Autowired
    UpdateDAO updateDAO;

    public String saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //获取扩展名
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (!isImageFile(extName)){
            return null;
        }
        String path = "E:/image";
        File tempFile = new File(path);
        if(!tempFile.exists()){
            tempFile.mkdirs();
        }

        //重命名图片
        String newFileName = UUID.randomUUID().toString().replaceAll("-","").concat("." + extName);

        //将文件保存到另一目标文件
        file.transferTo(new File(path + File.separator + newFileName));
        return newFileName;
    }

    //判断文件类型是否为图片
    public boolean isImageFile(String extName){
        String imageExt[] = new String[]{"jpg","png","bmp","jpeg","gif"};
        for (String ext : imageExt){
            if (ext.equals(extName)){
                return true;
            }
        }
        return false;
    }
    
    public void addStudent(String name,String gender,Integer age,String number,String tel,String image){
        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        student.setNumber(number);
        student.setTel(tel);
        student.setImage(image);
        updateDAO.addStudent(student);

    }
    public void deleteStudent(Integer id){
        updateDAO.deleteStudent(id);
    }


    public void updateStudent(Integer id,String name,String gender,Integer age,String number,String tel,String image){
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        student.setNumber(number);
        student.setTel(tel);
        student.setImage(image);
        updateDAO.updateStudent(student);
    }
    public List<Student> findStudent(Student student){
      return updateDAO.findStudentByParameter(student);
    }
}
