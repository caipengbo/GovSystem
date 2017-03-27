package govsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:后台页面的跳转
 * Created by Myth on 3/16/2017.
 */
@Controller
public class BackEndPageController {
    @RequestMapping("/toBackEndIndex")
    public String toBackEndIndex(){
        return "/back-end/index";
    }
    @RequestMapping("/toNews")
    public String toNews(){
        return "/back-end/news_manage";
    }
    @RequestMapping("/toUser")
    public String toUser(){
        return "/back-end/user_manage";
    }
    @RequestMapping("/toHome")
    public String toHome(){
        return "/back-end/home";
    }
    @RequestMapping("/toQuestion")
    public String toQuestion(){
        return "/back-end/question";
    }
}
