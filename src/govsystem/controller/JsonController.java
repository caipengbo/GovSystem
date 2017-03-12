package govsystem.controller;

import govsystem.domain.User;
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
public class JsonController {

    @Resource
    private BackService backService;
    //将用户信息传至DataGrid
    @RequestMapping("/getUsersToJson")
    @ResponseBody
    public Object getUsersToJson () {
        List<User> users = backService.listUsers();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("rows", users);
        jsonMap.put("total", users.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        return jsonObject;
    }
    //根据接收的JSON数据删除 DataGrid选中的用户
    @RequestMapping("/deleteUserById")
    public boolean deleteUserById(@RequestBody String uid) {
        System.out.println(uid);
        return false;
    }
}
