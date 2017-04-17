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

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by Myth on 3/12/2017.
 */
@Controller
public class LoginController {
    @Resource
    private FrontService frontService;
    @Resource
    private BackService backService;

    @RequestMapping("/registUser")
    @ResponseBody
    public Map<String,String > registUser(RegistForm registForm,HttpSession httpSession)  {
        Map<String,String > map = new HashMap<String,String >();
        User user = frontService.regist(registForm);
        if (user != null) {
            //httpSession.setAttribute("user",user);
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }


    @RequestMapping("/login")
    @ResponseBody
    public Map<String,String > login(LoginForm loginForm,HttpSession httpSession)  {
        Map<String,String > map = new HashMap<String,String >();
        if (loginForm.getIsAdm() == 0) {
            User user = frontService.loginUser(loginForm);
            if (user != null) {
                httpSession.setAttribute("user",user);
                map.put("msg","success");
                map.put("roleName","user");
            } else {
                map.put("msg","error");
            }
        } else {
            Admin admin = frontService.loginAdmin(loginForm);
            if (admin != null) {
                httpSession.setAttribute("admin",admin);
                map.put("msg","success");
                map.put("roleName","admin");
            } else {
                map.put("msg","error");
            }
        }
        return map;
    }


    @RequestMapping("/logout")
    public String logout(HttpSession httpSession)  {
        httpSession.invalidate();
        return "/front-end/index";
    }
}
