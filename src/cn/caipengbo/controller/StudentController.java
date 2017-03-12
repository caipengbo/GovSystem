package cn.caipengbo.controller;

import cn.caipengbo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Created by Myth on 3/4/2017.
 */
@Controller
public class StudentController {
    @Resource
    private StudentService studentService;
    @RequestMapping("jdbc")
    public String test(HttpServletRequest httpServletRequest) {
        //get传的也是parameter
        long id = Long.parseLong(httpServletRequest.getParameter("id"));
        System.out.println("执行action");
        //Student student = new Student("毛泽东",32);
        //studentService.saveStudent(student);
        studentService.updateStudentById(id);
        return "view";
    }
}
