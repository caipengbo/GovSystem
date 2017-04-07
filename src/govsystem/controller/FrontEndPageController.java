package govsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 单纯的页面跳转（无数据处理内容）
 * Created by Myth on 3/20/2017.
 */
@Controller
public class FrontEndPageController {
    @RequestMapping("/toFrontEndIndex")
    public String toFrontEndIndex(){
        return "/front-end/index";
    }
    @RequestMapping("/toSetting")
    public String toSetting(){
        return "/front-end/setting";
    }


}
