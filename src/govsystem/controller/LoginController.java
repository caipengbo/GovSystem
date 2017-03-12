package govsystem.controller;

import govsystem.domain.User;
import govsystem.formbean.frontform.LoginForm;
import govsystem.formbean.frontform.RegistForm;
import govsystem.service.FrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Description:
 * Created by Myth on 3/12/2017.
 */
@Controller
public class LoginController {
    @Resource
    private FrontService frontService;

    @RequestMapping("/regist")
    public ModelAndView regist(RegistForm registForm)  {
        System.out.println(registForm.toString());
        User user = frontService.regist(registForm);
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            modelAndView.addObject("message",user.getName() + "注册成功！");
            modelAndView.setViewName("front-end/success");
        } else {
            modelAndView.setViewName("front-end/error");
        }
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(LoginForm loginForm)  {
        //TODO 在此处添加验证码验证
        User user = frontService.login(loginForm);
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            modelAndView.addObject("message",user.getName() + "登录成功！");
        } else {
            modelAndView.addObject("message","登录失败，账号或者密码错误！");
        }
        modelAndView.setViewName("front-end/success");
        return modelAndView;
    }
}
