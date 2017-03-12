package cn.caipengbo.action;

import cn.caipengbo.domain.Student;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Myth on 3/3/2017.
 */
@Controller
public class Json {
    @RequestMapping("/outputJson")
    @ResponseBody
    public Object outputJson() {
        Student user1 = new Student("test1",10);
        Student user2 = new Student("test2",20);
        List<Student> users = new ArrayList<Student>();
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        users.add(user1);
        users.add(user2);
        jsonMap.put("rows",users);
        jsonMap.put("total",users.size());
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
        System.out.println(jsonObject);
        return jsonObject;
    }
}
