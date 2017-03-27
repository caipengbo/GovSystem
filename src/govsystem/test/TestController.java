package govsystem.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/26/2017.
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView();
        List<String> list = new LinkedList<>();
        list.add("串0");
        list.add("串1");
        list.add("串2");
        list.add("串3");
        list.add("串4");
        mav.addObject("list",list);
        mav.setViewName("/testpages/view");
        return mav;
    }
}
