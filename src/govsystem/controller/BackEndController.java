package govsystem.controller;

import govsystem.domain.News;
import govsystem.service.BackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * Created by Myth on 3/15/2017.
 */
@Controller
public class BackEndController {
    @Resource
    private BackService backService;
    @RequestMapping("/getNewsContext")
    public ModelAndView getNewsContext(int nid) {
        ModelAndView mav = new ModelAndView();
        News news = new News();
        List<News> newsList = backService.listNews(nid);
        if (newsList.size() > 0) {
            mav.addObject("news", newsList.get(0));
            mav.setViewName("back-end/news_context");
        } else {
            mav.setViewName("back-end/error");
        }
        return mav;
    }
}
