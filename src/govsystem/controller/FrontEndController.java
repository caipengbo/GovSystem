package govsystem.controller;

import govsystem.domain.*;
import govsystem.service.FrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

        if (frontService.authenticate(user.getName(),identitycode)) {
            user.setIdentityCode(identitycode);
            user.setIdentityFlag(1); //设置成已经认证
            if (frontService.modifyUser(user)) {
                httpSession.setAttribute("user",user);  //更新session中的user
                map.put("msg","success");
            } else {
                map.put("msg","error");
            }
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
    //信息视图，默认是公开的信息
    @RequestMapping("/toNewsView")
    public ModelAndView toNewsView(){
        ModelAndView mav = new ModelAndView();
        List<News> newsList = frontService.listNews(1);
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
        List<Message> messageList = frontService.listMessage(nid);
        mav.addObject("messageList",messageList);
        mav.addObject("news",news);
        mav.setViewName("/front-end/news_detail");
        return mav;
    }

    @RequestMapping("/addMessage")
    public ModelAndView addMessage(int nid,String comment,HttpSession httpSession) {
        ModelAndView mav = new ModelAndView();
        if (httpSession == null) {
            mav.setViewName("/front-end/error");
            return mav;
        }
        int identityFlag = ((User)httpSession.getAttribute("user")).getIdentityFlag();
        if (identityFlag == 0) { //未认证
            mav.setViewName("/front-end/error");
            return mav;
        }
        Message message = new Message();
        message.setContent(comment);
        message.setNid(nid);
        message.setUid(((User)httpSession.getAttribute("user")).getUid());
        if (frontService.addMessage(message)) {
            mav = toNewsDetail(nid);
            //TODO 跳转的页面
        } else {
            mav.setViewName("/front-end/error");
        }
        return mav;
    }


    @RequestMapping("/toPrivateNews")
    public ModelAndView toPrivateNews(HttpSession httpSession){
        ModelAndView mav = new ModelAndView();
        if (httpSession == null) {
            mav.setViewName("/front-end/error");
            return mav;
        }
        int uid = ((User)httpSession.getAttribute("user")).getUid();
        List<News> newsList = frontService.listUserNews(uid);
        mav.addObject("newsList",newsList);
        mav.setViewName("/front-end/news_status");
        return mav;
    }
    @RequestMapping("/applyViewNews")
    public ModelAndView applyViewNews(int nid,HttpSession httpSession){
        int uid = ((User)httpSession.getAttribute("user")).getUid();
        ModelAndView mav = new ModelAndView();
        if (frontService.addApply(uid,nid)) {
            mav = toPrivateNews(httpSession);
        } else {
            mav.setViewName("/front-end/error");
        }
        return mav;
    }

    @RequestMapping("/toQuestionView")
    public ModelAndView toQuestionView(){
        ModelAndView mav = new ModelAndView();
        List<Question> questionList = frontService.listAllQuestion();
        mav.addObject("questionList",questionList);
        mav.setViewName("/front-end/question_view");
        return mav;
    }
    //如果已经填写问卷，那么跳转到统计界面；如果没填写，跳到填写页面
    @RequestMapping("/toQuestionDetail")
    public ModelAndView toQuestionDetail(int qid,HttpSession httpSession){
        int uid = ((User)httpSession.getAttribute("user")).getUid();
        ModelAndView mav = new ModelAndView();
        Question question = new Question();
        //检查一下，该用户是否填写了该问卷
        if (frontService.checkCompleteQuestion(uid,qid)) {
            //跳到统计页面
            question = frontService.getQuestion(qid);
            float a = question.getaNum();
            float b = question.getbNum();
            float c = question.getcNum();
            float d = question.getdNum();
            float num = a + b + c + d;
            try {
                a = a/num*100;
                b = b/num*100;
                c = c/num*100;
                d = d/num*100;
            } catch (Exception e) {
                a = 0;
                b = 0;
                c = 0;
                d = 0;
            }
            mav.addObject("a",a);
            mav.addObject("b",b);
            mav.addObject("c",c);
            mav.addObject("d",d);
            mav.addObject("title",question.getTitle());
            mav.addObject("num",question.getAllNum());
            mav.setViewName("/front-end/statistic_chart");
            return mav;
        } else {
            //填写页面
            question.setQid(qid);
            List<QuestionItem> questionItemList = frontService.listAllQuestionItem(question);
            question = frontService.getQuestion(qid);
            mav.addObject("question",question);
            mav.addObject("questionItemList",questionItemList);
            mav.setViewName("/front-end/question_detail");
            return mav;
        }


    }
    //提交投票表单（radio的处理）
    @RequestMapping("/radioSubmit")
    public ModelAndView radioSubmit(int qid, HttpServletRequest httpServletRequest){
        ModelAndView mav = new ModelAndView();
        int parm_count = Integer.parseInt(httpServletRequest.getParameter("count"));
        String itemName = "";
        int val;
        int a_count = 0;
        int b_count = 0;
        int c_count = 0;
        int d_count = 0;
        int uid = ((User)httpServletRequest.getSession().getAttribute("user")).getUid();
        List<Integer> integerList = new ArrayList<>();
        for (int i=0; i<parm_count; i++) {
            itemName = "item" + i;
            if (httpServletRequest.getParameter(itemName) != null) {
                val = Integer.parseInt(httpServletRequest.getParameter(itemName));
            } else {
                val = 0;
            }
            switch (val) {
                case 0: {
                    a_count++;
                    break;
                }
                case 1: {
                    b_count++;
                    break;
                }
                case 2: {
                    c_count++;
                    break;
                }
                case 3: {
                    d_count++;
                    break;
                }
            }
        }
        if (frontService.completeQuestion(uid,qid,a_count,b_count,c_count,d_count)) {
            //统计页面
            mav = toQuestionDetail(qid,httpServletRequest.getSession());
            mav.setViewName("/front-end/statistic_chart");
        } else {
            mav.setViewName("/front-end/error");
        }
        return mav;
    }
}
