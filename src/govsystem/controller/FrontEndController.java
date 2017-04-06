package govsystem.controller;

import govsystem.domain.News;
import govsystem.domain.User;
import govsystem.domain.Video;
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
    @RequestMapping("/toVideoView")
    public ModelAndView toVideoView(){
        ModelAndView mav = new ModelAndView();
        List<Video> videoList  = frontService.listAllVideo();
        mav.addObject("videoList",videoList);
        mav.setViewName("/front-end/video_view");
        return mav;
    }

    @RequestMapping("/toNewsDetail")
    public ModelAndView toNewsDetail(int nid){
        ModelAndView mav = new ModelAndView();
        News news = frontService.getNews(nid);
        mav.addObject("news",news);
        mav.setViewName("/front-end/news_detail");
        return mav;
    }
    @RequestMapping("/toPublicNews")
    public ModelAndView toPublicNews(int ispublic){
        ModelAndView mav = new ModelAndView();
        if (ispublic != 0 && ispublic!= 1) {
            mav.setViewName("/front-end/error");
            return mav;
        }
        List<News> newsList = frontService.listNews(ispublic);
        mav.addObject("newsList",newsList);
        mav.setViewName("/front-end/news_view");
        return mav;
    }
    //如果已经填写问卷，那么跳转到统计界面；如果没填写，跳到填写页面
    @RequestMapping("/toVote")
    public ModelAndView toVote(){
        ModelAndView mav = new ModelAndView();
        if (ispublic != 0 && ispublic!= 1) {
            mav.setViewName("/front-end/error");
            return mav;
        }
        List<News> newsList = frontService.listNews(ispublic);
        mav.addObject("newsList",newsList);
        mav.setViewName("/front-end/vote");
    }

}
