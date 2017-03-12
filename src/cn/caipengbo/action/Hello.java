package cn.caipengbo.action;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Myth on 3/1/2017.
 */
//这是一个 controller
@Controller
public class Hello {
    //注解，指定url
    @RequestMapping("/sayHello")
    public String sayHello() {
        System.out.println("转向json页面");
        return "json";
    }
    @RequestMapping("/interceptor")
    public ModelAndView testInterceptor() {
        System.out.println("执行Action");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("view");
        return modelAndView;
    }

}
