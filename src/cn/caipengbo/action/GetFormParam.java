package cn.caipengbo.action;

import cn.caipengbo.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Myth on 3/3/2017.
 */
@Controller
public class GetFormParam {
    @RequestMapping("/getStudentInfo")
    public String getStudentInfo(Student student) {
        System.out.println(student.getName() + " " +student.getAge());
        return "forward:sayHelloAgain.action";
    }
}
