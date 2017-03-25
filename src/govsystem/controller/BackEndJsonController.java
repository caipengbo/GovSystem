package govsystem.controller;

import govsystem.domain.*;
import govsystem.formbean.backform.*;
import govsystem.service.BackService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public Map<String,String> addNews(AddNewsForm addNewsForm) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.addNews(addNewsForm)) {
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
    public Map<String,String> modifyNews(ModifyNewsForm modifyNewsForm) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.modifyNews(modifyNewsForm)) {
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

    @RequestMapping("/getQuetionToJson")
    @ResponseBody
    public Object getQuetionToJson () {
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
    public Map<String,String> addQuestion(AddQuestionForm addQuestionForm) {
        Map<String,String > map = new HashMap<String,String >();
        if (backService.addQuestion(addQuestionForm)) {
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

}
