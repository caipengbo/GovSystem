package cn.caipengbo.action;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Myth on 3/1/2017.
 */
@Controller
public class Hello2 {
    @RequestMapping("/sayHelloAgain")
    public String sayHelloAgain() {
        System.out.println("调用sayHelloAgain方法");
        return "view";
    }
}
