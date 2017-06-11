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
    @RequestMapping("/toNewsManage")
    public String toNewsManage(){
        return "/back-end/news_manage";
    }
    @RequestMapping("/toUserManage")
    public String toUserManage(){
        return "/back-end/user_manage";
    }
    @RequestMapping("/toHome")
    public String toHome(){
        return "/back-end/home";
    }
    @RequestMapping("/toQuestionManage")
    public String toQuestionManage(){
        return "/back-end/question";
    }
    @RequestMapping("/toAdminManage")
    public String toAdminManage() {
        return "/back-end/admin_manage";
    }
    @RequestMapping("/toVideoManage")
    public String toVideoManage() {
        return "/back-end/video";
    }
}
