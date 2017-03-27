package govsystem.controller;

import govsystem.domain.News;
import govsystem.domain.User;
import govsystem.service.FrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 前端 Controller
 * Created by Myth on 3/25/2017.
 */
@Controller
public class FrontEndController {
    @Resource
    private FrontService frontService;

    @RequestMapping("/changePassword")
    @ResponseBody
    public Map<String,String > changePassword(String oldPassword,String newPassword,HttpSession httpSession) {
        Map<String,String > map = new HashMap<String,String >();
        User user = (User)httpSession.getAttribute("user");
        if (!user.getPassword().equals(oldPassword)) {
            map.put("msg","error");
            return map;
        }
        user.setPassword(newPassword);
        if (frontService.modifyUser(user)) {
            httpSession.setAttribute("user",user);
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    //简单提交身份证，后台修改身份证
    @RequestMapping("/authenticate")
    @ResponseBody
    public Map<String,String > authenticate(String identitycode, HttpSession httpSession) {
        Map<String,String> map = new HashMap<String,String >();
        if (identitycode == null) {
            map.put("msg","error");
            return map;
        }
        User user = (User)httpSession.getAttribute("user");
        user.setIdentityCode(identitycode);
        user.setIdentityFlag(2);
        if (frontService.modifyUser(user)) {
            httpSession.setAttribute("user",user);
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/saveUser")
    @ResponseBody
    public Map<String,String> saveUser(@RequestBody User user, HttpSession httpSession) {
        Map<String,String > map = new HashMap<String,String >();
        user.setPassword(((User)httpSession.getAttribute("user")).getPassword());
        user.setIdentityCode(((User)httpSession.getAttribute("user")).getIdentityCode());
        user.setIdentityFlag(((User)httpSession.getAttribute("user")).getIdentityFlag());
        user.setUid(((User)httpSession.getAttribute("user")).getUid());
        if (frontService.modifyUser(user)) {
            httpSession.setAttribute("user",user);
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/toNewsView")
    public ModelAndView toNewsView(){
        ModelAndView mav = new ModelAndView();
        List<News> newsList = frontService.listAllNews();

        mav.addObject("newsList",newsList);
        mav.setViewName("/front-end/news_view");
        return mav;
    }
}
