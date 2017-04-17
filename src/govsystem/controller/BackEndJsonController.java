package govsystem.controller;

import govsystem.domain.*;
import govsystem.formbean.backform.*;
import govsystem.service.BackService;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Created by Myth on 3/12/2017.
 */
@Controller
public class BackEndJsonController {

    @Resource
    private BackService backService;
    //将用户信息传至DataGrid
    @RequestMapping("/getUsersToJson")
    @ResponseBody
    public Object getUsersToJson (String username) {
        List<User> users = backService.listUsers(username);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", users);
        jsonMap.put("total", users.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }
    //根据接收的JSON数据删除 DataGrid选中的用户
    @RequestMapping("/deleteUserById")
    @ResponseBody
    public Map<String,String> deleteUserById(@RequestBody User user) {
        int uid = 0;
        Map<String,String > map = new HashMap<String,String >();
        uid = user.getUid();
        if (backService.deleteUserById(uid)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    //根据接收的JSON数据修改DataGrid选中的用户
    @RequestMapping("/modifyUser")
    @ResponseBody
    public Map<String,String> modifyUser(ModifyUserForm modifyUserForm) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.modifyUser(modifyUserForm)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }

    //将新闻传至DataGrid
    @RequestMapping("/getNewsToJson")
    @ResponseBody
    public Object getNewsToJson (Integer nid) {
        List<News> news = backService.listNews(nid);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", news);
        jsonMap.put("total", news.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }
    @RequestMapping("/addNews")
    @ResponseBody
    public Map<String,String> addNews(AddNewsForm addNewsForm, HttpSession httpSession) {
        Map<String,String > map = new HashMap<String,String >();
        News news = new News();
        int aid;
        try {
            BeanUtils.copyProperties(news,addNewsForm);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","error");
            return map;
        }
        news.setApplyNum(0);
        news.setLookedNum(0);
        news.setMessageNum(0);
        news.setAid(((Admin)httpSession.getAttribute("admin")).getAid());
        if (backService.addNews(news)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }

    //根据接收的JSON数据删除 DataGrid选中的用户
    @RequestMapping("/deleteNewsById")
    @ResponseBody
    public Map<String,String> deleteNewsById(@RequestBody News news) {
        int nid = 0;
        Map<String,String > map = new HashMap<String,String >();
        nid = news.getNid();
        if (backService.deleteNewsById(nid)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/modifyNews")
    @ResponseBody
    public Map<String,String> modifyNews(ModifyNewsForm modifyNewsForm,HttpSession httpSession) {
        Map<String,String > map = new HashMap<String,String >();
        News news = new News();
        try {
            BeanUtils.copyProperties(news,modifyNewsForm);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg","error");
            return map;
        }
        news.setAid(((Admin)httpSession.getAttribute("admin")).getAid());
        if (backService.modifyNews(news)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }

    @RequestMapping("/getMessageToJson")
    @ResponseBody
    public Object getMessageToJson (Integer nid) {
        List<Message> messageList = backService.listMessage(nid);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", messageList);
        jsonMap.put("total", messageList.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }

    @RequestMapping("/getLookedUserToJson")
    @ResponseBody
    public Object getLookedUserToJson (Integer nid) {
        List<User> userList = backService.listLookedUser(nid);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", userList);
        jsonMap.put("total", userList.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }

    @RequestMapping("/getApplyUserToJson")
    @ResponseBody
    public Object getApplyUserToJson (Integer nid) {
        List<User> userList = backService.listApplyUser(nid);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", userList);
        jsonMap.put("total", userList.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }

    @RequestMapping("/allowApply")
    @ResponseBody
    public Map<String,String> allowApply(Integer nid,Integer uid) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.allowApply(nid,uid)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/refuseApply")
    @ResponseBody
    public Map<String,String> refuseApply(Integer nid,Integer uid) {
        Map<String,String > map = new HashMap<String,String>();
        if (backService.refuseApply(nid,uid)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/refuseView")
    @ResponseBody
    public Map<String,String> refuseView(Integer nid,Integer uid) {
        Map<String,String > map = new HashMap<String,String>();
        if (backService.refuseView(nid,uid)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/deleteMessage")
    @ResponseBody
    public Map<String,String> deleteMessage(@RequestBody Message message) {
        Map<String,String > map = new HashMap<String,String>();
        if (backService.deleteMessage(message)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/checkMessage")
    @ResponseBody
    public Map<String,String> checkMessage(@RequestBody Message message) {
        Map<String,String > map = new HashMap<String,String>();
        message.setState(1); //设置审核通过标记
        if (backService.checkMessage(message)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }


    @RequestMapping("/getAdminToJson")
    @ResponseBody
    public Object getAdminToJson() {
        List<Admin> userList = backService.listAdmin();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", userList);
        jsonMap.put("total", userList.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }
    @RequestMapping("/addAdmin")
    @ResponseBody
    public Map<String,String> addAdmin(Admin admin) {
        Map<String,String> map = new HashMap<String,String>();
        if (backService.addAdmin(admin)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/modifyAdmin")
    @ResponseBody
    public Map<String,String> modifyAdmin(Admin admin) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.modifyAdmin(admin)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/deleteAdminById")
    @ResponseBody
    public Map<String,String> deleteAdminById(@RequestBody Admin admin) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.deleteAdminById(admin.getAid())) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }

    @RequestMapping("/getQuestionToJson")
    @ResponseBody
    public Object getQuestionToJson () {
        List<Question> userList = backService.listQuestion();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", userList);
        jsonMap.put("total", userList.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }
    @RequestMapping("/getQuetionItemToJson")
    @ResponseBody
    public Object getQuetionItemToJson (Integer qid) {
        Question question = new Question();
        question.setQid(qid);
        List<QuestionItem> userList = backService.listQuetionItem(question);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", userList);
        jsonMap.put("total", userList.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }

    @RequestMapping("/deleteQuestion")
    @ResponseBody
    public Map<String,String> deleteQuestion(int qid) {
        Question question = new Question();
        question.setQid(qid);
        Map<String,String > map = new HashMap<String,String >();
        if (backService.deleteQuestion(question)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }

    @RequestMapping("/deleteQuestionItem")
    @ResponseBody
    public Map<String,String> deleteQuestionItem(int qid,int no) {
        QuestionItem questionItem = new QuestionItem();
        questionItem.setQid(qid);
        questionItem.setNo(no);
        Map<String,String > map = new HashMap<String,String >();
        if (backService.deleteQuestionItem(questionItem)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/addQuestion")
    @ResponseBody
    public Map<String,String> addQuestion(AddQuestionForm addQuestionForm,HttpSession httpSession) {
        Map<String,String > map = new HashMap<String,String >();
        Question question = new Question();
        question.setAvailable(addQuestionForm.getAvailable());
        question.setTitle(addQuestionForm.getTitle());
        question.setAid(((Admin)httpSession.getAttribute("admin")).getAid());
        if (backService.addQuestion(question)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/addQuestionItem")
    @ResponseBody
    public Map<String,String> addQuestionItem(AddQuestionItemForm addQuestionItemForm) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.addQuestionItem(addQuestionItemForm)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }

    @RequestMapping("/modifyQuestion")
    @ResponseBody
    public Map<String,String> modifyQuestion(Question question) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.modifyQuestion(question)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/addVideo")
    @ResponseBody
    public Map<String,String> addVideo(AddVideoForm addVideoForm,HttpServletRequest request) throws IllegalStateException, IOException
    {
        Map<String,String > map = new HashMap<String,String >();
        Video video = new Video();
        video.setTitle(addVideoForm.getTitle());
        video.setDescription(addVideoForm.getDescription());
        video.setAid(((Admin)request.getSession().getAttribute("admin")).getAid());
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            if(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    // 文件保存路径
                    String filePath = request.getSession().getServletContext().getRealPath("/") + "mp4/"
                            + file.getOriginalFilename();
                    // 转存文件
                    file.transferTo(new File(filePath));
                    video.setFileName(file.getOriginalFilename());
                }
            }
        }
        if (backService.addVideo(video)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }

    @RequestMapping("/getVideoToJson")
    @ResponseBody
    public Object getVideoToJson () {
        List<Video> videos = backService.listVideo();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", videos);
        jsonMap.put("total", videos.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }
    @RequestMapping("/modifyVideo")
    @ResponseBody
    public Map<String,String> modifyVideo(Video video) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.updateVideo(video)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("/deleteVideo")
    @ResponseBody
    public Map<String,String> deleteVideo(int vid) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.deleteVideo(vid)) {
            map.put("msg","success");
        } else {
            map.put("msg","error");
        }
        return map;
    }
}
