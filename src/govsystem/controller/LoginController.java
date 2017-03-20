package govsystem.controller;

import govsystem.domain.Admin;
import govsystem.domain.User;
import govsystem.formbean.frontform.LoginForm;
import govsystem.formbean.frontform.RegistForm;
import govsystem.service.BackService;
import govsystem.service.FrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by Myth on 3/12/2017.
 */
@Controller
@SessionAttributes("role")
public class LoginController {
    @Resource
    private FrontService frontService;
    @Resource
    private BackService backService;

    @RequestMapping("/registUser")
    public ModelAndView registUser(RegistForm registForm)  {
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
    @ResponseBody
    public Map<String,String > login(LoginForm loginForm)  {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,String > map = new HashMap<String,String >();  // map里面 roleName -1失败，  0用户  1 管理员
        if (loginForm.getIsAdm() == 0) {
            User user = frontService.loginUser(loginForm);
            if (user != null) {
                modelAndView.addObject("role", user);
                map.put("msg","success");
                map.put("roleName","user");
            } else {
                map.put("msg","error");
            }
        } else {
            Admin admin = frontService.loginAdmin(loginForm);
            if (admin != null) {
                modelAndView.addObject("role", admin);
                map.put("msg","success");
                map.put("roleName","admin");
            } else {
                map.put("msg","error");
            }
        }

        return map;
    }
}
