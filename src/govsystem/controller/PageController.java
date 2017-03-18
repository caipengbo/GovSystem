package govsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 页面跳转用的Controller 测试专用
 * Created by Myth on 3/12/2017.
 */
@Controller
public class PageController {
    @RequestMapping("/toregist")
    public String toRegist(){
        System.out.println("Jump to regist");
        return "/front-end/regist";
    }
    @RequestMapping("/tologin")
    public String toLogin(){
        System.out.println("Jump to login");
        return "/front-end/login";
    }
    @RequestMapping("/jsp1")
    public String toJsp1() {
        return "back-end/user_manage";
    }
    @RequestMapping("/jsp2")
    public String toJsp2() {
        return "back-end/news_manage";
    }
    @RequestMapping("/jsp3")
    public String toJsp3() {
        return "back-end/index";
    }
    @RequestMapping("/jsp4")
    public String toJsp4() {
        return "back-end/admin_manage";
    }
}
